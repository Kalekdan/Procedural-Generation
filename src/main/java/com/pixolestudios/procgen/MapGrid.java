package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.logUtils.LoggingLevel;

import java.util.Random;

import static main.java.com.pixolestudios.procgen.MapGenMain.logger;

public class MapGrid {

    private int xSize, ySize;
    private GridPoint[][] gridPoints;
    private float waterHeightLevel;

    public MapGrid(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = new GridPoint[xSize][ySize];
    }

    public MapGrid(GridPoint[][] map, int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = map;
    }

    /**
     * Initial generation of the map, each grid point is randomly assigned water/land and height regardless of surrounding points
     * @param waterPercent approximate percentage of the map to be water
     * @param minHeight the minimum terrain height value
     * @param maxHeight the maximum terrain height value
     */
    public void InitialGenerateMap(float waterPercent, float minHeight, float maxHeight) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                gridPoints[i][j] = new GridPoint(GenerateTerrainType(waterPercent), GenerateTerrainHeight(minHeight, maxHeight));
            }
        }
    }

    /**
     * Generates a map with no water with the heights randomly assigned values between min and max
     * @param minHeight the minimum terrain height value
     * @param maxHeight the maximum terrain height value
     */
    public void InitialGenerateDryMap(float minHeight, float maxHeight) {
        InitialGenerateMap(0, minHeight, maxHeight);
    }

    /**
     * Adds water to the map at a level given, turning all terrain types below water level to water
     * @param waterLevel level at which to flood the map to
     */
    public void FloodMap(float waterLevel) {
        waterHeightLevel = waterLevel;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (gridPoints[i][j].getHeight() <= waterHeightLevel) {
                    gridPoints[i][j].setHeight(waterHeightLevel);
                    gridPoints[i][j].setType("w");
                }
            }
        }
    }

    /**
     * Adds beaches to the edge of the terrain
     * @param iterations number of times to iterate through turning terrain into beaches
     * @param threshold the minimum number of adjacent water tiles required for terrain to be considered beach
     */
    public void AddBeaches(int iterations, int threshold, int squareSizeToCompare) {
        GridPoint[][] tempMap = copyMap(gridPoints);
        for (int count = 0; count < iterations; count++) {
            logger.log("Beach adding - Iteration " + (count+1) + "/" + iterations, LoggingLevel.CONFIG);
            tempMap = copyMap(gridPoints);
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    if (noTerrainTilesSurroundingPoints(gridPoints, i, j, "w", squareSizeToCompare) >= threshold && gridPoints[i][j].getType().equals("l")) {
                        tempMap[i][j].setType("b");
                    } else {
                        tempMap[i][j].setType(gridPoints[i][j].getType());
                    }
                }
            }
            gridPoints = copyMap(tempMap);
        }
    }

    /**
     * Smooths terrain formations and clusters terrain types together
     * @param iterations number of times to iterate through smoothing algorithm
     * @param threshold minumum number of adjacent land tiles for a tile to be turned into a land tile
     */
    public void RemoveTerrainNoise(int iterations, int threshold, int squareSizeToCompare) {
        GridPoint[][] tempMap = copyMap(gridPoints);
        for (int count = 0; count < iterations; count++) {
            logger.log("Noise Reduction - Iteration " + (count+1) + "/" + iterations, LoggingLevel.CONFIG);
            tempMap = copyMap(gridPoints);
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    if (noTerrainTilesSurroundingPoints(gridPoints, i, j, "l", squareSizeToCompare) >= threshold) {
                        tempMap[i][j].setType("l");
                    } else {
                        tempMap[i][j].setType("w");
                        tempMap[i][j].setHeight(waterHeightLevel);
                    }
                }
            }
            gridPoints = copyMap(tempMap);
        }
    }

    private int noTerrainTilesSurroundingPoints(GridPoint[][] map, int xpos, int ypos, String terrainType, int squareSizeToCompare) {
        int numTerrainTiles = 0;
        for (int i = -squareSizeToCompare; i <= squareSizeToCompare; i++) {
            for (int j = -squareSizeToCompare; j <= squareSizeToCompare; j++) {
                if (pointInBounds(i + xpos, j + ypos)) {
                    if ((terrainType.equals(map[i + xpos][j + ypos].getType()))) {
                        numTerrainTiles++;
                    }
                }
            }
        }
        return numTerrainTiles;
    }

    /**
     * Makes the height of each grid point an average of its neigbours
     */
    public void BasicSmoothHeightMap(int squareSizeToCompare) {
        GridPoint[][] smoothMap = copyMap(gridPoints);
        float avgHeight;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (!smoothMap[i][j].getType().equals("w")) {
                    avgHeight = averageHeightSurroundingPoints(smoothMap, i, j, squareSizeToCompare);
                    smoothMap[i][j].setHeight(avgHeight);
                }
            }
        }
        gridPoints = smoothMap;
    }

    private float averageHeightSurroundingPoints(GridPoint[][] map, int xpos, int ypos, int squareSizeToCompare) {
        float avg = 1;
        int numPointsChecked = 0;
        for (int i = -squareSizeToCompare; i <= squareSizeToCompare; i++) {
            for (int j = -squareSizeToCompare; j <= squareSizeToCompare; j++) {
                if (pointInBounds(i + xpos, j + ypos)) {
                    numPointsChecked++;
                    avg += map[i + xpos][j + ypos].getHeight();
                }
            }
        }
        avg = avg / numPointsChecked;
        return avg;
    }

    private boolean pointInBounds(int x, int y) {
        if ((x < 0 || x >= xSize) || (y < 0 || y >= ySize)) {
            return false;
        }
        return true;
    }

    private String GenerateTerrainType(float percentWater) {
        Random r = new Random();
        if (r.nextFloat() > percentWater) {
            return "l";
        } else {
            return "w";
        }
    }

    private float GenerateTerrainHeight(float min, float max) {
        Random r = new Random();
        return min + (r.nextFloat() * (max - min));
    }

    /**
     * Returns the point at the location given by x and y coordinates starting at 0,0
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @return point at location (x,y)
     */
    public GridPoint getPointAtLoc(int x, int y) {
        return gridPoints[x][y];
    }

    /**
     * Returns number of grid points in x direction
     * @return x size of grid
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * Returns number of grid points in y direction
     * @return y size of grid
     */
    public int getYSize() {
        return ySize;
    }

    public GridPoint[][] getMap(){
        return gridPoints;
    }

    private GridPoint[][] copyMap(GridPoint[][] mapToCopy) {
        GridPoint[][] newMap = new GridPoint[xSize][ySize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                newMap[j][i] = new GridPoint(mapToCopy[j][i].getType(), mapToCopy[j][i].getHeight());
            }
        }

        return newMap;
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                toReturn += gridPoints[j][i].toString() + "\t\t";
            }
            toReturn += "\n";
        }
        return toReturn;
    }
}
