package nl.han.ica.childrenoffire;

import java.util.Arrays;

import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.SpriteObject;

/**
 * Enemy
 */
public class Enemy extends SpriteObject {

    private ChildrenOfFire world;

    /**
     * Constructor
     * 
     * @param world - Reference to the world object
     * @param path - Path to the sprite object
     */
    public Enemy(ChildrenOfFire world, String path) {
        super(new Sprite(path));
        this.world = world;
    }

    @Override
    public void update() {
        int map[][] = getEnemySurrounding();
        int dir = (int) (Math.random() * ((4 - 1) + 1) + 1);

        switch (dir) {
        case 1:
            if (map[0][1] != 0) {
                break;
            }

            setY(getY() - world.getTileMap().getTileSize());

            break;
        case 2:
            if (map[1][2] != 0) {
                break;
            }

            setX(getX() + world.getTileMap().getTileSize());

            break;
        case 3:
            if (map[2][1] != 0) {
                break;
            }

            setY(getY() + world.getTileMap().getTileSize());
            break;
        case 4:
            if (map[1][0] != 0) {
                break;
            }

            setX(getX() - world.getTileMap().getTileSize());
            break;
        default:
            break;
        }
    }

    private int[][] getEnemySurrounding() {
        int map[][] = new int[3][3];

        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                map[row + 1][col + 1] = world.getTileMap().findTileTypeIndex(
                        world.getTileMap().getTileOnPosition((int) (getX() + (col * 33)), (int) (getY() + (row * 33))));
            }
        }

        return map;
    }
}