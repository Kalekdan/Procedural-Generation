package com.pixolestudios.procgen;

import main.java.com.pixolestudios.procgen.MapGenMain;
import org.junit.Assert;
import org.junit.Test;

public class UITest {
    @Test
    public void mainMethodLaunchesDialog(){
        Assert.assertNotNull(MapGenMain.getWindow());
        Assert.assertFalse(MapGenMain.getWindow().isVisible());
        MapGenMain.main(new String[] {""});
        Assert.assertTrue(MapGenMain.getWindow().isVisible());
    }
}
