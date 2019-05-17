package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.logUtils.Logger;
import main.java.com.pixolestudios.logUtils.LoggingLevel;

public class MapGenMain {

    public static Logger logger = new Logger(LoggingLevel.CONFIG);

    public static void main(String[] args) {

        logger.log("Creating map object", LoggingLevel.INFO);
        MapGrid map = new MapGrid(150, 100);

        logger.log("Generating dry terrain", LoggingLevel.INFO);
        map.InitialGenerateDryMap(1, 255);
        ImageGen img = new ImageGen(map, "imgout/dryTerrainMap.png", 10);
        img.GenerateImg();

        logger.log("Flooding terrain", LoggingLevel.INFO);
        map.FloodMap(90);
        ImageGen img5 = new ImageGen(map, "imgout/floodedTerrainMap.png", 10);
        img5.GenerateImg();

        logger.log("Removing terrain noise", LoggingLevel.INFO);
        map.RemoveTerrainNoise(3, 31, 3);
        ImageGen img3 = new ImageGen(map, "imgout/smoothedFormationMap.png", 10);
        img3.GenerateImg();

        logger.log("Smoothing heights", LoggingLevel.INFO);
        map.BasicSmoothHeightMap(3);
        ImageGen img6 = new ImageGen(map, "imgout/smoothedHeightMap.png", 10);
        img6.GenerateImg();

        logger.log("Adding beaches", LoggingLevel.INFO);
        map.AddBeaches(1, 20, 4);
        ImageGen img4 = new ImageGen(map, "imgout/addedBeachesMap.png", 10);
        img4.GenerateImg();
    }

}
