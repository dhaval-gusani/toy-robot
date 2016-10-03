
package com.rea.test.command;

import com.rea.test.models.Command;
import com.rea.test.models.Direction;
import com.rea.test.models.Position;
import com.rea.test.models.Robot;

import static com.rea.test.models.Command.PLACE;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class PlaceCommandHandler extends BaseParameterCommandHandler {

    @Override
    public void execute(Robot robot, String[] parameters) {
        switch (robot.getState()) {
            case OFF_BOARD:
                executeCommand(robot, parameters);
                break;
            case ON_BOARD:
                System.out.println(String.format("Ignoring command PLACE, Robot is already on the board at %s", robot.getPosition()));
        }
    }

    private void executeCommand(Robot robot, String[] parameters) {
        try {
            Position position = validateParameters(parameters);
            robot.setPosition(position);

            robot.setState(ON_BOARD);
            System.out.println("Robot placed at: " + position);
        } catch (IllegalArgumentException e) {
            System.out.println("Ignoring command PLACE, " + e.getMessage());
        }
    }

    private Position validateParameters(String[] parameters) {
        if (parameters == null || parameters.length != 3) {
            throw new IllegalArgumentException("Should pass 3 parameters for PLACE Command, [positionX, positionY, direction]");
        }

        try {
            int x = parseInt(parameters[0]);
            int y = parseInt(parameters[1]);
            Direction direction;

            if (isBlank(parameters[2])) {
                throw new IllegalArgumentException("Direction cannot be blank");
            } else {
                direction = Direction.valueOf(parameters[2]);
            }

            validatePosition(x, y);

            return new Position(x, y, direction);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public Command getCommand() {
        return PLACE;
    }
}
