
package com.rea.test.command;

import com.rea.test.models.Position;
import com.rea.test.models.Robot;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.PrintStream;

import static com.rea.test.models.Command.REPORT;
import static com.rea.test.models.Direction.SOUTH;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(DataProviderRunner.class)
public class ReportCommandHandlerTest extends BaseNoParameterCommandHandlerTest {

    private ReportCommandHandler commandHandler;
    private Robot robot;

    @Before
    public void setup() {
        commandHandler = new ReportCommandHandler();
        super.setup(commandHandler);
        robot = new Robot();
        System.setOut(new PrintStream(getOutContent()));
    }

    @Test
    public void shouldPrintPositionOnREPORTCommand() {
        Position position = new Position(1, 2, SOUTH);
        robot.setState(ON_BOARD);
        robot.setPosition(position);

        commandHandler.execute(robot);

        assertTrue(getOutContent().toString().contains(position.toString()));
    }

    @Test
    public void shouldTestGetCommand() {
        assertEquals(REPORT, commandHandler.getCommand());
    }
}
