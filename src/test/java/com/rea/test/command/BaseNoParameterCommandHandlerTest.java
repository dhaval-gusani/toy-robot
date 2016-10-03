
package com.rea.test.command;

import com.rea.test.models.Robot;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public abstract class BaseNoParameterCommandHandlerTest {

    private BaseNoParameterCommandHandler commandHandler;
    private Robot robot;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    protected void setup(BaseNoParameterCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        robot = new Robot();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldExecuteCommandOnlyIfOnBoard() {
        commandHandler.execute(robot);

        assertTrue(outContent.toString().contains("Ignoring command " + commandHandler.getCommand() + ", Robot is not on board"));
    }

    protected ByteArrayOutputStream getOutContent() {
        return outContent;
    }
}
