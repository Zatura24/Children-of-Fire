package nl.han.ica.childrenoffire.objects;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.childrenoffire.ChildrenOfFire;
import nl.han.ica.childrenoffire.tiles.WallTile;

/**
 * <h2>Bullet<h2>
 * 
 * Implementation of the bullet class
 * 
 * @see SpriteObject, ICollidableWithGameObjects, ICollidableWithTiles
 */
public class Bullet extends SpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {
    private int speed = 5;
    private float x, y;
    private float direction;
    private ChildrenOfFire world;
    private int bulletDamage;
    private boolean canHurtEnemy;
    private boolean canHurtPlayer;

    /**
     * Basic constuctor of the bullet class
     * 
     * @param world - Reference to the world object
     * @param float x - x position of the object
     * @param float y - y position of the object
     * @param float direction - direction of the object
     * @param int bulletDamage - damage of the bullet
     */
    public Bullet(ChildrenOfFire world, float x, float y, float direction, int bulletDamage) {
        this(world, x, y, direction, bulletDamage, false, false);
    }

    /**
     * Basic constuctor of the bullet class
     * 
     * @param world - Reference to the world object
     * @param float x - X position of the object
     * @param float y - Y position of the object
     * @param float direction - Direction of the object
     * @param int bulletDamage - Damage of the bullet
     * @param bool canHurtEnemy - If this bullet can hit an enemy instance
     * @param bool canHurtPlayer - If this bullet can hit a player instance
     */
    public Bullet(ChildrenOfFire world, float x, float y, float direction, int bulletDamage, boolean canHurtEnemy, boolean canHurtPlayer){
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/mana-ball.png"));
        this.world = world;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.bulletDamage = bulletDamage;
        this.canHurtEnemy = canHurtEnemy;
        this.canHurtPlayer = canHurtPlayer;
    }

    /**
     * The movement of the bullet
     */
    public void bulletMove() {
        setX(x);
        setY(y);
        setDirectionSpeed(direction, speed);
    }

    /**
     * This function will be called every frame
     */
    public void update() {

    }

    /**
    * This function will be called when player collides with a game object
    * 
    * @param List<GameObject> collidedObjects - List of collided objects
    */
    public void gameObjectCollisionOccurred(java.util.List<GameObject> collidedObjects) {
        for (GameObject object : collidedObjects) {
            // If collided with an enemy and is allowed to hit it, deal damage
            if(canHurtEnemy){
                if (object instanceof Enemy) {
                    ((Enemy) object).decreaseHealth(bulletDamage);
                    world.deleteGameObject(this);
                }
            }
            // If collided with a player and is allowed to hit it, deal damage
            if(canHurtPlayer){
                if (object instanceof Player) {
                    ((Player) object).decreaseHealth(bulletDamage);
                    world.deleteGameObject(this);
                }
            }
        }
    }

    /**
    * This function will be called when player collides with a tile
    * 
    * @param List<CollidedTile> collidedTiles - List of collided tiles
    */
    public void tileCollisionOccurred(java.util.List<CollidedTile> collidedTiles) {
        for (CollidedTile t : collidedTiles) {
            // If collided with a wall, destory this object
            if (t.theTile instanceof WallTile) {
                world.deleteGameObject(this);
            }
        }
    }
}