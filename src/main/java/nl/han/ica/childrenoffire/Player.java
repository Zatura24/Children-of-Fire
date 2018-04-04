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

public class Player extends SpriteObject implements ICollidableWithGameObjects,ICollidableWithTiles{
    private int healthPlayer;
    private int speed = 5;
    private ChildrenOfFire world;
    private int currentKey;
    private int credits;

    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * 
     */
    public Player(ChildrenOfFire world){
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/lyn.png"));
        this.world = world;
        setFriction(0.3f);
    }

    /**
     * This function will be called every frame
     */
    public void update(){
        //Doe iets
    }

    /**
     * This function will be called when a key is pressed
     * 
     * @param int keyCode - keycode of pressed key
     * @param char key - character representation of pressed key
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
    * @param List<CollidedTile> collidedTiles - list of collidedtiles
    */
    public void tileCollisionOccurred(java.util.List<CollidedTile> collidedTiles){
        PVector vector;
        for(CollidedTile t : collidedTiles){
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
                }
                if (t.collisionSide == t.INSIDE) {
                    if (currentKey == world.RIGHT) {
                        try {
                            vector = world.getTileMap().getTilePixelLocation(t.theTile);
                            setX(vector.x - getWidth());
                        } catch (TileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if(t.collisionSide == t.INSIDE){
                    if(currentKey == world.DOWN){
                        try{
                            vector = world.getTileMap().getTilePixelLocation(t.theTile);
                            setY(vector.y - getHeight());
                        }
                        catch(TileNotFoundException e){
                            e.printStackTrace();
                        }
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
            if(t.theTile instanceof StairsTile){
               world.increaseTileMap();
            }
        }
    }

}