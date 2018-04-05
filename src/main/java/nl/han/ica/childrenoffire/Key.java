package nl.han.ica.childrenoffire;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

public class Key extends SpriteObject implements IPickupable{
    public Key() {
        super(new Sprite("src/main/java/nl/han/ica/childrenoffire/files/objectsprites/key.png"));
    }

    public void update() {
        
    }

    @Override
    public void pickUp(Player player, ChildrenOfFire world) {
        player.increaseKeys(1);
        world.deleteGameObject(this);
    }
}