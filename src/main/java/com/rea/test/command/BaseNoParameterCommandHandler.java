
package com.rea.test.command;

import com.rea.test.models.Command;
import com.rea.test.models.Robot;

public abstract class BaseNoParameterCommandHandler extends BaseCommandHandler {

    private Command command;

    public BaseNoParameterCommandHandler(Command command) {
        this.command = command;
    }

    public void execute(Robot robot) {
        switch (robot.getState()) {
            case OFF_BOARD:
                System.out.println(String.format("Ignoring command %s, Robot is not on board", command));
                break;
            case ON_BOARD:
                executeCommand(robot);
        }
    }

    public abstract void executeCommand(Robot robot);
}
