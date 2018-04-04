package nl.han.ica.childrenoffire;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.childrenoffire.tiles.WallTile;

public class Bullet extends SpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {
    private int speed = 5;
    private float x, y;
    private float direction;
    private ChildrenOfFire world;
    private int bulletDamage;

    public Bullet(float x, float y, float direction, int bulletDamage, ChildrenOfFire world) {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/mana-ball.png"));
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.world = world;
        this.bulletDamage = bulletDamage;
    }

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

    public void gameObjectCollisionOccurred(java.util.List<GameObject> collidedObjects) {
        for (GameObject object : collidedObjects) {
            if (object instanceof Enemy) {
                ((Enemy) object).decreaseHealth(bulletDamage);
                world.deleteGameObject(this);
            }
        }
    }

    public void tileCollisionOccurred(java.util.List<CollidedTile> collidedTiles) {
        for (CollidedTile t : collidedTiles) {
            if (t.theTile instanceof WallTile) {
                world.deleteGameObject(this);
            }
        }
    }
}