import java.util.Random;

public class MapGrid {

    private int xSize, ySize;
    private GridPoint[][] gridPoints;

    MapGrid(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = new GridPoint[xSize][ySize];
    }

    MapGrid(GridPoint[][] map, int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = map;
    }

    /**
     * Initial generation of the map, each grid point is randomly assigned water/land and height regardless of surrounding points
     *
     * @param waterPercent approximate percentage of the map to be water
     */
    public void InitialGenerateMap(float waterPercent, float minHeight, float maxHeight) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                gridPoints[i][j] = new GridPoint(GenerateTerrainType(waterPercent), GenerateTerrainHeight(minHeight, maxHeight));
            }
        }
    }

    public void InitialGenerateDryMap(float minHeight, float maxHeight) {
        InitialGenerateMap(0, minHeight, maxHeight);
    }

    public void FloodMap(float waterLevel) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (gridPoints[i][j].getHeight() <= waterLevel) {
                    gridPoints[i][j].setHeight(waterLevel);
                    gridPoints[i][j].setType("w");
                }
            }
        }
    }

    public void RemoveTerrainNoise(int iterations, int threshold) {
        GridPoint[][] tempMap = copyMap(gridPoints);
        for (int count = 0; count < iterations; count++) {
            tempMap = copyMap(gridPoints);
            for (int i = 0; i < xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    if (noTerrainTilesSurroundingPoints(gridPoints, i, j, "l") >= threshold) {
                        tempMap[i][j].setType("l");
                    } else {
                        tempMap[i][j].setType("w");
                    }
                }
            }
            gridPoints = copyMap(tempMap);
        }
    }

    private int noTerrainTilesSurroundingPoints(GridPoint[][] map, int xpos, int ypos, String terrainType) {
        int numTerrainTiles = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
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
    public void BasicSmoothHeightMap() {
        GridPoint[][] smoothMap = gridPoints;
        float avgHeight;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                if (!smoothMap[i][j].getType().equals("w")){
                    avgHeight = averageHeightSurroundingPoints(smoothMap, i, j);
                    smoothMap[i][j].setHeight(avgHeight);
                }
            }
        }
        gridPoints = smoothMap;
    }

    private float averageHeightSurroundingPoints(GridPoint[][] map, int xpos, int ypos) {
        float avg = 1;
        int numPointsChecked = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
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

    /**
     * Tidies up the map to make it more natural after the random height and terrain generation
     */
    public void SmoothMap(float maxAdjacentHeightDiff, float avgAdjacentHeightDiff) {
        SmoothMapTerrainTypes();
        SmoothMapHeights(maxAdjacentHeightDiff, avgAdjacentHeightDiff);
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

    private void SmoothMapHeights(float maxDiff, float avgDiff) {

    }

    private void SmoothMapTerrainTypes() {

    }

    /**
     * Returns the point at the location given by x and y coordinates starting at 0,0
     *
     * @param x
     * @param y
     * @return
     */
    public GridPoint getPointAtLoc(int x, int y) {
        return gridPoints[x][y];
    }

    /**
     * Returns number of grid points in x direction
     *
     * @return
     */
    public int getXSize() {
        return xSize;
    }

    /**
     * Returns number of grid points in y direction
     *
     * @return
     */
    public int getYSize() {
        return ySize;
    }

    private GridPoint[][] copyMap(GridPoint[][] mapToCopy){
        GridPoint[][] newMap = new GridPoint[xSize][ySize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                newMap[j][i] = new GridPoint(mapToCopy[j][i].getType(),mapToCopy[j][i].getHeight());
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
