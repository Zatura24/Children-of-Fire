package nl.han.ica.childrenoffire;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Coin extends SpriteObject{
    private int value;

    public Coin(int value) {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/coin.png"));
        this.value = value;
    }

    public void update() {
        
    }

    public int getValue() {
        return value;
    }

    public void pickUp(Player player, ChildrenOfFire world) {
        player.increaseScore(value);
        world.deleteGameObject(this);
    }
}