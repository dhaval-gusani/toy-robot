
package com.rea.test.command;

import com.rea.test.models.Direction;
import com.rea.test.models.Position;
import com.rea.test.models.Robot;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.PrintStream;

import static com.rea.test.models.Direction.*;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class MoveCommandHandlerTest extends BaseNoParameterCommandHandlerTest {

    private MoveCommandHandler commandHandler;
    private Robot robot;

    @Before
    public void setup() {
        commandHandler = new MoveCommandHandler();
        super.setup(commandHandler);
        robot = new Robot();
        System.setOut(new PrintStream(getOutContent()));
    }

    @UseDataProvider("movementTestCases")
    @Test
    public void shouldTestVariousMovementCases(Position inputPosition, String message, Position expectedPosition) {
        robot.setState(ON_BOARD);
        robot.setPosition(inputPosition);

        commandHandler.executeCommand(robot);

        assertTrue(getOutContent().toString().contains(message));
        assertEquals(expectedPosition, robot.getPosition());
    }

    @DataProvider
    public static Object[][] movementTestCases() {
        return new Object[][] {
                {getPosition(0, 0, EAST), "Moved", getPosition(1, 0, EAST)},
                {getPosition(0, 0, NORTH), "Moved", getPosition(0, 1, NORTH)},
                {getPosition(0, 0, WEST), "Ignoring command MOVE, new position after move is invalid", getPosition(0, 0, WEST)},
                {getPosition(0, 0, SOUTH), "Ignoring command MOVE, new position after move is invalid", getPosition(0, 0, SOUTH)},
                {getPosition(0, 4, EAST), "Moved", getPosition(1, 4, EAST)},
                {getPosition(0, 4, NORTH), "Ignoring command MOVE, new position after move is invalid", getPosition(0, 4, NORTH)},
                {getPosition(0, 4, WEST), "Ignoring command MOVE, new position after move is invalid", getPosition(0, 4, WEST)},
                {getPosition(0, 4, SOUTH), "Moved", getPosition(0, 3, SOUTH)},
                {getPosition(4, 0, EAST), "Ignoring command MOVE, new position after move is invalid", getPosition(4, 0, EAST)},
                {getPosition(4, 0, NORTH), "Moved", getPosition(4, 1, NORTH)},
                {getPosition(4, 0, WEST), "Moved", getPosition(3, 0, WEST)},
                {getPosition(4, 0, SOUTH), "Ignoring command MOVE, new position after move is invalid", getPosition(4, 0, SOUTH)},
                {getPosition(4, 4, EAST), "Ignoring command MOVE, new position after move is invalid", getPosition(4, 4, EAST)},
                {getPosition(4, 4, NORTH), "Ignoring command MOVE, new position after move is invalid", getPosition(4, 4, NORTH)},
                {getPosition(4, 4, WEST), "Moved", getPosition(3, 4, WEST)},
                {getPosition(4, 4, SOUTH), "Moved", getPosition(4, 3, SOUTH)},
                {getPosition(2, 0, NORTH), "Moved", getPosition(2, 1, NORTH)},
                {getPosition(2, 0, SOUTH), "Ignoring command MOVE, new position after move is invalid", getPosition(2, 0, SOUTH)},
                {getPosition(2, 4, NORTH), "Ignoring command MOVE, new position after move is invalid", getPosition(2, 4, NORTH)},
                {getPosition(2, 4, SOUTH), "Moved", getPosition(2, 3, SOUTH)},
                {getPosition(0, 2, EAST), "Moved", getPosition(1, 2, EAST)},
                {getPosition(0, 2, WEST), "Ignoring command MOVE, new position after move is invalid", getPosition(0, 2, WEST)},
                {getPosition(4, 2, EAST), "Ignoring command MOVE, new position after move is invalid", getPosition(4, 2, EAST)},
                {getPosition(4, 2, WEST), "Moved", getPosition(3, 2, WEST)}
        };
    }

    private static Position getPosition(int x, int y, Direction direction) {
        return new Position(x, y, direction);
    }
}

