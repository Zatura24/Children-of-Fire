package nl.han.ica.childrenoffire;

public class Rabite extends Enemy {
    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     */
    public Rabite(ChildrenOfFire world) {
        this(world, "src/main/java/nl/han/ica/childrenoffire/files/objectsprites/rabite.png", 10);
    }

    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * @param health - Amount of health this enemy has
     */
    public Rabite(ChildrenOfFire world, int health) {
        this(world, "src/main/java/nl/han/ica/childrenoffire/files/objectsprites/rabite.png", health);
    }

    /**
     * Private Constructor
     * 
     * @param world - Reference to the world object
     * @param path - Path to the sprite object
     * @param health - Amount of health this enemy has
     */
    private Rabite(ChildrenOfFire world, String path, int health) {
        super(world, path, health);
    }

    @Override
    public void dropItem() {
        Coin coin = new Coin();
        getWorld().addGameObject(coin);

        coin.setX(this.getX());
        coin.setY(this.getY());
    }
}