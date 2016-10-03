
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

import static com.rea.test.models.Command.RIGHT;
import static com.rea.test.models.Direction.*;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class RightCommandHandlerTest extends BaseNoParameterCommandHandlerTest {

    private RightCommandHandler commandHandler;
    private Robot robot;

    @Before
    public void setup() {
        commandHandler = new RightCommandHandler();
        super.setup(commandHandler);
        robot = new Robot();
        System.setOut(new PrintStream(getOutContent()));
    }

    @UseDataProvider("directionOnRightCases")
    @Test
    public void shouldTurnRightOnGettingRIGHTCommand(Direction inputDirection, Direction expectedDirection) {
        robot.setState(ON_BOARD);
        robot.setPosition(new Position(1, 2, inputDirection));

        commandHandler.execute(robot);

        assertEquals(expectedDirection, robot.getPosition().getDirection());
        assertTrue(getOutContent().toString().contains("Turned Right"));
    }

    @DataProvider
    public static Object[][] directionOnRightCases() {
        return new Direction[][] {
                {EAST, SOUTH},
                {SOUTH, WEST},
                {WEST, NORTH},
                {NORTH, EAST}
        };
    }

    @Test
    public void shouldTestGetCommand() {
        assertEquals(RIGHT, commandHandler.getCommand());
    }
}

