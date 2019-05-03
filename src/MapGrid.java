import java.util.Random;

public class MapGrid {

    private int xSize, ySize;
    private GridPoint[][] gridPoints;

    MapGrid(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        gridPoints = new GridPoint[xSize][ySize];
    }

    /**
     * Initial generation of the map, each grid point is randomly assigned water/land and height regardless of surrounding points
     *
     * @param waterPercent    approximate percentage of the map to be water
     * @param heightVariation approximate aferage difference between lowest point and highest
     */
    public void InitialGenerateMap(float waterPercent, float heightVariation) {
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                gridPoints[i][j] = new GridPoint(GenerateTerrainType(waterPercent), GenerateTerrainHeight());
            }
        }
    }

    /**
     * Tidies up the map to make it more natural after the random height and terrain generation
     */
    public void SmoothMap() {
        SmoothMapTerrainTypes();
        SmoothMapHeights();
    }

    private String GenerateTerrainType(float percentWater) {
        Random r = new Random();
        if (r.nextFloat() > percentWater){
            return "land";
        } else {
            return "water";
        }
    }

    private float GenerateTerrainHeight() {
        return 0f;
    }

    private void SmoothMapHeights() {

    }

    private void SmoothMapTerrainTypes() {

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
                toReturn += gridPoints[j][i].toString() + "\t";
            }
            toReturn += "\n";
        }
        return toReturn;
    }
}
