
package com.rea.test.command;

import com.rea.test.models.Command;
import com.rea.test.models.Robot;

import static com.rea.test.models.Command.LEFT;

public class LeftCommandHandler extends BaseNoParameterCommandHandler {

    public LeftCommandHandler() {
        super(LEFT);
    }

    @Override
    public void executeCommand(Robot robot) {

        robot.turnLeft();
        System.out.println("Turned Left");
    }

    @Override
    public Command getCommand() {
        return LEFT;
    }
}
