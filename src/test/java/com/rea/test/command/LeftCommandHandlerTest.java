
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

import static com.rea.test.models.Command.LEFT;
import static com.rea.test.models.Direction.*;
import static com.rea.test.models.Direction.EAST;
import static com.rea.test.models.Direction.SOUTH;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class LeftCommandHandlerTest extends BaseNoParameterCommandHandlerTest {

    private LeftCommandHandler commandHandler;
    private Robot robot;

    @Before
    public void setup() {
        commandHandler = new LeftCommandHandler();
        super.setup(commandHandler);
        robot = new Robot();
        System.setOut(new PrintStream(getOutContent()));
    }

    @UseDataProvider("directionOnLeftCases")
    @Test
    public void shouldTurnLeftOnGettingLEFTCommand(Direction inputDirection, Direction expectedDirection) {
        robot.setState(ON_BOARD);
        robot.setPosition(new Position(1, 2, inputDirection));

        commandHandler.execute(robot);

        assertEquals(expectedDirection, robot.getPosition().getDirection());
        assertTrue(getOutContent().toString().contains("Turned Left"));
    }

    @DataProvider
    public static Object[][] directionOnLeftCases() {
        return new Direction[][] {
                {EAST, NORTH},
                {NORTH, WEST},
                {WEST, SOUTH},
                {SOUTH, EAST}
        };
    }

    @Test
    public void shouldTestGetCommand() {
        assertEquals(LEFT, commandHandler.getCommand());
    }

    protected LeftCommandHandler getCommandHandler() {
        return new LeftCommandHandler();
    }
}

