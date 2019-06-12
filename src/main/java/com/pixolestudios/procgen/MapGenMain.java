package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.uiUtils.PrimaryWindow;

public class MapGenMain {

    public static String WINDOW_TITLE = "Procedural Generator";

    private static PrimaryWindow window = new PrimaryWindow();

    private MapGenMain() {
    }

    public static void main(String[] args) {
        window.setVisible(true);

//        Exporter export = new Exporter(map.getMap());
//        export.ExportHeightMapCSV("output/exports/heightMap.csv");
//        export.ExportTerrainTypeCSV("output/exports/terrainType.csv");
    }

    public static PrimaryWindow getWindow(){
        return window;
    }
}
