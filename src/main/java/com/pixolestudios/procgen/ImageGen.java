package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.logUtils.LoggingLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static main.java.com.pixolestudios.procgen.MapGenMain.logger;

public class ImageGen {
    private MapGrid map;
    private int width, height, ppg;
    private File OutputFile;

    /**
     * Prepares the image gen to generate an image of the map
     * @param map the map to generate the image of
     * @param FileLoc the project relative path to the output file
     * @param ppg TODO the number of pixels per grid point on the final image
     */
    public ImageGen(MapGrid map, String FileLoc, int ppg){
        this.map = map;
        width = map.getXSize();
        height = map.getYSize();
        this.ppg = ppg;
        OutputFile = new File(FileLoc);
    }

    /**
     * Generates the image of the map at the output location provided in construction
     */
    public void GenerateImg(){
        if (map.getPointAtLoc(0,0) == null){
            try {
                logger.log("Map not yet initialised. Cannot produce image.", LoggingLevel.WARNING);
                throw new Exception("Map not yet initialised. Try generating a map before trying to make an image");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        logger.log("Generating image - Size:" + width * ppg + "x" + height * ppg, LoggingLevel.DEBUG);
        BufferedImage img = new BufferedImage(width * ppg, height * ppg, BufferedImage.TYPE_INT_ARGB);

        int a,r,g,b;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if (map.getPointAtLoc(x,y).getType().equals("w")){
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
                    a = 0; r = 0; g = 0; b = 0;
                }

                //TODO add height->alpha calculation
                a = Math.round(map.getPointAtLoc(x,y).getHeight());

                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
                for (int i = 0; i < ppg; i++){
                    for (int j = 0; j < ppg; j++){
                        img.setRGB(x * ppg + i, y * ppg + j, p);
                    }
                }
            }
        }
        //write image
        try {
            ImageIO.write(img, "png", OutputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
