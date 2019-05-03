import java.util.Random;

public class MapGrid {

    private int xSize, ySize;
    private GridPoint[][] gridPoints;

    MapGrid(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = new GridPoint[xSize][ySize];
    }

    MapGrid(GridPoint[][] map, int xSize, int ySize){
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = map;
    }

    /**
     * Initial generation of the map, each grid point is randomly assigned water/land and height regardless of surrounding points
     *
     * @param waterPercent    approximate percentage of the map to be water
     * @param heightVariation approximate aferage difference between lowest point and highest
     */
    public void InitialGenerateMap(float waterPercent, float minHeight, float maxHeight) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                gridPoints[i][j] = new GridPoint(GenerateTerrainType(waterPercent), GenerateTerrainHeight(minHeight, maxHeight));
            }
        }
    }

    /**
     * Makes the height of each grid point an average of its neigbours
     */
    public GridPoint[][] BasicSmoothHeightMap(){
        GridPoint[][] smoothMap = gridPoints;
        float avgHeight;
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                avgHeight = averageSurroundingPoints(smoothMap, i, j);
                smoothMap[i][j].setHeight(avgHeight);
            }
        }
        return smoothMap;
    }

    private float averageSurroundingPoints(GridPoint[][] map, int xpos, int ypos) {
        float avg = 1;
        int numPointsChecked = 0;
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (pointInBounds(i + xpos,j + ypos)){
                    numPointsChecked ++;
                    avg += map[i + xpos][j + ypos].getHeight();
                }
            }
        }
        avg = avg / numPointsChecked;
        return avg;
    }

    private boolean pointInBounds(int x, int y) {
        if ((x < 0 || x >= xSize) || (y < 0 || y >= ySize)){
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
        if (r.nextFloat() > percentWater){
            return "l";
        } else {
            return "w";
        }
    }

    private float GenerateTerrainHeight(float max, float min) {
        Random r = new Random();
        return min + r.nextFloat() * (max - min);
    }

    private void SmoothMapHeights(float maxDiff, float avgDiff) {

    }

    private void SmoothMapTerrainTypes() {

    }


    public GridPoint getPointAtLoc(int x, int y){
        return gridPoints[x][y];
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
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
