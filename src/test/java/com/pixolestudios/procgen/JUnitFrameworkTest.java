package com.pixolestudios.procgen;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitFrameworkTest {
    @Test
    public void testSetup() {
        String str = "JUnit setup successfully";
        assertEquals("JUnit setup successfully", str);
    }
}
