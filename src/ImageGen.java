import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGen {
    private MapGrid map;
    private int width, height;
    private File OutputFile;



    ImageGen(MapGrid map, String FileLoc, int ppg){
        this.map = map;
        width = map.getXSize() * ppg;
        height = map.getYSize() * ppg;
        OutputFile = new File(FileLoc);
    }

    public void GenerateImg(){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        int a,r,g,b;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if (map.getPointAtLoc(x,y).getType().equals("w")){
                    a = 255; //(int)(Math.random()*256); //alpha
                    r = 0;// (int)(Math.random()*256); //red
                    g = 0;//(int)(Math.random()*256); //green
                    b = 255;//(int)(Math.random()*256); //blue
                } else {
                    a = 255; //(int)(Math.random()*256); //alpha
                    r = 0;// (int)(Math.random()*256); //red
                    g = 255;//(int)(Math.random()*256); //green
                    b = 0;//(int)(Math.random()*256); //blue
                }


                int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel

                img.setRGB(x, y, p);
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
