package nl.han.ica.childrenoffire;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;

/**
 * Enemy
 */
public abstract class Enemy extends AnimatedSpriteObject implements IHasItem, ICollidableWithGameObjects {

    private ChildrenOfFire world;

    private int health;

    private int attackDamage = 20;

    // simpel timer variables for moving the object
    private final int MOVEDELAY = 1000;
    private long startTime;
    private long currentTime;

    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * @param path - Path to the sprite object
     * @param health - Amount of health this enemy has
     */
    public Enemy(ChildrenOfFire world, String path, int posX, int posY, int health) {
        super(new Sprite(path), 2);
        this.world = world;
        this.health = health;

        setCurrentFrameIndex(0);
        setX(posX);
        setY(posY);

        startTime = System.currentTimeMillis();
    }

    /**
     * This function will be called every frame
     */
    @Override
    public void update() {
        currentTime = System.currentTimeMillis();

        moveObject();
        destroyObjectIfDead();
    }

    /** 
     * This function takes a snippet of the index map surrounding the object in a 3x3 grid.
     * <p>
     * i.e. 
     * [
     *  [1, 1, 1],
     *  [0, 0, 0],
     *  [0, 0, 0]
     * ]
     * this means that there is a wall above the object
     * 
     * @return Returns a 2 dimensional array snippet of the surrounding index map
     */
    private int[][] getObjectSurrounding() {
        int map[][] = new int[3][3];
        int tileSize = world.getTileMap().getTileSize();

        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                map[row + 1][col + 1] = world.getTileMap().findTileTypeIndex(world.getTileMap()
                        .getTileOnPosition((int) (getX() + (col * tileSize)), (int) (getY() + (row * tileSize))));
            }
        }

        return map;
    }

    /**
     * Moves the object in a random direction if the timer has surpassed the threshold
     */
    private void moveObject() {
        if (currentTime - startTime >= MOVEDELAY) {
            startTime = System.currentTimeMillis();

            int direction = (int) (Math.random() * 360);
            setSpeed(1);
            setDirection(direction);

            setCurrentFrameIndex(direction > 180 ? 1 : 0);
        }

        preventFromGetttingOOB();
    }

    /**
     * This prevents the object from getting out of bounds (OOB).
     * <p>
     * <b>Note:</b> This function should always be called in the moveObject function
     */
    private void preventFromGetttingOOB() {
        int surroundings[][] = getObjectSurrounding();

        if (surroundings[0][1] != 0) {
            setDirection(180);
        }

        if (surroundings[1][2] != 0) {
            setDirection(270);
            setCurrentFrameIndex(1);
        }

        if (surroundings[2][1] != 0) {
            setDirection(0);
        }

        if (surroundings[1][0] != 0) {
            setDirection(90);
            setCurrentFrameIndex(0);
        }
    }

    /**
     * This functions destroys the object only if its health is less then or equal to 0
     */
    private void destroyObjectIfDead() {
        if (health <= 0) {
            dropItem();
            world.deleteGameObject(this);
        }
    }

    @Override
    public void gameObjectCollisionOccurred(java.util.List<GameObject> collidedObjects) {
        for (GameObject object : collidedObjects) {
            if (object instanceof Player) {
                Player objectAsPlayer = (Player) object;
                
                objectAsPlayer.decreaseHealth(attackDamage);

                // move the player away from the enemy
                if (objectAsPlayer.getDirection() == 90) { // van links naar rechts
                    objectAsPlayer.setX(objectAsPlayer.getX() - (objectAsPlayer.getWidth() / 2));
                }
                if (objectAsPlayer.getDirection() == 270) { // van rechts naar links
                    objectAsPlayer.setX(objectAsPlayer.getX() + (objectAsPlayer.getWidth() / 2));
                }
                if (objectAsPlayer.getDirection() == 0) { // van onder naar boven
                    objectAsPlayer.setY(objectAsPlayer.getY() + (objectAsPlayer.getWidth() / 2));
                }
                if (objectAsPlayer.getDirection() == 180) { // van onder naar boven
                    objectAsPlayer.setY(objectAsPlayer.getY() - (objectAsPlayer.getWidth() / 2));
                }
            }
        }
    }

    /**
     * Implement what will happen when this enemy drops an item
     */
    @Override
    public abstract void dropItem();

    protected ChildrenOfFire getWorld() {
        return this.world;
    }

    protected void decreaseHealth(int amount) {
        this.health -= amount;
    }

}