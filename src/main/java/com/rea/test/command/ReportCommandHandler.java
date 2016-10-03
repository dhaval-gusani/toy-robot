
package com.rea.test.command;

import com.rea.test.models.Command;
import com.rea.test.models.Robot;

import static com.rea.test.models.Command.REPORT;

public class ReportCommandHandler extends BaseNoParameterCommandHandler {

    public ReportCommandHandler() {
        super(REPORT);
    }

    @Override
    public void executeCommand(Robot robot) {

        System.out.println(robot.getPosition());
    }

    @Override
    public Command getCommand() {
        return REPORT;
    }
}
