
package com.rea.test.command;

import com.rea.test.models.Robot;

public abstract class BaseParameterCommandHandler extends BaseCommandHandler {

    public abstract void execute(Robot robot, String[] parameters);
}
