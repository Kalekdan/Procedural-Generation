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

    @Test
    public void assertImageResolutionAsExpected() {
        int mapXSize = 10, mapYSize = 20;
        int pixelsPerGridPoint = 50;

        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);
        mapToImage.InitialGenerateDryMap(1, 255);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/assertImageResolutionAsExpected.png", pixelsPerGridPoint);
        img.GenerateImg();

        BufferedImage bimg = null;
        try {
            bimg = ImageIO.read(new File(testOutLoc + "/assertImageResolutionAsExpected.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int actualXSize = bimg.getWidth();
        int actualYSize = bimg.getHeight();

        Assert.assertEquals(mapXSize * pixelsPerGridPoint, actualXSize);
        Assert.assertEquals(mapYSize * pixelsPerGridPoint, actualYSize);
    }

    @Test
    public void imageGenOnUninitializedMapDoesntMakeImage() {
        int mapXSize = 10, mapYSize = 20;
        MapGrid mapToImage = new MapGrid(mapXSize, mapYSize);

        ImageGen img = new ImageGen(mapToImage, testOutLoc + "/imageGenOnUninitiatedMapDoesntMakeImage.png", 1);
        img.GenerateImg();

        File output = new File(testOutLoc + "/imageGenOnUninitiatedMapDoesntMakeImage.png");
        Assert.assertFalse("Image was created on uninitialised map.", output.exists());
    }
}