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

    public String getType(){
        return type;
    }

    public void setHeight(float newVal){
        height = newVal;
    }
}
