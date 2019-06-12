package com.pixolestudios.procgen;

import main.java.com.pixolestudios.exceptions.UninitializedMapException;
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
        map.setInstantiated(true);
        return map;
    }

    @Test
    public void assertDryMapGenerateHasNoWater() throws UninitializedMapException {
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
        Assert.assertEquals(xSize, map.getMap().length);
        Assert.assertEquals(ySize, map.getMap()[0].length);
    }

    @Test
    public void assertMapSizeGettersEqualsValuesSet() {
        int xSize = 30, ySize = 15;
        MapGrid map = new MapGrid(30, 15);
        Assert.assertEquals(xSize, map.getXSize());
        Assert.assertEquals(ySize, map.getYSize());
    }

    //TODO Update test, may need to create override functions to check equality of points
    @Test
    public void assertMapPointGetterReturnsCorrectPoint() throws UninitializedMapException {
        MapGrid map = generateFixedMapGrid();
        Assert.assertEquals("l", map.getPointAtLoc(1, 1).getType());
        Assert.assertEquals(100, map.getPointAtLoc(1, 1).getHeight(), 0);
    }

    @Test
    public void uninstantiatedMapReturnsUninstantiated() {
        MapGrid map = new MapGrid(2, 2);
        Assert.assertFalse("Expected map not instantiated", map.isInstantiated());
    }

    @Test
    public void instantiatedMapReturnsInstantiated() {
        MapGrid map = new MapGrid(2, 2);
        map.InitialGenerateDryMap(1, 100);
        Assert.assertTrue("Expectte map instantiated", map.isInstantiated());
    }

    @Test
    public void floodUninstantiatedMapThrowsException() {
        MapGrid map = new MapGrid(1, 1);
        try {
            map.FloodMap(1);
        } catch (Exception e) {
            Assert.assertTrue("Expected main.java.com.pixolestudios.exceptions.UninitializedMapException but got " + e.getClass().getName(), e.getClass() == UninitializedMapException.class);
        }
    }

    @Test
    public void removeNoiseUninstantiatedMapThrowsException() {
        MapGrid map = new MapGrid(1, 1);
        try {
            map.RemoveTerrainNoise(1, 1, 1);
        } catch (Exception e) {
            Assert.assertTrue("Expected main.java.com.pixolestudios.exceptions.UninitializedMapException but got " + e.getClass().getName(), e.getClass() == UninitializedMapException.class);
        }
    }

    @Test
    public void addBeachesUninstantiatedMapThrowsException() {
        MapGrid map = new MapGrid(1, 1);
        try {
            map.AddBeaches(1, 1, 1);
        } catch (Exception e) {
            Assert.assertTrue("Expected main.java.com.pixolestudios.exceptions.UninitializedMapException but got " + e.getClass().getName(), e.getClass() == UninitializedMapException.class);
        }
    }

    @Test
    public void SmoothHeightsUninstantiatedMapThrowsException() {
        MapGrid map = new MapGrid(1, 1);
        try {
            map.BasicSmoothHeightMap(1);
        } catch (Exception e) {
            Assert.assertTrue("Expected main.java.com.pixolestudios.exceptions.UninitializedMapException but got " + e.getClass().getName(), e.getClass() == UninitializedMapException.class);
        }
    }

    @Test
    public void getPointUninstantiatedMapThrowsException() {
        MapGrid map = new MapGrid(1, 1);
        try {
            map.getPointAtLoc(1, 1);
        } catch (Exception e) {
            Assert.assertTrue("Expected main.java.com.pixolestudios.exceptions.UninitializedMapException but got " + e.getClass().getName(), e.getClass() == UninitializedMapException.class);
        }
    }

    @Test
    public void mapToStringReturnsExpectedString() throws UninitializedMapException {
        MapGrid map = new MapGrid(3, 2);
        map.InitialGenerateDryMap(20, 20);

        Assert.assertEquals("l:20.0\t\tl:20.0\t\tl:20.0\t\t\n" +
                "l:20.0\t\tl:20.0\t\tl:20.0\t\t\n", map.toString());

        map.FloodMap(21);
        Assert.assertEquals("w:21.0\t\tw:21.0\t\tw:21.0\t\t\n" +
                "w:21.0\t\tw:21.0\t\tw:21.0\t\t\n", map.toString());
    }

    @Test
    public void floodMapAboveMaxHeightFillsWithWater() throws UninitializedMapException {
        MapGrid map = new MapGrid(xSizeOfGrid, ySizeOfGrid);
        map.InitialGenerateDryMap(20, 20);
        map.FloodMap(21);

        for (int x = 0; x < xSizeOfGrid; x++) {
            for (int y = 0; y < ySizeOfGrid; y++) {
                Assert.assertEquals("w", map.getPointAtLoc(x, y).getType());
            }
        }
    }

    @Test
    public void generateMap0PercentWaterIsDry() throws UninitializedMapException {
        MapGrid map = new MapGrid(xSizeOfGrid, ySizeOfGrid);
        map.InitialGenerateMap(0.0f, 20, 20);
        for (int x = 0; x < xSizeOfGrid; x++) {
            for (int y = 0; y < ySizeOfGrid; y++) {
                Assert.assertEquals("l", map.getPointAtLoc(x, y).getType());
            }
        }
    }

    @Test
    public void generateMap100PercentWaterIsWet() throws UninitializedMapException {
        MapGrid map = new MapGrid(xSizeOfGrid, ySizeOfGrid);
        map.InitialGenerateMap(100.0f, 20, 20);
        for (int x = 0; x < xSizeOfGrid; x++) {
            for (int y = 0; y < ySizeOfGrid; y++) {
                Assert.assertEquals("w", map.getPointAtLoc(x, y).getType());
            }
        }
    }
}