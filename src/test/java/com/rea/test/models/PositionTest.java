
package com.rea.test.models;

import org.junit.Before;
import org.junit.Test;

import static com.rea.test.models.Direction.NORTH;
import static com.rea.test.utilities.TestUtilities.assertCanonical;
import static org.junit.Assert.*;

public class PositionTest {

    private Position position;

    @Before
    public void setup() {
        position = new Position(1, 2, NORTH);
    }

    @Test
    public void shouldBeAbleToConstructObjectAndShouldHaveGetterMethods() {

        assertEquals(1, position.getX());
        assertEquals(2, position.getY());
        assertEquals(NORTH, position.getDirection());
    }

    @Test
    public void shouldTestOutputStringFormat() {
        assertEquals("Position[X: 1, Y: 2, Direction: NORTH]", position.toString());
    }

    @Test
    public void shouldAssertCanonical() {
        Position duplicate = new Position(1, 2, NORTH);
        Position other = new Position(2, 2, NORTH);

        assertCanonical(position, duplicate, other);
    }
}
