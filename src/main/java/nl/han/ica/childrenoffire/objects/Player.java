package nl.han.ica.childrenoffire.objects;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.AnimatedSpriteObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.childrenoffire.ChildrenOfFire;
import nl.han.ica.childrenoffire.tiles.KeyHoleTile;
import nl.han.ica.childrenoffire.tiles.StairsTile;
import nl.han.ica.childrenoffire.tiles.WallTile;
import processing.core.PVector;

/**
 * <h2>Player</h2>
 * 
 * Implementation of the player class.
 * 
 * @see AnimatedSpriteObject, ICollidableWithGameObjects, ICollidableWithTiles
 */
public class Player extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {
    private ChildrenOfFire world;
    private int health;
    private int speed;
    private int credits;
    private int keys;

    private int currentKey;

    /**
     * Basic Constructor for a player class
     * 
     * @param world - Reference to the world object
     * 
     */
    public Player(ChildrenOfFire world) {
        this(world, 100, 5);
    }

    /**
     * Constructor for a player class
     * 
     * @param world - Reference to the world object
     * @param int health - The health of the player
     */
    public Player(ChildrenOfFire world, int health) {
        this(world, health, 5);
    }

    /**
     * Private constructor
     * 
     * @param world - Reference to the world object
     * @param int health - The health of the player
     * @param int speed - the movement speed of the player
     */
    private Player(ChildrenOfFire world, int health, int speed) {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/lyn.png"), 2);
        this.world = world;
        this.health = health;
        this.speed = speed;
        setCurrentFrameIndex(1);
        setFriction(0.3f);
    }

    /**
     * This function will be called every frame
     */
    public void update() {

    }

    /**
     * This function will be called when a key is pressed
     * 
     * @param int keyCode - Keycode of pressed key
     * @param char key - Character representation of pressed key
     */
    @Override
    public void keyPressed(int keyCode, char key) {
        currentKey = keyCode; // set the currently pressed key
        
        // move up
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }

        // move right
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
            setCurrentFrameIndex(1);
        }

        // move down
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }

        // move left
        if (keyCode == world.LEFT) {
            setDirectionSpeed(270, speed);
            setCurrentFrameIndex(0);
        }

        // shoot
        if (key == 's') {
            Bullet bullet = new Bullet(this.world, this.getX(), this.getY(), this.getDirection(), 10, true, false);
            world.addGameObject(bullet);
            bullet.bulletMove();
        }
    }

    /**
    * This function will be called when player collides with a game object
    * 
    * @param List<GameObject> collidedObjects - List of collided objects
    */
    @Override
    public void gameObjectCollisionOccurred(java.util.List<GameObject> collidedObjects) {
        for (GameObject object : collidedObjects) {
            
            // pickup coin
            if (object instanceof Coin) {
                ((Coin) object).pickUp(this, world);
            }

            // pickup key
            if (object instanceof Key){
                ((Key) object).pickUp(this, world);
            }
        }
    }

    /**
    * This function will be called when player collides with a tile
    * 
    * @param List<CollidedTile> collidedTiles - List of collided tiles
    */
    public void tileCollisionOccurred(java.util.List<CollidedTile> collidedTiles) {
        PVector vector;
        for (CollidedTile tile : collidedTiles) {

            // If player collided with a wall, move the player back
            if (tile.theTile instanceof WallTile || tile.theTile instanceof KeyHoleTile) {
                if (tile.collisionSide == tile.INSIDE) {
                    // if the player was moving up, move it back down
                    if (currentKey == world.UP) {
                        try {
                            vector = world.getTileMap().getTilePixelLocation(tile.theTile);
                            setY(vector.y + getHeight());
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    // if the player was moving right, move it back left
                    if (currentKey == world.RIGHT) {
                        try {
                            vector = world.getTileMap().getTilePixelLocation(tile.theTile);
                            setX(vector.x - getWidth());
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    // if the player was moving down, move it back up
                    if (currentKey == world.DOWN) {
                        try {
                            vector = world.getTileMap().getTilePixelLocation(tile.theTile);
                            setY(vector.y - getHeight());
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    // if the player was moving left, move it back right
                    if (currentKey == world.LEFT) {
                        try {
                            vector = world.getTileMap().getTilePixelLocation(tile.theTile);
                            setX(vector.x + getWidth());
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            // If the player collided with a stair, go to next level
            if (tile.theTile instanceof StairsTile) {
                world.goToNextTileMap();
            }

            // If the player collided with a keyhole, try open it
            if (tile.theTile instanceof KeyHoleTile) {
                world.openGate(tile.theTile);
            }
        }
    }

    /**
     * Increase the player score with a given amount
     * 
     * @param int value - value with wich the score will increased
     */
    protected void increaseScore(int value) {
        this.credits += value;
    }

    /**
     * Increase key amount with given value
     * 
     * @param int value - value with wich the keys will increased
     */
    protected void increaseKeys(int value) {
        this.keys += value;
    }

    /**
     * Decreasing the health of the player with a given amount
     * 
     * @param int amount - the amount with wich the player health will decrease
     */
    protected void decreaseHealth(int amount) {
        // decrease health
        if (this.health > 0) {
            this.health -= amount;
        }

        // if health is lower than 0, restart the game
        if (this.health <= 0) {
            world.resetGame();
        }
    }

    /**
     * Get current health
     * 
     * @return int health - current player health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Get current credits
     * 
     * @return int credits - current player credits
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * Get current keys
     * 
     * @return int keys - current player keys
     */
    public int getKeys() {
        return this.keys;
    }

}