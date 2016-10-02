
package com.rea.test.models;

import static com.rea.test.models.Robot.State.OFF_BOARD;

public class Robot {

    private State state;
    private Position position;

    public Robot() {
        state = OFF_BOARD;
    }

    public enum State {
        ON_BOARD, OFF_BOARD
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
