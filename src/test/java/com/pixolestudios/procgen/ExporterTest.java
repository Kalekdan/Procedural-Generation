package com.pixolestudios.procgen;

import main.java.com.pixolestudios.procgen.Exporter;
import main.java.com.pixolestudios.procgen.MapGrid;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExporterTest {

    private final String testOutLoc = "src/test/java/com/pixolestudios/procgen/testout";

    private final int mapXSize = 10, mapYSize = 20;

    @Test
    public void assertExportHeightMapCSVOutputExists() {
        MapGrid mapToExport = new MapGrid(mapXSize, mapYSize);
        mapToExport.InitialGenerateDryMap(1, 255);

        Exporter export = new Exporter(mapToExport.getMap());
        export.ExportHeightMapCSV(testOutLoc + "/assertExportHeightMapCSVOutputFileSize.csv");

        File output = new File(testOutLoc + "/assertExportHeightMapCSVOutputFileSize.csv");
        Assert.assertTrue("File was exported in expected location.", output.exists());
    }

    @Test
    public void assertExportHeightMapCSVOutputsExpectedNumberOfRows() throws IOException {
        MapGrid mapToExport = new MapGrid(mapXSize, mapYSize);
        mapToExport.InitialGenerateDryMap(1, 255);

        Exporter export = new Exporter(mapToExport.getMap());
        export.ExportHeightMapCSV(testOutLoc + "/assertExportHeightMapCSVOutputsExpectedNumberOfRows.csv");

        Path path = Paths.get(testOutLoc + "/assertExportHeightMapCSVOutputsExpectedNumberOfRows.csv");
        long lineCount = Files.lines(path).count();
        Assert.assertEquals("Exported file does not have number of rows", mapYSize, lineCount);
    }

    @Test
    public void assertExportHeightMapCSVOutputsExpectedNumberOfCollumns() throws IOException {
        MapGrid mapToExport = new MapGrid(mapXSize, mapYSize);
        mapToExport.InitialGenerateDryMap(1, 255);

        Exporter export = new Exporter(mapToExport.getMap());
        export.ExportHeightMapCSV(testOutLoc + "/assertExportHeightMapCSVOutputsExpectedNumberOfCollumns.csv");

        BufferedReader reader = new BufferedReader(new FileReader(testOutLoc + "/assertExportHeightMapCSVOutputsExpectedNumberOfCollumns.csv"));
        int numberOfCollumns = reader.readLine().split(",").length;
        Assert.assertEquals("Exported file does not have number of collumns", mapXSize, numberOfCollumns);
    }

    @Test
    public void assertExportTerrainTypeCSVOutputExists() {
        MapGrid mapToExport = new MapGrid(mapXSize, mapYSize);
        mapToExport.InitialGenerateDryMap(1, 255);

        Exporter export = new Exporter(mapToExport.getMap());
        export.ExportHeightMapCSV(testOutLoc + "/assertExportTerrainTypeCSVOutputExists.csv");

        File output = new File(testOutLoc + "/assertExportTerrainTypeCSVOutputExists.csv");
        Assert.assertTrue("File was exported in expected location.", output.exists());
    }

    @Test
    public void assertExportTerrainTypeCSVOutputsExpectedNumberOfRows() throws IOException {
        MapGrid mapToExport = new MapGrid(mapXSize, mapYSize);
        mapToExport.InitialGenerateDryMap(1, 255);

        Exporter export = new Exporter(mapToExport.getMap());
        export.ExportHeightMapCSV(testOutLoc + "/assertExportTerrainTypeCSVOutputsExpectedNumberOfRows.csv");

        Path path = Paths.get(testOutLoc + "/assertExportTerrainTypeCSVOutputsExpectedNumberOfRows.csv");
        long lineCount = Files.lines(path).count();
        Assert.assertEquals("Exported file does not have number of rows", mapYSize, lineCount);
    }

    @Test
    public void assertExportTerrainTypeCSVOutputsExpectedNumberOfCollumns() throws IOException {
        MapGrid mapToExport = new MapGrid(mapXSize, mapYSize);
        mapToExport.InitialGenerateDryMap(1, 255);

        Exporter export = new Exporter(mapToExport.getMap());
        export.ExportHeightMapCSV(testOutLoc + "/assertExportTerrainTypeCSVOutputsExpectedNumberOfCollumns.csv");

        BufferedReader reader = new BufferedReader(new FileReader(testOutLoc + "/assertExportTerrainTypeCSVOutputsExpectedNumberOfCollumns.csv"));
        int numberOfCollumns = reader.readLine().split(",").length;
        Assert.assertEquals("Exported file does not have number of collumns", mapXSize, numberOfCollumns);
    }

}