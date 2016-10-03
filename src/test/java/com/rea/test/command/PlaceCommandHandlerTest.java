
package com.rea.test.command;

import com.rea.test.models.Position;
import com.rea.test.models.Robot;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.rea.test.models.Command.PLACE;
import static com.rea.test.models.Direction.*;
import static com.rea.test.models.Robot.State.OFF_BOARD;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class PlaceCommandHandlerTest {

    private PlaceCommandHandler commandHandler;
    private Robot robot;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        commandHandler = new PlaceCommandHandler();
        robot = new Robot();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldIgnoreCommandIfRobotAlreadyOnBoard() {
        robot.setState(ON_BOARD);
        robot.setPosition(new Position(0, 0, EAST));

        commandHandler.execute(robot, new String[]{});
        assertTrue(outContent.toString().contains("Ignoring command PLACE, Robot is already on the board at Position[X: 0, Y: 0, Direction: EAST]"));
    }

    @UseDataProvider("parameterLengthCases")
    @Test
    public void shouldValidateNumberForParametersProvided(String[] parameters) {
        robot.setState(OFF_BOARD);

        commandHandler.execute(robot, parameters);
        assertTrue(outContent.toString().contains("Ignoring command PLACE, " +
                "Should pass 3 parameters for PLACE Command, [positionX, positionY, direction]"));
    }

    @DataProvider
    public static Object[][] parameterLengthCases() {
        return new Object[][] {
                {new String[] {String.valueOf(1), String.valueOf(2), String.valueOf(NORTH), String.valueOf(1)}},
                {new String[] {String.valueOf(1), String.valueOf(2)}}
        };
    }

    @UseDataProvider("parameterTestCases")
    @Test
    public void shouldValidateParametersPassedToTheCommand(String inputX, String inputY, String inputDirection, String message, Robot.State state) {
        robot.setState(OFF_BOARD);

        commandHandler.execute(robot, new String[] {inputX, inputY, inputDirection});
        assertTrue(outContent.toString().contains(message));
        assertEquals(state, robot.getState());
    }

    @DataProvider
    public static Object[][] parameterTestCases() {
        return new Object[][] {
                {"0", "1", "NORTH", "Robot placed at: Position[X: 0, Y: 1, Direction: NORTH]", ON_BOARD},
                {"1", "0", "SOUTH", "Robot placed at: Position[X: 1, Y: 0, Direction: SOUTH]", ON_BOARD},
                {"A", "0", "NORTH", "Ignoring command PLACE, For input string: \"A\"", OFF_BOARD},
                {"1", "B", "NORTH", "Ignoring command PLACE, For input string: \"B\"", OFF_BOARD},
                {"1", "1", "SOMETHING", "Ignoring command PLACE, No enum constant com.rea.test.models.Direction.SOMETHING", OFF_BOARD},
                {"-1", "1", "EAST", "Ignoring command PLACE, X Position should be between 0 and 4", OFF_BOARD},
                {"1", "-1", "WEST", "Ignoring command PLACE, Y Position should be between 0 and 4", OFF_BOARD},
                {"5", "1", "NORTH", "Ignoring command PLACE, X Position should be between 0 and 4", OFF_BOARD},
                {"1", "5", "SOUTH", "Ignoring command PLACE, Y Position should be between 0 and 4", OFF_BOARD},
                {"1", "2", null, "Ignoring command PLACE, Direction cannot be blank", OFF_BOARD},
                {"1", "2", "", "Ignoring command PLACE, Direction cannot be blank", OFF_BOARD}
        };
    }

    @Test
    public void shouldTestGetCommand() {
        assertEquals(PLACE, commandHandler.getCommand());
    }
}
