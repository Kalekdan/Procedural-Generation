package com.pixolestudios.procgen;

import main.java.com.pixolestudios.procgen.GridPoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GridPointTest {

    GridPoint gridPoint;
    String terrainType = "type";
    float terrainHeight = 3.14f;

    @Before
    public void setup() {
        gridPoint = new GridPoint(terrainType, terrainHeight);
    }

    @Test
    public void assertGettersReturnExpected() {
        Assert.assertEquals(gridPoint.getType(), terrainType);
        Assert.assertEquals(gridPoint.getHeight(), terrainHeight, 0f);
    }

    @Test
    public void assertSettersUpdateValues() {
        String newType = "newTerrainType";
        float newTerrainHeight = 9.876f;
        gridPoint.setType(newType);
        gridPoint.setHeight(newTerrainHeight);

        Assert.assertEquals(gridPoint.getType(), newType);
        Assert.assertEquals(gridPoint.getHeight(), newTerrainHeight, 0f);
    }

    @Test
    public void assertToStringFitsExpectedFormat() {
        Assert.assertEquals(gridPoint.toString(), terrainType + ":" + terrainHeight);
    }

}