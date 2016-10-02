
package com.rea.test.models;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

public enum Direction {
    EAST(0, 1, 0), NORTH(90, 0, 1), WEST(180, -1, 0), SOUTH(270, 0, -1);

    private int degrees;
    private int unitsInXDirectionOnStepForward;
    private int unitsInYDirectionOnStepForward;
    private static final Map<Integer, Direction> directions = new HashMap<>(values().length);
    private static final int DEGREES_CHANGE_PER_TURN = 90;

    static {
        stream(values()).forEach(direction -> directions.put(direction.getDegrees(), direction));
    }

    Direction(int degrees, int unitsInXDirectionOnStepForward, int unitsInYDirectionOnStepForward) {
        this.degrees = degrees;
        this.unitsInXDirectionOnStepForward = unitsInXDirectionOnStepForward;
        this.unitsInYDirectionOnStepForward = unitsInYDirectionOnStepForward;
    }

    public int getDegrees() {
        return degrees;
    }

    public int getUnitsInXDirectionOnStepForward() {
        return unitsInXDirectionOnStepForward;
    }

    public int getUnitsInYDirectionOnStepForward() {
        return unitsInYDirectionOnStepForward;
    }

    public Direction getDirectionOnLeft() {
        return getDirectionFromDegrees(degrees + DEGREES_CHANGE_PER_TURN);
    }

    public Direction getDirectionOnRight() {
        int degreesToAvoidNegatives = degrees == 0 ? 360 : degrees;
        return getDirectionFromDegrees(degreesToAvoidNegatives - DEGREES_CHANGE_PER_TURN);
    }

    private Direction getDirectionFromDegrees(int degrees) {
        int degreesToLookup = degrees % 360;
        if (directions.get(degreesToLookup) != null) {
            return directions.get(degreesToLookup);
        }
        throw new IllegalArgumentException("Not able to find direction for degrees: " + degreesToLookup);
    }
}
