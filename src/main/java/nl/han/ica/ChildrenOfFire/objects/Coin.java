package nl.han.ica.ChildrenOfFire.objects;

import nl.han.ica.ChildrenOfFire.ChildrenOfFire;
import nl.han.ica.ChildrenOfFire.interfaces.IPickupable;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * <h3>Coin<h3>
 * 
 * Simple implementation of a coin object
 * 
 * @see SpriteObject, IPickupable
 */
public class Coin extends SpriteObject implements IPickupable{
    private int value;

    /**
     * Basic constructor
     * 
     * @param int value - value of the coin
     */
    public Coin(int value) {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/coin.png"));
        this.value = value;
    }

    /**
     * This function will be called every frame
     */
    public void update() {
        
    }

    /**
     * Get the value of the coin object 
     * 
     * @return int value - value of the coin
     */
    public int getValue() {
        return value;
    }

    /**
     * What will happen when a player picks up this coin
     */
    @Override
    public void pickUp(Player player, ChildrenOfFire world) {
        player.increaseScore(value);
        world.deleteGameObject(this);
    }
}