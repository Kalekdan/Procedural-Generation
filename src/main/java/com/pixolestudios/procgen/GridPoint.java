package main.java.com.pixolestudios.procgen;

public class GridPoint {
    private String type;
    private float height;

    /**
     * Individual Grid point on the map
     * @param terrainType type terrain (water,land etc)
     * @param terrainHeight height of terrain
     */
    public GridPoint(String terrainType, float terrainHeight){
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

    public float getHeight(){
        return height;
    }

    /**
     * Sets the height to the value provided
     * @param newHeight the value to set the new height to
     */
    public void setHeight(float newHeight){
        height = newHeight;
    }

    public void setType(String newType){
        type = newType;
    }
}
