
package com.rea.test.command;

import com.rea.test.models.Command;
import com.rea.test.models.Position;
import com.rea.test.models.Robot;

import static com.rea.test.models.Command.MOVE;

public class MoveCommandHandler extends BaseNoParameterCommandHandler {

    public MoveCommandHandler() {
        super(MOVE);
    }

    @Override
    public void executeCommand(Robot robot) {
        try {
            Position expectedNewPosition = getNewPosition(robot.getPosition());
            validatePosition(expectedNewPosition.getX(), expectedNewPosition.getY());

            robot.setPosition(expectedNewPosition);
            System.out.println("Moved");
        } catch (IllegalArgumentException e) {
            System.out.println("Ignoring command MOVE, new position after move is invalid");
        }
    }

    private Position getNewPosition(Position position) {
        return new Position(
                position.getX() + position.getDirection().getUnitsInXDirectionOnStepForward(),
                position.getY() + position.getDirection().getUnitsInYDirectionOnStepForward(),
                position.getDirection());
    }

    @Override
    public Command getCommand() {
        return MOVE;
    }
}
