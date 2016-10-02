
package com.rea.test.models;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rea.test.models.Direction.*;
import static org.junit.Assert.*;

@RunWith(DataProviderRunner.class)
public class DirectionTest {

    @UseDataProvider("directionOnLeftCases")
    @Test
    public void shouldBeAbleToGetDirectionOnLeft(Direction inputDirection, Direction expectedDirection) {
        assertEquals(expectedDirection, inputDirection.getDirectionOnLeft());
    }

    @UseDataProvider("directionOnRightCases")
    @Test
    public void shouldBeAbleToGetDirectionOnRight(Direction inputDirection, Direction expectedDirection) {
        assertEquals(expectedDirection, inputDirection.getDirectionOnRight());
    }

    @UseDataProvider("unitsOnXAndYOnStepForward")
    @Test
    public void shouldHaveCorrectUnitsOnXAndYForStepForward(Direction inputDirection, int unitX, int unitY) {
        assertEquals(unitX, inputDirection.getUnitsInXDirectionOnStepForward());
        assertEquals(unitY, inputDirection.getUnitsInYDirectionOnStepForward());
    }

    @DataProvider
    public static Object[][] directionOnLeftCases() {
        return new Direction[][] {
                {EAST, NORTH},
                {NORTH, WEST},
                {WEST, SOUTH},
                {SOUTH, EAST}
        };
    }

    @DataProvider
    public static Object[][] directionOnRightCases() {
        return new Direction[][] {
                {EAST, SOUTH},
                {SOUTH, WEST},
                {WEST, NORTH},
                {NORTH, EAST}
        };
    }

    @DataProvider
    public static Object[][]  unitsOnXAndYOnStepForward() {
        return new Object[][] {
                {EAST, 1, 0},
                {NORTH, 0, 1},
                {WEST, -1, 0},
                {SOUTH, 0, -1}
        };
    }
}
