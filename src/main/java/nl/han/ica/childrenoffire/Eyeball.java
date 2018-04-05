package nl.han.ica.childrenoffire;

public class Eyeball extends Enemy {
    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * @param int posX - Starting X position
     * @param int posY - Starting Y position
     */
    public Eyeball(ChildrenOfFire world, int posX, int posY) {
        this(world, posX, posY, 50);
    }

    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * @param int posX - Starting X position
     * @param int posY - Starting Y position
     * @param health - Amount of health this enemy has
     */
    public Eyeball(ChildrenOfFire world, int posX, int posY, int health) {
        this(world, "src/main/java/nl/han/ica/childrenoffire/files/objectsprites/eyeball.png", posX, posY, health);
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
    private Eyeball(ChildrenOfFire world, String path, int posX, int posY, int health) {
        super(world, path, posX, posY, health, true);
    }

    

    @Override
    public void dropItem() {
        Key key = new Key();
        getWorld().addGameObject(key);

        key.setX(this.getX());
        key.setY(this.getY());
    }
}