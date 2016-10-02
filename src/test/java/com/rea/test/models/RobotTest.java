
package com.rea.test.models;

import org.junit.Before;
import org.junit.Test;

import static com.rea.test.models.Direction.SOUTH;
import static com.rea.test.models.Robot.State.OFF_BOARD;
import static com.rea.test.models.Robot.State.ON_BOARD;
import static org.junit.Assert.*;

public class RobotTest {

    private Robot robot;

    @Before
    public void setup() {
        robot = new Robot();
    }

    @Test
    public void shouldTestGettersAndSetters() {
        robot.setState(ON_BOARD);
        robot.setPosition(new Position(1, 2, SOUTH));

        assertEquals(ON_BOARD, robot.getState());
        assertEquals(new Position(1, 2, SOUTH), robot.getPosition());
    }

    @Test
    public void shouldDefaultToOffBoardOnNewInstanceCreation() {
        assertEquals(OFF_BOARD, robot.getState());
    }
}

