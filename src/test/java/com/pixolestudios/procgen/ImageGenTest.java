package com.pixolestudios.procgen;


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
    public void assertImageResolutionAsExpected() throws IOException {
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
    public void imageGenOnUninitializedMapDoesntMakeImage() {
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/imageGenOnUninitiatedMapDoesntMakeImage.png", 1);
        img.GenerateImg();

        File output = new File(testOutLoc + "/imageGenOnUninitiatedMapDoesntMakeImage.png");
        Assert.assertFalse("Image was created on uninitialised map.", output.exists());
    }

    @Test
    public void imageGenOnMapMakesImageInExpectedLocation() {
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);
        mapToImage.InitialGenerateDryMap(1, 255);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/imageGenOnMapMakesImageInExpectedLocation.png", pixelsPerGridPoint);
        img.GenerateImg();
        File output = new File(testOutLoc + "/imageGenOnMapMakesImageInExpectedLocation.png");
        Assert.assertTrue("Image was generated in expected location.", output.exists());
    }
}