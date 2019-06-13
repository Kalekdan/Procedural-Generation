package com.pixolestudios.procgen;

import org.junit.ComparisonFailure;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitFrameworkTest {
    @Test
    public void testSetup() {
        String str = "JUnit setup successfully";
        assertEquals("JUnit setup successfully", str);
    }

    @Test
    public void testAssertionErrorsThrown() {
        String expected = "expected";
        String actual = "actual";
        try {
            //Force a comparison failure
            assertEquals(expected, actual);
        } catch (ComparisonFailure e){
            assertEquals("expected:<[expected]> but was:<[actual]>", e.getMessage());
        }
    }
}
