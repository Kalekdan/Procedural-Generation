public class GridPoint {
    private String type;
    private float height;

    GridPoint(String terrainType, float terrainHeight){
        type = terrainType;
        height = terrainHeight;
    }

    @Override
    public String toString() {
        return type + ":" + height;
    }
}
