
package com.rea.test;

import com.rea.test.command.*;
import com.rea.test.models.Command;
import com.rea.test.models.Position;
import com.rea.test.models.Robot;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static com.rea.test.models.Command.*;
import static com.rea.test.models.Direction.EAST;
import static org.junit.Assert.*;

public class FileReaderTest {
    private static final String TEST_SOURCE_FILE = "./src/test/resources/testCommandFile.txt";
    private FileReader reader;
    private Robot robot;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));

        reader = new FileReader(getCommandHandlersWithParameters(), getCommandHandlersWithoutParameters());
        robot = new Robot();
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowFileNotFoundExceptionIfFileDoesNotExist() throws Exception {
        reader.readFile(robot, "testSourceFile.csv");
    }

    @Test
    public void shouldIgnoreBlankLinesAndProcessCommands() throws Exception {
        reader.readFile(robot, TEST_SOURCE_FILE);

        assertEquals(IOUtils.toString(getClass().getResourceAsStream("/expectedText.txt")), outContent.toString());
        assertEquals(new Position(3, 4, EAST), robot.getPosition());
    }

    private Map<Command,BaseParameterCommandHandler> getCommandHandlersWithParameters() {
        Map<Command, BaseParameterCommandHandler> commandHandlersWithParameters = new HashMap<>();
        commandHandlersWithParameters.put(PLACE, new PlaceCommandHandler());
        return commandHandlersWithParameters;
    }

    private Map<Command,BaseNoParameterCommandHandler> getCommandHandlersWithoutParameters() {
        Map<Command, BaseNoParameterCommandHandler> commandHandlersWithoutParameters = new HashMap<>();
        commandHandlersWithoutParameters.put(MOVE, new MoveCommandHandler());
        commandHandlersWithoutParameters.put(LEFT, new LeftCommandHandler());
        commandHandlersWithoutParameters.put(RIGHT, new RightCommandHandler());
        commandHandlersWithoutParameters.put(REPORT, new ReportCommandHandler());
        return commandHandlersWithoutParameters;
    }
}

