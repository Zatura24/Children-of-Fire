package nl.han.ica.childrenoffire;

import nl.han.ica.OOPDProcessingEngineHAN.Collision.CollidedTile;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithGameObjects;
import nl.han.ica.OOPDProcessingEngineHAN.Collision.ICollidableWithTiles;
import nl.han.ica.OOPDProcessingEngineHAN.Exceptions.TileNotFoundException;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.GameObject;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.childrenoffire.tiles.StairsTile;
import nl.han.ica.childrenoffire.tiles.WallTile;
import processing.core.PVector;

public class Player extends SpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles{
    private ChildrenOfFire world;
    private int health;
    private int speed;
    private int credits;

    
    private int currentKey;

    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * 
     */
    public Player(ChildrenOfFire world){
        this(world, 100, 5);        
    }

    public Player(ChildrenOfFire world, int health) {
        this(world, health, 5);
    }

    private Player(ChildrenOfFire world, int health, int speed) {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/lyn.png"));
        this.world = world;
        this.health = health;
        this.speed = speed;
        setFriction(0.3f);
    }

    /**
     * This function will be called every frame
     */
    public void update(){
        
    }

    /**
     * This function will be called when a key is pressed
     * 
     * @param int keyCode - Keycode of pressed key
     * @param char key - Character representation of pressed key
     */
    @Override
    public void keyPressed(int keyCode, char key){
        currentKey = keyCode;
        if (keyCode == world.UP) {
            setDirectionSpeed(0, speed);
        }
        if (keyCode == world.RIGHT) {
            setDirectionSpeed(90, speed);
        }
        if (keyCode == world.DOWN) {
            setDirectionSpeed(180, speed);
        }
        if(keyCode == world.LEFT){
            setDirectionSpeed(270, speed);
        }
    }

    public void increaseScore(int value){
        this.credits += value;
    }

    @Override
    public void gameObjectCollisionOccurred(java.util.List<GameObject> collidedObjects){
        for(GameObject o : collidedObjects){
            if(o instanceof Coin){
                ((Coin) o).pickUp(this, world);
            }

        }
    }

    /**
    * This function will be called when player collides with an tile
    * 
    * @param List<CollidedTile> collidedTiles - List of collidedtiles
    */
    public void tileCollisionOccurred(java.util.List<CollidedTile> collidedTiles){
        PVector vector;
        for(CollidedTile t : collidedTiles){
            // If player collided with a wall, move the player back
            if(t.theTile instanceof WallTile){
                if(t.collisionSide == t.INSIDE){
                    if(currentKey == world.UP){
                        try{
                            vector = world.getTileMap().getTilePixelLocation(t.theTile);
                            setY(vector.y + getHeight());
                        }
                        catch(TileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                    if (currentKey == world.RIGHT) {
                        try {
                            vector = world.getTileMap().getTilePixelLocation(t.theTile);
                            setX(vector.x - getWidth());
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    if(currentKey == world.DOWN){
                        try{
                            vector = world.getTileMap().getTilePixelLocation(t.theTile);
                            setY(vector.y - getHeight());
                        }
                        catch(TileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                    if (t.collisionSide == t.INSIDE) {
                        if (currentKey == world.LEFT) {
                            try {
                                vector = world.getTileMap().getTilePixelLocation(t.theTile);
                                setX(vector.x + getWidth());
                            } catch (TileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                 
            }

            // If the player collided with a stair, go to next level
            if(t.theTile instanceof StairsTile){
               world.goToNextTileMap();
            }
        }
    }

    public int getHealth() {
        return this.health;
    }

    public int getCredits() {
        return this.credits;
    }

    public void decreaseHealth(int d){
        if(this.health > 0 ){
            this.health -= d;
        }
        if(this.health == 0){
            world.resetGame();
        }
    }
}