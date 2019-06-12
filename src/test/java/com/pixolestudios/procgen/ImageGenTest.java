package com.pixolestudios.procgen;


import main.java.com.pixolestudios.exceptions.UninitializedMapException;
import main.java.com.pixolestudios.plogger.PLog;
import main.java.com.pixolestudios.procgen.GridPoint;
import main.java.com.pixolestudios.procgen.ImageGen;
import main.java.com.pixolestudios.procgen.MapGrid;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGenTest {

    private final String testOutLoc = "src/test/java/com/pixolestudios/procgen/testout";

    private final int mapXSize = 10, mapYSize = 20;
    private final int pixelsPerGridPoint = 50;

    @Test
    public void assertImageResolutionAsExpected() throws IOException, UninitializedMapException {
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);
        mapToImage.InitialGenerateDryMap(1, 255);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/assertImageResolutionAsExpected.png", pixelsPerGridPoint);
        img.GenerateImg();

        BufferedImage bimg = null;
        bimg = ImageIO.read(new File(testOutLoc + "/assertImageResolutionAsExpected.png"));
        int actualXSize = bimg.getWidth();
        int actualYSize = bimg.getHeight();

        Assert.assertEquals(mapXSize * pixelsPerGridPoint, actualXSize);
        Assert.assertEquals(mapYSize * pixelsPerGridPoint, actualYSize);
    }

    @Test
    public void imageGenOnUninitializedMapDoesntMakeImage() throws IOException {
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/imageGenOnUninitiatedMapDoesntMakeImage.png", 1);
        try {
            img.GenerateImg();
        } catch (UninitializedMapException e) {
            PLog.warning("Map has not been initialized");
        }

        File output = new File(testOutLoc + "/imageGenOnUninitiatedMapDoesntMakeImage.png");
        Assert.assertFalse("Image was created on uninitialised map.", output.exists());
    }

    @Test
    public void imageGenOnMapMakesImageInExpectedLocation() throws UninitializedMapException, IOException {
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);
        mapToImage.InitialGenerateDryMap(1, 255);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/imageGenOnMapMakesImageInExpectedLocation.png", pixelsPerGridPoint);
        img.GenerateImg();
        File output = new File(testOutLoc + "/imageGenOnMapMakesImageInExpectedLocation.png");
        Assert.assertTrue("Image was generated in expected location.", output.exists());
    }

    @Test
    public void imageGenNoLocationSpecifiedMakesImageInDefLocation() throws UninitializedMapException, IOException {
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);
        mapToImage.InitialGenerateDryMap(1, 255);

        ImageGen img = new ImageGen(mapToImage, pixelsPerGridPoint);
        img.GenerateImg();
        File output = new File(ImageGen.DEFAULT_OUT_LOC);
        Assert.assertTrue("Image was generated in default location.", output.exists());
    }

    @Test
    public void imgGenColorsTerrainTypesAsExpected() throws UninitializedMapException, IOException {
        //Set the grid points
        GridPoint[][] gridPoints = new GridPoint[1][4];
        gridPoints[0][0] = new GridPoint("w", 100);
        gridPoints[0][1] = new GridPoint("l", 100);
        gridPoints[0][2] = new GridPoint("b", 100);
        gridPoints[0][3] = new GridPoint("invalidType", 100);
        MapGrid mapToImage = new MapGrid(gridPoints, 1, 4);
        mapToImage.setInstantiated(true);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/imgGenColorsTerrainTypesAsExpected.png", 1);
        img.GenerateImg();

        BufferedImage bimg = null;
        bimg = ImageIO.read(new File(testOutLoc + "/imgGenColorsTerrainTypesAsExpected.png"));
        int waterPixel = (100 << 24) | (0 << 16) | (0 << 8) | 255;
        int landPixel = (100 << 24) | (0 << 16) | (255 << 8) | 0;
        int beachPixel = (100 << 24) | (255 << 16) | (242 << 8) | 179;
        int invalidPixel = (100 << 24) | (0 << 16) | (0 << 8) | 0;

        Assert.assertEquals(waterPixel, bimg.getRGB(0, 0));
        Assert.assertEquals(landPixel, bimg.getRGB(0, 1));
        Assert.assertEquals(beachPixel, bimg.getRGB(0, 2));
        Assert.assertEquals(invalidPixel, bimg.getRGB(0, 3));
    }
}