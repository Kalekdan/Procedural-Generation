package com.pixolestudios.procgen;

import main.java.com.pixolestudios.procgen.GridPoint;
import main.java.com.pixolestudios.procgen.MapGrid;
import org.junit.Assert;
import org.junit.Test;

public class MapGridTest {

    private final int xSizeOfGrid = 150;
    private final int ySizeOfGrid = 100;

    //TODO: create a constant "random" map
    private MapGrid generateFixedMapGrid() {
        //Manually create a fixed map grid
        GridPoint[][] gridPoints = new GridPoint[xSizeOfGrid][ySizeOfGrid];
        for (int x = 0; x < xSizeOfGrid; x++) {
            for (int y = 0; y < ySizeOfGrid; y++) {
                gridPoints[x][y] = new GridPoint("l", 100);
            }
        }
        MapGrid map = new MapGrid(gridPoints, xSizeOfGrid, ySizeOfGrid);
        return map;
    }

    @Test
    public void assertDryMapGenerateHasNoWater() {
        boolean waterFound = false;
        int xSize = 40, ySize = 30;
        int countOfWater = 0;

        MapGrid dryMap = new MapGrid(xSize, ySize);
        dryMap.InitialGenerateDryMap(1, 255);

        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (dryMap.getPointAtLoc(x, y).getType().equals("w")) {
                    waterFound = true;
                    countOfWater++;
                }
            }
        }
        Assert.assertFalse("Expected no water. Found " + countOfWater + " water tiles", waterFound);
    }

    @Test
    public void assertMapSizeEqualsValuesSet() {
        int xSize = 30, ySize = 15;
        MapGrid map = new MapGrid(30, 15);
        Assert.assertEquals(map.getMap().length, xSize);
        Assert.assertEquals(map.getMap()[0].length, ySize);
    }

    @Test
    public void assertMapSizeGettersEqualsValuesSet() {
        int xSize = 30, ySize = 15;
        MapGrid map = new MapGrid(30, 15);
        Assert.assertEquals(map.getXSize(), xSize);
        Assert.assertEquals(map.getYSize(), ySize);
    }

    //TODO Update test, may need to create override functions to check equality of points
    @Test
    public void assertMapPointGetterReturnsCorrectPoint(){
        MapGrid map = generateFixedMapGrid();
        Assert.assertEquals(map.getPointAtLoc(1,1).getType(), "l");
        Assert.assertEquals(map.getPointAtLoc(1,1).getHeight(), 100, 0);
    }
}