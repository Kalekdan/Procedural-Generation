package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.fileUtils.FileUtils;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
    private GridPoint[][] map;

    /**
     * Construct a new Exporter object to export maps
     *
     * @param map the map to be exported
     */
    public Exporter(GridPoint[][] map) {
        this.map = map;
    }

    /**
     * Export the heightmap to a csv file
     *
     * @param outputFile path to the file to export to
     */
    public void ExportHeightMapCSV(String outputFile) throws IOException {
        String fileContent = "";
        FileUtils.mkdirs(outputFile);
        FileWriter fileWriter = new FileWriter(outputFile);
        for (int j = 0; j < map[0].length; j++) {
            fileContent += map[0][j].getHeight();
            for (int i = 1; i < map.length; i++) {
                fileContent += "," + map[i][j].getHeight();
            }
            fileWriter.write(fileContent + "\n");
            fileContent = "";
        }
        fileWriter.close();
    }

    /**
     * Export a csv of terrain types
     *
     * @param outputFile path to the file to export to
     */
    public void ExportTerrainTypeCSV(String outputFile) throws IOException {
        String fileContent = "";
        FileUtils.mkdirs(outputFile);
        FileWriter fileWriter = new FileWriter(outputFile);
        for (int j = 0; j < map[0].length; j++) {
            fileContent += map[0][j].getType();
            for (int i = 1; i < map.length; i++) {
                fileContent += "," + map[i][j].getType();
            }
            fileWriter.write(fileContent + "\n");
            fileContent = "";
        }
        fileWriter.close();

    }

}
