package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.exceptions.UninitializedMapException;
import main.java.com.pixolestudios.fileUtils.FileUtils;
import main.java.com.pixolestudios.plogger.PLog;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGen {
    private MapGrid map;
    private int width, height, ppg;
    private File OutputFile;

    public static final String DEFAULT_OUT_LOC = "output/imggen/tempOutImg.png";

    /**
     * Prepares the image gen to generate an image of the map
     *
     * @param map     the map to generate the image of
     * @param FileLoc the project relative path to the output file
     * @param ppg     pixels per grid point
     */
    public ImageGen(MapGrid map, String FileLoc, int ppg) {
        this.map = map;
        width = map.getXSize();
        height = map.getYSize();
        this.ppg = ppg;
        FileUtils.mkdirs(FileLoc);
        OutputFile = new File(FileLoc);
    }

    /**
     * Prepares the image gen to generate image of map at default location
     *
     * @param map map to generate image of
     * @param ppg pixels per grid point
     */
    public ImageGen(MapGrid map, int ppg) {
        this(map, DEFAULT_OUT_LOC, ppg);
    }

    /**
     * Generates the image of the map at the output location provided in construction
     */
    public void GenerateImg() throws UninitializedMapException, IOException {
        if (!map.isInstantiated()) {
            throw new UninitializedMapException();
        }
        PLog.debug("Generating image - Size:" + width * ppg + "x" + height * ppg, "image_gen");
        BufferedImage img = new BufferedImage(width * ppg, height * ppg, BufferedImage.TYPE_INT_ARGB);

        int a, r, g, b;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (map.getPointAtLoc(x, y).getType().equals("w")) {
                    a = 255; //(int)(Math.random()*256); //alpha
                    r = 0;// (int)(Math.random()*256); //red
                    g = 0;//(int)(Math.random()*256); //green
                    b = 255;//(int)(Math.random()*256); //blue
                } else if (map.getPointAtLoc(x, y).getType().equals("l")) {
                    a = 255; //(int)(Math.random()*256); //alpha
                    r = 0;// (int)(Math.random()*256); //red
                    g = 255;//(int)(Math.random()*256); //green
                    b = 0;//(int)(Math.random()*256); //blue
                } else if (map.getPointAtLoc(x, y).getType().equals("b")) {
                    a = 255; //(int)(Math.random()*256); //alpha
                    r = 255;// (int)(Math.random()*256); //red
                    g = 242;//(int)(Math.random()*256); //green
                    b = 179;//(int)(Math.random()*256); //blue
                } else {
                    a = 0;
                    r = 0;
                    g = 0;
                    b = 0;
                }

                //TODO add height->alpha calculation
                a = Math.round(map.getPointAtLoc(x, y).getHeight());

                int p = (a << 24) | (r << 16) | (g << 8) | b; //pixel
                for (int i = 0; i < ppg; i++) {
                    for (int j = 0; j < ppg; j++) {
                        img.setRGB(x * ppg + i, y * ppg + j, p);
                    }
                }
            }
        }
        //write image
        ImageIO.write(img, "png", OutputFile);
    }


}
