package nl.han.ica.ChildrenOfFire;

import nl.han.ica.OOPDProcessingEngineHAN.Engine.GameEngine;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.FilePersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Persistence.IPersistence;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.Tile;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileMap;
import nl.han.ica.OOPDProcessingEngineHAN.Tile.TileType;
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
        "main/java/nl/han/ica/ChildrenOfFire/Tilemaps/tilemap-1.txt"
    };
    private int currentTileMap = 0; // default

    public static void main(String[] args) {
        PApplet.main(new String[] { "nl.han.ica.ChildrenOfFire.ChildrenOfFire" });
    }

    /**
     * setup of the whole game
     */
    @Override
    public void setupGame() {
        initializeTileMap();
    }

    /**
     * this function will be called every frame
     */
    @Override
    public void update() {

    }

    /**
     * This function will initialize the whole tile map
     * 
     * @see TileMap
     */
    private void initializeTileMap() {
        int tileSize = 32;
        TileType tileTypes[] = initializeTileTypes();
        int indexMap[][] = loadIndexMapFromFile(tilemapList[currentTileMap], 43, 25);
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
     * @see Tile, TileType
     */
    private TileType[] initializeTileTypes() {
        return new TileType[] {};
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
        
        int indexMap[][] = new int[width][height];

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