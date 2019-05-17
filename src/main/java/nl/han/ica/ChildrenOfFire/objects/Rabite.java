package nl.han.ica.ChildrenOfFire.objects;

import nl.han.ica.ChildrenOfFire.ChildrenOfFire;

/**
 * <h3>Rabite</h3>
 * 
 * Rabite class wich is an extension of the enemy class.
 * 
 * @see Enemy
 */
public class Rabite extends Enemy {    
    /**
     * Basic constructor for a rabite object 
     * 
     * @param world - Reference to the world class
     * @param int posX - Starting X position
     * @param int posY - Starting Y position
     */
    public Rabite(ChildrenOfFire world, int posX, int posY) {
        this(world, posX, posY, 10);
    }

    /**
     * Constructor
     * 
     * @param world - Reference to the world class
     * @param int posX - Starting X position
     * @param int posY - Starting Y position
     * @param health - Amount of health this enemy has
     */
    public Rabite(ChildrenOfFire world, int posX, int posY, int health) {
        this(world, "src/main/java/nl/han/ica/childrenoffire/files/objectsprites/rabite.png", posX, posY, health);
    }

    /**
     * Private Constructor
     * 
     * @param world - Reference to the world object
     * @param path - Path to the sprite object
     * @param int posX - Starting X position
     * @param int posY - Starting Y position
     * @param health - Amount of health this enemy has
     */
    private Rabite(ChildrenOfFire world, String path, int posX, int posY, int health) {
        super(world, path, posX, posY, health);
    }

    /**
     * Function that will be called whenever this object has to drop its coin
     */
    @Override
    public void dropItem() {
        Coin coin = new Coin(1);
        getWorld().addGameObject(coin);

        coin.setX(this.getX());
        coin.setY(this.getY());
    }
}