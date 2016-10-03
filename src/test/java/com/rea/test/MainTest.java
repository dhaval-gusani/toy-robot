
package com.rea.test;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldPrintErrorWithoutFilePath() {
        Main.main(null);
        assertTrue(outContent.toString().contains("Please provide valid filePath as commandLine argument"));
    }

    @Test
    public void shouldPrintErrorWithInvalidFilePath() {
        Main.main(new String[]{"./src/test/resources/testing.txt"});

        assertTrue(outContent.toString().contains("Please provide valid filePath as commandLine argument"));
    }

    @Test
    public void shouldProcessMainIfValidFilePathGiven() throws Exception {
        Main.main(new String[]{"./src/test/resources/testCommandFile.txt"});

        assertTrue(outContent.toString().contains(IOUtils.toString(getClass().getResourceAsStream("/expectedText.txt"))));
    }
}

