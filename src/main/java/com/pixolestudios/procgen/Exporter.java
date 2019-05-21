package main.java.com.pixolestudios.procgen;

import main.java.com.pixolestudios.fileUtils.FileUtils;

import java.io.FileWriter;
import java.io.IOException;

public class Exporter {
    private GridPoint[][] map;

    public Exporter(GridPoint[][] map) {
        this.map = map;
    }

    public void ExportHeightMapCSV(String outputFile){
        String fileContent = "";
        FileUtils.mkdirs(outputFile);
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            for (int j = 0; j < map[0].length; j++) {
                fileContent += map[0][j].getHeight();
                for (int i = 1; i < map.length; i++) {
                    fileContent += "," + map[i][j].getHeight();
                }
                fileWriter.write(fileContent + "\n");
                fileContent = "";
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ExportTerrainTypeCSV(String outputFile){
        String fileContent = "";
        FileUtils.mkdirs(outputFile);
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            for (int j = 0; j < map[0].length; j++) {
                fileContent += map[0][j].getType();
                for (int i = 1; i < map.length; i++) {
                    fileContent += "," + map[i][j].getType();
                }
                fileWriter.write(fileContent + "\n");
                fileContent = "";
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
