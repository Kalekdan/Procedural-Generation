package com.pixolestudios.procgen;

import main.java.com.pixolestudios.exceptions.UninitializedMapException;
import main.java.com.pixolestudios.procgen.GridPoint;
import main.java.com.pixolestudios.procgen.MapGrid;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MapGridTest {

    private final int xSizeOfGrid = 150;
    private final int ySizeOfGrid = 100;

    private final int xSizeSmall = 20;
    private final int ySizeSmall = 15;

    //TODO: create a constant "random" map
    private MapGrid generateFixedMapGrid() {
        //<editor-fold desc="Height Array">
        float[][] heightArr = {{41.5f, 26.5f, 180.5f, 155.5f, 77.5f, 166.5f, 25.5f, 31.5f, 196.5f, 107.5f, 15.5f, 238.5f, 58.5f, 126.5f, 74.5f, 153.5f, 22.5f, 68.5f, 171.5f, 234.5f},
                {55.5f, 59.5f, 187.5f, 64.5f, 170.5f, 17.5f, 101.5f, 191.5f, 236.5f, 167.5f, 68.5f, 80.5f, 87.5f, 73.5f, 249.5f, 230.5f, 175.5f, 101.5f, 137.5f, 76.5f},
                {107.5f, 248.5f, 83.5f, 44.5f, 173.5f, 205.5f, 227.5f, 73.5f, 109.5f, 108.5f, 202.5f, 149.5f, 184.5f, 175.5f, 4.5f, 219.5f, 160.5f, 95.5f, 201.5f, 205.5f},
                {33.5f, 205.5f, 135.5f, 123.5f, 13.5f, 46.5f, 115.5f, 58.5f, 3.5f, 226.5f, 5.5f, 203.5f, 42.5f, 204.5f, 95.5f, 227.5f, 60.5f, 248.5f, 121.5f, 193.5f},
                {158.5f, 251.5f, 26.5f, 155.5f, 49.5f, 14.5f, 246.5f, 28.5f, 124.5f, 119.5f, 55.5f, 242.5f, 241.5f, 109.5f, 166.5f, 93.5f, 204.5f, 109.5f, 72.5f, 57.5f},
                {121.5f, 167.5f, 99.5f, 111.5f, 89.5f, 215.5f, 146.5f, 145.5f, 151.5f, 235.5f, 107.5f, 29.5f, 229.5f, 66.5f, 81.5f, 195.5f, 172.5f, 87.5f, 142.5f, 25.5f},
                {205.5f, 157.5f, 28.5f, 212.5f, 50.5f, 144.5f, 125.5f, 200.5f, 83.5f, 62.5f, 31.5f, 99.5f, 209.5f, 206.5f, 65.5f, 221.5f, 155.5f, 27.5f, 18.5f, 254.5f},
                {10.5f, 105.5f, 118.5f, 22.5f, 143.5f, 202.5f, 223.5f, 120.5f, 251.5f, 127.5f, 250.5f, 165.5f, 104.5f, 3.5f, 167.5f, 138.5f, 27.5f, 141.5f, 180.5f, 87.5f},
                {42.5f, 35.5f, 73.5f, 193.5f, 133.5f, 198.5f, 251.5f, 189.5f, 53.5f, 242.5f, 232.5f, 48.5f, 148.5f, 237.5f, 174.5f, 44.5f, 95.5f, 208.5f, 37.5f, 121.5f},
                {66.5f, 142.5f, 19.5f, 156.5f, 58.5f, 141.5f, 13.5f, 218.5f, 57.5f, 52.5f, 152.5f, 107.5f, 7.5f, 112.5f, 16.5f, 182.5f, 187.5f, 66.5f, 73.5f, 103.5f},
                {65.5f, 115.5f, 116.5f, 47.5f, 78.5f, 200.5f, 224.5f, 49.5f, 202.5f, 181.5f, 178.5f, 13.5f, 44.5f, 186.5f, 193.5f, 200.5f, 237.5f, 136.5f, 107.5f, 232.5f},
                {24.5f, 146.5f, 165.5f, 102.5f, 98.5f, 53.5f, 94.5f, 190.5f, 184.5f, 230.5f, 21.5f, 179.5f, 175.5f, 116.5f, 127.5f, 221.5f, 232.5f, 182.5f, 124.5f, 38.5f},
                {172.5f, 166.5f, 87.5f, 144.5f, 205.5f, 247.5f, 166.5f, 171.5f, 150.5f, 252.5f, 28.5f, 9.5f, 139.5f, 67.5f, 57.5f, 56.5f, 183.5f, 148.5f, 145.5f, 30.5f},
                {62.5f, 68.5f, 139.5f, 197.5f, 79.5f, 156.5f, 43.5f, 24.5f, 232.5f, 127.5f, 229.5f, 247.5f, 206.5f, 62.5f, 201.5f, 119.5f, 119.5f, 102.5f, 20.5f, 45.5f},
                {68.5f, 61.5f, 123.5f, 198.5f, 162.5f, 8.5f, 22.5f, 203.5f, 241.5f, 80.5f, 69.5f, 194.5f, 3.5f, 182.5f, 5.5f, 94.5f, 107.5f, 230.5f, 122.5f, 202.5f},
                {3.5f, 119.5f, 165.5f, 160.5f, 132.5f, 53.5f, 146.5f, 75.5f, 121.5f, 208.5f, 163.5f, 239.5f, 129.5f, 121.5f, 237.5f, 23.5f, 249.5f, 11.5f, 216.5f, 87.5f},
                {177.5f, 165.5f, 150.5f, 70.5f, 24.5f, 131.5f, 153.5f, 232.5f, 17.5f, 122.5f, 53.5f, 11.5f, 111.5f, 121.5f, 168.5f, 48.5f, 19.5f, 139.5f, 193.5f, 177.5f},
                {164.5f, 87.5f, 202.5f, 235.5f, 254.5f, 22.5f, 154.5f, 81.5f, 145.5f, 67.5f, 7.5f, 167.5f, 178.5f, 27.5f, 236.5f, 95.5f, 215.5f, 56.5f, 198.5f, 194.5f},
                {243.5f, 60.5f, 229.5f, 37.5f, 213.5f, 172.5f, 47.5f, 121.5f, 117.5f, 163.5f, 208.5f, 116.5f, 226.5f, 149.5f, 2.5f, 180.5f, 196.5f, 240.5f, 46.5f, 235.5f},
                {100.5f, 30.5f, 93.5f, 50.5f, 163.5f, 196.5f, 3.5f, 97.5f, 230.5f, 212.5f, 205.5f, 176.5f, 123.5f, 135.5f, 123.5f, 234.5f, 123.5f, 142.5f, 245.5f, 9.5f},
                {144.5f, 46.5f, 57.5f, 101.5f, 118.5f, 7.5f, 208.5f, 174.5f, 58.5f, 248.5f, 199.5f, 51.5f, 24.5f, 173.5f, 15.5f, 241.5f, 99.5f, 27.5f, 28.5f, 221.5f},
                {242.5f, 34.5f, 42.5f, 138.5f, 243.5f, 188.5f, 177.5f, 217.5f, 146.5f, 218.5f, 15.5f, 4.5f, 1.5f, 184.5f, 109.5f, 200.5f, 44.5f, 148.5f, 142.5f, 231.5f},
                {183.5f, 118.5f, 77.5f, 60.5f, 190.5f, 88.5f, 108.5f, 182.5f, 170.5f, 86.5f, 251.5f, 66.5f, 39.5f, 115.5f, 93.5f, 33.5f, 104.5f, 143.5f, 228.5f, 166.5f},
                {95.5f, 6.5f, 217.5f, 246.5f, 80.5f, 26.5f, 210.5f, 242.5f, 4.5f, 32.5f, 103.5f, 198.5f, 134.5f, 55.5f, 222.5f, 65.5f, 26.5f, 57.5f, 191.5f, 142.5f},
                {77.5f, 4.5f, 192.5f, 28.5f, 98.5f, 105.5f, 172.5f, 190.5f, 181.5f, 165.5f, 152.5f, 14.5f, 66.5f, 88.5f, 150.5f, 242.5f, 3.5f, 207.5f, 204.5f, 207.5f,}};
        //</editor-fold>

        //Manually create a fixed map grid
        GridPoint[][] gridPoints = new GridPoint[xSizeSmall][ySizeSmall];
        for (int x = 0; x < xSizeSmall; x++) {
            for (int y = 0; y < ySizeSmall; y++) {
                gridPoints[x][y] = new GridPoint("l", 100);
                gridPoints[x][y].setHeight(heightArr[x][y]);
            }
        }

        MapGrid map = new MapGrid(gridPoints, xSizeSmall, ySizeSmall);
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
        Assert.assertEquals(59.5, map.getPointAtLoc(1, 1).getHeight(), 0);
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

    @Test
    public void addBeachesEditsMapAsExpected() throws UninitializedMapException {
        MapGrid map = generateFixedMapGrid();
        map.FloodMap(80);
        //<editor-fold desc="Beach Type Array">
        String[][] beachTypeArr = {{"w","w","l","w","l","l","l","w","w","w","w","w","l","w","w","w","l","l","l","l"},
                {"w","w","b","l","l","l","l","b","w","b","b","l","l","w","w","l","l","l","w","w"},
                {"l","b","b","b","w","b","w","b","w","w","b","b","b","l","b","b","l","l","l","l"},
                {"l","w","w","b","b","b","b","w","b","b","w","l","l","l","l","b","w","b","w","w"},
                {"w","l","b","w","w","b","w","l","b","w","w","b","l","w","l","b","w","b","l","l"},
                {"l","w","b","w","w","b","l","l","l","l","l","w","b","b","w","w","b","w","l","l"},
                {"w","b","b","b","b","b","l","l","l","w","l","b","b","w","w","b","b","l","w","w"},
                {"w","l","w","w","w","l","l","l","l","l","w","l","l","w","l","w","b","l","l","l"},
                {"l","b","b","w","b","l","l","l","w","w","l","b","l","l","b","b","w","l","l","l"},
                {"l","l","b","b","b","b","w","l","b","w","b","b","l","l","b","b","l","w","l","l"},
                {"w","w","l","w","w","l","w","b","b","b","b","w","w","l","w","l","w","w","l","l"},
                {"l","l","l","l","l","w","l","b","w","l","w","b","w","l","b","b","w","l","l","l"},
                {"w","b","b","w","b","b","l","b","b","w","w","b","b","b","w","b","b","l","l","l"},
                {"l","w","l","l","l","w","l","w","l","l","b","b","w","w","l","l","l","w","l","l"},
                {"w","l","w","l","l","l","w","l","l","w","l","l","w","l","w","l","l","l","w","l"}};
        //</editor-fold>
        //<editor-fold desc="Beach Height Array">
        float[][] beachHeightArr = {{80.0f,80.0f,107.5f,80.0f,158.5f,121.5f,205.5f,80.0f,80.0f,80.0f,80.0f,80.0f,172.5f,80.0f,80.0f,80.0f,177.5f,164.5f,243.5f,100.5f},
                {80.0f,80.0f,248.5f,205.5f,251.5f,167.5f,157.5f,105.5f,80.0f,142.5f,115.5f,146.5f,166.5f,80.0f,80.0f,119.5f,165.5f,87.5f,80.0f,80.0f},
                {180.5f,187.5f,83.5f,135.5f,80.0f,99.5f,80.0f,118.5f,80.0f,80.0f,116.5f,165.5f,87.5f,139.5f,123.5f,165.5f,150.5f,202.5f,229.5f,93.5f},
                {155.5f,80.0f,80.0f,123.5f,155.5f,111.5f,212.5f,80.0f,193.5f,156.5f,80.0f,102.5f,144.5f,197.5f,198.5f,160.5f,80.0f,235.5f,80.0f,80.0f},
                {80.0f,170.5f,173.5f,80.0f,80.0f,89.5f,80.0f,143.5f,133.5f,80.0f,80.0f,98.5f,205.5f,80.0f,162.5f,132.5f,80.0f,254.5f,213.5f,163.5f},
                {166.5f,80.0f,205.5f,80.0f,80.0f,215.5f,144.5f,202.5f,198.5f,141.5f,200.5f,80.0f,247.5f,156.5f,80.0f,80.0f,131.5f,80.0f,172.5f,196.5f},
                {80.0f,101.5f,227.5f,115.5f,246.5f,146.5f,125.5f,223.5f,251.5f,80.0f,224.5f,94.5f,166.5f,80.0f,80.0f,146.5f,153.5f,154.5f,80.0f,80.0f},
                {80.0f,191.5f,80.0f,80.0f,80.0f,145.5f,200.5f,120.5f,189.5f,218.5f,80.0f,190.5f,171.5f,80.0f,203.5f,80.0f,232.5f,81.5f,121.5f,97.5f},
                {196.5f,236.5f,109.5f,80.0f,124.5f,151.5f,83.5f,251.5f,80.0f,80.0f,202.5f,184.5f,150.5f,232.5f,241.5f,121.5f,80.0f,145.5f,117.5f,230.5f},
                {107.5f,167.5f,108.5f,226.5f,119.5f,235.5f,80.0f,127.5f,242.5f,80.0f,181.5f,230.5f,252.5f,127.5f,80.5f,208.5f,122.5f,80.0f,163.5f,212.5f},
                {80.0f,80.0f,202.5f,80.0f,80.0f,107.5f,80.0f,250.5f,232.5f,152.5f,178.5f,80.0f,80.0f,229.5f,80.0f,163.5f,80.0f,80.0f,208.5f,205.5f},
                {238.5f,80.5f,149.5f,203.5f,242.5f,80.0f,99.5f,165.5f,80.0f,107.5f,80.0f,179.5f,80.0f,247.5f,194.5f,239.5f,80.0f,167.5f,116.5f,176.5f},
                {80.0f,87.5f,184.5f,80.0f,241.5f,229.5f,209.5f,104.5f,148.5f,80.0f,80.0f,175.5f,139.5f,206.5f,80.0f,129.5f,111.5f,178.5f,226.5f,123.5f},
                {126.5f,80.0f,175.5f,204.5f,109.5f,80.0f,206.5f,80.0f,237.5f,112.5f,186.5f,116.5f,80.0f,80.0f,182.5f,121.5f,121.5f,80.0f,149.5f,135.5f},
                {80.0f,249.5f,80.0f,95.5f,166.5f,81.5f,80.0f,167.5f,174.5f,80.0f,193.5f,127.5f,80.0f,201.5f,80.0f,237.5f,168.5f,236.5f,80.0f,123.5f}};
        //</editor-fold>

        map.AddBeaches(1, 8, 2);
        for (int x = 0; x < map.getXSize(); x++) {
            for (int y = 0; y < map.getYSize(); y++) {
                Assert.assertEquals(beachTypeArr[y][x], map.getPointAtLoc(x, y).getType());
                Assert.assertEquals(beachHeightArr[y][x], map.getPointAtLoc(x, y).getHeight(), 0.0f);
            }
        }
    }

    @Test
    public void removeNoiseEditsMapAsExpected() throws UninitializedMapException, IOException {
        MapGrid map = generateFixedMapGrid();
        map.FloodMap(80);
        //<editor-fold desc="Noise Type Array">
        String[][] noiseTypeArr = {{"w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w"},
                {"w","w","w","w","w","w","w","w","w","w","w","w","w","w","l","l","w","w","w","w"},
                {"w","w","w","l","l","l","l","l","w","w","w","l","l","l","l","l","l","w","w","w"},
                {"w","w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w","w"},
                {"w","w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w","w"},
                {"w","w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w","w"},
                {"w","w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w","w"},
                {"w","w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w"},
                {"w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w"},
                {"w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w"},
                {"w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w"},
                {"w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w"},
                {"w","w","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","l","w","w"},
                {"w","w","w","w","l","l","w","w","w","w","w","w","w","w","w","l","w","w","w","w"},
                {"w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w","w"}};
        //</editor-fold>
        //<editor-fold desc="Noise Height Array">
        float[][] noiseHeightArr = {{80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,119.5f,80.0f,80.0f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,135.5f,80.0f,99.5f,80.0f,118.5f,80.0f,80.0f,80.0f,165.5f,87.5f,139.5f,123.5f,165.5f,150.5f,80.0f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,123.5f,155.5f,111.5f,212.5f,80.0f,193.5f,156.5f,80.0f,102.5f,144.5f,197.5f,198.5f,160.5f,80.0f,235.5f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,80.0f,80.0f,89.5f,80.0f,143.5f,133.5f,80.0f,80.0f,98.5f,205.5f,80.0f,162.5f,132.5f,80.0f,254.5f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,80.0f,80.0f,215.5f,144.5f,202.5f,198.5f,141.5f,200.5f,80.0f,247.5f,156.5f,80.0f,80.0f,131.5f,80.0f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,115.5f,246.5f,146.5f,125.5f,223.5f,251.5f,80.0f,224.5f,94.5f,166.5f,80.0f,80.0f,146.5f,153.5f,154.5f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,80.0f,80.0f,145.5f,200.5f,120.5f,189.5f,218.5f,80.0f,190.5f,171.5f,80.0f,203.5f,80.0f,232.5f,81.5f,121.5f,80.0f},
                {80.0f,80.0f,109.5f,80.0f,124.5f,151.5f,83.5f,251.5f,80.0f,80.0f,202.5f,184.5f,150.5f,232.5f,241.5f,121.5f,80.0f,145.5f,117.5f,80.0f},
                {80.0f,80.0f,108.5f,226.5f,119.5f,235.5f,80.0f,127.5f,242.5f,80.0f,181.5f,230.5f,252.5f,127.5f,80.5f,208.5f,122.5f,80.0f,163.5f,80.0f},
                {80.0f,80.0f,202.5f,80.0f,80.0f,107.5f,80.0f,250.5f,232.5f,152.5f,178.5f,80.0f,80.0f,229.5f,80.0f,163.5f,80.0f,80.0f,208.5f,80.0f},
                {80.0f,80.0f,149.5f,203.5f,242.5f,80.0f,99.5f,165.5f,80.0f,107.5f,80.0f,179.5f,80.0f,247.5f,194.5f,239.5f,80.0f,167.5f,116.5f,80.0f},
                {80.0f,80.0f,184.5f,80.0f,241.5f,229.5f,209.5f,104.5f,148.5f,80.0f,80.0f,175.5f,139.5f,206.5f,80.0f,129.5f,111.5f,178.5f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,80.0f,109.5f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,121.5f,80.0f,80.0f,80.0f,80.0f},
                {80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f,80.0f}};
        //</editor-fold>
        map.RemoveTerrainNoise(2, 14, 2);
        for (int x = 0; x < map.getXSize(); x++) {
            for (int y = 0; y < map.getYSize(); y++) {
                Assert.assertEquals(noiseTypeArr[y][x], map.getPointAtLoc(x, y).getType());
                Assert.assertEquals(noiseHeightArr[y][x], map.getPointAtLoc(x, y).getHeight(), 0.0f);
            }
        }
    }

    @Test
    public void smoothHeightEditsMapAsExpected() throws UninitializedMapException, IOException {
        MapGrid map = generateFixedMapGrid();
        map.FloodMap(80);
        //<editor-fold desc="Smooth Type Array">
        String[][] smoothTypeArr = {{"w","w","l","w","l","l","l","w","w","w","w","w","l","w","w","w","l","l","l","l"},
                {"w","w","l","l","l","l","l","l","w","l","l","l","l","w","w","l","l","l","w","w"},
                {"l","l","l","l","w","l","w","l","w","w","l","l","l","l","l","l","l","l","l","l"},
                {"l","w","w","l","l","l","l","w","l","l","w","l","l","l","l","l","w","l","w","w"},
                {"w","l","l","w","w","l","w","l","l","w","w","l","l","w","l","l","w","l","l","l"},
                {"l","w","l","w","w","l","l","l","l","l","l","w","l","l","w","w","l","w","l","l"},
                {"w","l","l","l","l","l","l","l","l","w","l","l","l","w","w","l","l","l","w","w"},
                {"w","l","w","w","w","l","l","l","l","l","w","l","l","w","l","w","l","l","l","l"},
                {"l","l","l","w","l","l","l","l","w","w","l","l","l","l","l","l","w","l","l","l"},
                {"l","l","l","l","l","l","w","l","l","w","l","l","l","l","l","l","l","w","l","l"},
                {"w","w","l","w","w","l","w","l","l","l","l","w","w","l","w","l","w","w","l","l"},
                {"l","l","l","l","l","w","l","l","w","l","w","l","w","l","l","l","w","l","l","l"},
                {"w","l","l","w","l","l","l","l","l","w","w","l","l","l","w","l","l","l","l","l"},
                {"l","w","l","l","l","w","l","w","l","l","l","l","w","w","l","l","l","w","l","l"},
                {"w","l","w","l","l","l","w","l","l","w","l","l","w","l","w","l","l","l","w","l"}};
        //</editor-fold>
        //<editor-fold desc="Smooth Height Array">
        float[][] smoothHeightArr = {{80.0f,80.0f,127.78268f,80.0f,134.27617f,120.376816f,112.85952f,80.0f,80.0f,80.0f,80.0f,80.0f,108.099464f,80.0f,80.0f,80.0f,141.64958f,136.13f,137.76285f,113.90823f},
                {80.0f,80.0f,125.45625f,125.0869f,133.26474f,122.14946f,116.21846f,109.31484f,80.0f,104.28992f,109.89217f,116.207504f,114.961624f,80.0f,80.0f,129.99136f,136.65572f,130.7692f,80.0f,80.0f},
                {124.53333f,120.70677f,116.79235f,116.524414f,80.0f,114.82555f,80.0f,110.019844f,80.0f,80.0f,111.37799f,113.5143f,115.89635f,121.26238f,121.63047f,131.12177f,140.03168f,139.91798f,125.59398f,116.81736f},
                {133.10222f,80.0f,80.0f,115.87604f,118.5843f,119.53246f,122.83461f,80.0f,119.963486f,115.09002f,80.0f,121.64105f,127.89854f,124.51718f,122.41724f,126.30388f,80.0f,135.65697f,80.0f,80.0f},
                {80.0f,120.22746f,122.05797f,80.0f,80.0f,123.38449f,80.0f,133.4332f,128.21393f,80.0f,80.0f,121.714554f,131.12613f,80.0f,120.72534f,127.796364f,80.0f,131.80571f,122.71548f,118.33586f},
                {128.70682f,80.0f,115.04878f,80.0f,80.0f,129.40588f,140.899f,146.8672f,140.64824f,127.89633f,135.75797f,80.0f,131.20703f,124.431114f,80.0f,80.0f,128.83617f,80.0f,116.28764f,112.98382f},
                {80.0f,123.38005f,111.70418f,117.13457f,119.13498f,127.46383f,141.96408f,145.367f,142.37868f,80.0f,138.58614f,133.26611f,133.74702f,80.0f,80.0f,119.75369f,126.72422f,125.24283f,80.0f,80.0f},
                {80.0f,133.7449f,80.0f,80.0f,80.0f,134.13441f,148.12823f,146.53511f,146.91132f,145.34694f,80.0f,145.1854f,138.97455f,80.0f,131.90768f,80.0f,118.77846f,123.4931f,126.219826f,123.75572f},
                {136.66667f,128.2718f,117.92896f,80.0f,117.535934f,132.14355f,146.85124f,151.05972f,80.0f,80.0f,141.35081f,139.48148f,138.26813f,137.27185f,129.20998f,121.87607f,80.0f,126.870605f,129.07463f,133.30699f},
                {136.64445f,123.811874f,125.52293f,129.31459f,120.3707f,127.65352f,80.0f,140.08429f,137.42555f,80.0f,135.93051f,142.35089f,144.52258f,146.2699f,130.20087f,128.45259f,117.663574f,80.0f,134.66336f,137.78809f},
                {80.0f,80.0f,136.65813f,80.0f,80.0f,128.75183f,80.0f,131.1821f,126.58137f,132.04051f,128.83044f,80.0f,80.0f,147.78773f,80.0f,126.28061f,80.0f,80.0f,140.78474f,134.14592f},
                {131.90964f,131.095f,138.33112f,137.47572f,137.3632f,80.0f,139.34982f,128.53656f,80.0f,128.10617f,80.0f,132.19244f,80.0f,136.80927f,127.98127f,121.40168f,80.0f,139.16719f,133.98483f,126.269005f},
                {80.0f,129.10194f,130.70232f,80.0f,126.93857f,128.43063f,140.22797f,125.316795f,127.32625f,80.0f,80.0f,125.05032f,125.78576f,132.15565f,80.0f,126.33483f,128.82771f,138.42796f,131.62039f,122.26474f},
                {125.53414f,80.0f,137.8551f,135.34946f,128.73723f,80.0f,133.23376f,80.0f,124.66095f,122.78371f,119.26882f,121.27187f,80.0f,80.0f,126.104126f,132.05685f,133.162f,80.0f,126.9527f,123.150444f},
                {80.0f,130.42801f,80.0f,127.229126f,133.42079f,124.707054f,80.0f,126.22775f,122.999565f,80.0f,118.03596f,117.49404f,80.0f,126.35051f,80.0f,134.53346f,137.4346f,139.11848f,80.0f,118.448296f}};

        //</editor-fold>
        map.BasicSmoothHeightMap(2);
        for (int x = 0; x < map.getXSize(); x++) {
            for (int y = 0; y < map.getYSize(); y++) {
                Assert.assertEquals(smoothTypeArr[y][x], map.getPointAtLoc(x, y).getType());
                Assert.assertEquals(smoothHeightArr[y][x], map.getPointAtLoc(x, y).getHeight(), 0.0f);
            }
        }
    }
}