package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.logUtils.Logger;
import main.java.com.pixolestudios.logUtils.LoggingLevel;

public class MapGenMain {

    public static Logger logger = new Logger(LoggingLevel.DEBUG);

    public static void main(String[] args) {
        logger.writeLogsToFile("output/temp/logfile.txt", true);

        logger.log("Creating map object", LoggingLevel.INFO);
        MapGrid map = new MapGrid(150, 100);

        logger.log("Generating dry terrain", LoggingLevel.INFO);
        map.InitialGenerateDryMap(1, 255);
        ImageGen img = new ImageGen(map, "output/imggen/dryTerrainMap.png", 10);
        img.GenerateImg();

        logger.log("Flooding terrain", LoggingLevel.INFO);
        map.FloodMap(90);
        ImageGen img5 = new ImageGen(map, "output/imggen/floodedTerrainMap.png", 10);
        img5.GenerateImg();

        logger.log("Removing terrain noise", LoggingLevel.INFO);
        map.RemoveTerrainNoise(3, 31, 3);
        ImageGen img3 = new ImageGen(map, "output/imggen/smoothedFormationMap.png", 10);
        img3.GenerateImg();

        logger.log("Smoothing heights", LoggingLevel.INFO);
        map.BasicSmoothHeightMap(3);
        ImageGen img6 = new ImageGen(map, "output/imggen/smoothedHeightMap.png", 10);
        img6.GenerateImg();

        logger.log("Adding beaches", LoggingLevel.INFO);
        map.AddBeaches(1, 20, 4);
        ImageGen img4 = new ImageGen(map, "output/imggen/addedBeachesMap.png", 10);
        img4.GenerateImg();

        Exporter export = new Exporter(map.getMap());
        export.ExportHeightMapCSV("output/exports/heightMap.csv");
        export.ExportTerrainTypeCSV("output/exports/terrainType.csv");
    }

}
