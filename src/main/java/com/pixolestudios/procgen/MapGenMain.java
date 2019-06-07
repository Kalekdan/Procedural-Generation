package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.plogger.PLog;
import main.java.com.pixolestudios.uiUtils.PrimaryWindow;

public class MapGenMain {

    public static String WINDOW_TITLE = "Procedural Generator";

    public static void main(String[] args) {
        PrimaryWindow window = new PrimaryWindow();
        window.setVisible(true);
        PLog.info("Creating map object");
        MapGrid map = new MapGrid(150, 100);

        PLog.info("Generating dry terrain");
        map.InitialGenerateDryMap(1, 255);
        ImageGen img = new ImageGen(map, "output/imggen/dryTerrainMap.png", 10);
        img.GenerateImg();

        PLog.info("Flooding terrain");
        map.FloodMap(90);
        ImageGen img5 = new ImageGen(map, "output/imggen/floodedTerrainMap.png", 10);
        img5.GenerateImg();

        PLog.info("Removing terrain noise");
        map.RemoveTerrainNoise(3, 31, 3);
        ImageGen img3 = new ImageGen(map, "output/imggen/smoothedFormationMap.png", 10);
        img3.GenerateImg();

        PLog.info("Smooothing heights");
        map.BasicSmoothHeightMap(3);
        ImageGen img6 = new ImageGen(map, "output/imggen/smoothedHeightMap.png", 10);
        img6.GenerateImg();

        PLog.info("Adding beaches");
        map.AddBeaches(1, 20, 4);
        ImageGen img4 = new ImageGen(map, "output/imggen/addedBeachesMap.png", 10);
        img4.GenerateImg();

        Exporter export = new Exporter(map.getMap());
        export.ExportHeightMapCSV("output/exports/heightMap.csv");
        export.ExportTerrainTypeCSV("output/exports/terrainType.csv");
    }

}
