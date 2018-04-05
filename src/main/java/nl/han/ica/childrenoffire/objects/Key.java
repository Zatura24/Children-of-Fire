package nl.han.ica.childrenoffire.objects;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;
import nl.han.ica.childrenoffire.ChildrenOfFire;
import nl.han.ica.childrenoffire.interfaces.IPickupable;

/**
 * Basic key class that has the implementations of IPickupable
 * 
 * @see SpriteObject, IPickupable
 */
public class Key extends SpriteObject implements IPickupable{
    /**
     * Basic constructor of the key class
     */
    public Key() {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/key.png"));
    }

    /**
     * This function will be called every frame
     */
    public void update() {
        
    }

    /**
     * What will happen when a player picks up this key
     */
    @Override
    public void pickUp(Player player, ChildrenOfFire world) {
        player.increaseKeys(1);
        world.deleteGameObject(this);
    }
}