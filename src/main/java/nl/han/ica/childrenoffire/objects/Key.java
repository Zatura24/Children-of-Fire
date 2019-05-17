package nl.han.ica.ChildrenOfFire.objects;

import nl.han.ica.ChildrenOfFire.ChildrenOfFire;
import nl.han.ica.ChildrenOfFire.interfaces.IPickupable;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

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