
package com.rea.test.command;

import com.rea.test.models.Command;
import com.rea.test.models.Robot;

import static com.rea.test.models.Command.RIGHT;

public class RightCommandHandler extends BaseNoParameterCommandHandler {

    public RightCommandHandler() {
        super(RIGHT);
    }

    @Override
    public void executeCommand(Robot robot) {

        robot.turnRight();
        System.out.println("Turned Right");
    }

    @Override
    public Command getCommand() {
        return RIGHT;
    }
}
