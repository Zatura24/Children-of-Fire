package nl.han.ica.childrenoffire;

import java.util.ArrayList;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Objects.Sprite;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
import nl.han.ica.OOPDProcessingEngineHAN.View.View;
import nl.han.ica.childrenoffire.tiles.*;
import processing.core.PApplet;

/**
 * <h1>Children of Fire</h1>
 * an oopg adventure game
 * <p>
 * <b>Note:</b> This game is made for the for school purposes only
 * 
 * @author Jannick Joosten
 * @author Max van Essen
 * @version 0.0.1
 * @since 2018-03-30
 */
@SuppressWarnings("serial")
public class ChildrenOfFire extends GameEngine {
    private String[] tilemapList = {
        "main/java/nl/han/ica/childrenoffire/files/tilemaps/tilemap-1.txt"
    };
    private int currentTileMap = 0; // default

    public static void main(String[] args) {
        PApplet.main(new String[] { "nl.han.ica.childrenoffire.ChildrenOfFire" });
    }

    /**
     * setup of the whole game
     */
    @Override
    public void setupGame() {
        int worldWidth = 1400;
        int worldHeight = 800;

        initializeTileMap();

        createViewWithoutViewport(worldWidth, worldHeight);
    }

    /**
     * this function will be called every frame
     */
    @Override
    public void update() {

    }

    /**
     * Create view without viewport
     * @param int screenWidth - Width of the screen
     * @param int screenHeight - Height of the screen
     */
    private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth,screenHeight);

        setView(view);
        size(screenWidth, screenHeight);
    }

    /**
     * This function will initialize the whole tile map
     * 
     * @see TileMap
     */
    private void initializeTileMap() {
        int tileSize = 32;
        TileType tileTypes[] = initializeTileTypes();
        int indexMap[][] = loadIndexMapFromFile(tilemapList[currentTileMap], 44, 25);
        tileMap = new TileMap(tileSize, tileTypes, indexMap);
    }

    /**
     * This function will initialize all tile types
     * <p>
     * Note: that for every tile type there needs to excist an class file
     * which extends the Tile class
     * 
     * 
     * @return returns a new TileType[] containing all tile types
     * @see TileType
     */
    private TileType[] initializeTileTypes() {
        ArrayList<TileType> tileTypeList = new ArrayList<>();

        String path = "src/main/java/nl/han/ica/childrenoffire/files/tilesprites/";

        Sprite groundSprite = new Sprite(path + "ground.png");
        Sprite wallTopSprite = new Sprite(path + "wall-top.png");
        Sprite wallSideSprite = new Sprite(path + "wall-side.png");
        Sprite spawnSprite = new Sprite(path + "spawn.png");
        Sprite stairsSprite = new Sprite(path + "stairs.png");
        
        tileTypeList.add(new TileType<>(GroundTile.class, groundSprite));
        tileTypeList.add(new TileType<>(WallTile.class, wallTopSprite));
        tileTypeList.add(new TileType<>(WallTile.class, wallSideSprite));
        tileTypeList.add(new TileType<>(SpawnTile.class, spawnSprite));
        tileTypeList.add(new TileType<>(StairsTile.class, stairsSprite));

        return tileTypeList.toArray(new TileType[tileTypeList.size()]);
    }

    /**
     * This function will load an index map from a text file.
     * <p>
     * Note: that in the file every index must be 3 characters long,
     * i.e. " 0 " or "-1  " (the whitespaces are also count as characters).
     * 
     * @param String path - This is the file path to the index map text file
     * @param int width - This is the width of the index map
     * @param int height - This is the height of the index map
     * @return int[][] indexMap - This will return the index map
     */
    public int[][] loadIndexMapFromFile(String path, int width, int height) {
        IPersistence indexMapPersistence = new FilePersistence(path);
        String fileToStringList[];
        
        int indexMap[][] = new int[height][width];

        if (indexMapPersistence.fileExists()) {
            fileToStringList = indexMapPersistence.loadDataStringArray("\\n");
            
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    String valueAsString = fileToStringList[row].substring((col*3), ((col*3)+3));
                    indexMap[row][col] = Integer.parseInt(valueAsString.replaceAll("\\s+", ""));
                }
            }
        }

        return indexMap;
    }
}