
package com.rea.test.command;

import com.rea.test.models.Command;

import static com.rea.test.Constants.MAX_X_UNITS_ON_BOARD_STARTING_FROM_ZERO;
import static com.rea.test.Constants.MAX_Y_UNITS_ON_BOARD_STARTING_FROM_ZERO;

public abstract class BaseCommandHandler {

    public abstract Command getCommand();

    protected void validatePosition(int x, int y) {
        if (x < 0 || x > MAX_X_UNITS_ON_BOARD_STARTING_FROM_ZERO) {
            throw new IllegalArgumentException("X Position should be between 0 and " + MAX_X_UNITS_ON_BOARD_STARTING_FROM_ZERO);
        }
        if (y < 0 || y > MAX_Y_UNITS_ON_BOARD_STARTING_FROM_ZERO) {
            throw new IllegalArgumentException("Y Position should be between 0 and " + MAX_Y_UNITS_ON_BOARD_STARTING_FROM_ZERO);
        }
    }
}
