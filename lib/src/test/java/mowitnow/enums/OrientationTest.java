package mowitnow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class OrientationTest {

    @Test
    void testOrientation() {
        // Test enum values
        assertEquals("N", Orientation.NORTH.toString());
        assertEquals("E", Orientation.EAST.toString());
        assertEquals("S", Orientation.SOUTH.toString());
        assertEquals("W", Orientation.WEST.toString());
    }

    @Test
    void testParse() {
        // Test valid input
        assertEquals(Orientation.NORTH, Orientation.parse("N"));
        assertEquals(Orientation.EAST, Orientation.parse("E"));
        assertEquals(Orientation.SOUTH, Orientation.parse("S"));
        assertEquals(Orientation.WEST, Orientation.parse("W"));

        // Test invalid input
        assertNull(Orientation.parse("X")); // Non-existing orientation
        assertNull(Orientation.parse(""));  // Empty string
        assertNull(Orientation.parse(null)); // Null input
    }

    @Test
    void testTurnRight() {
        assertEquals(Orientation.EAST, Orientation.NORTH.turnRight());
        assertEquals(Orientation.SOUTH, Orientation.EAST.turnRight());
        assertEquals(Orientation.WEST, Orientation.SOUTH.turnRight());
        assertEquals(Orientation.NORTH, Orientation.WEST.turnRight());
    }

    @Test
    void testTurnLeft() {
        assertEquals(Orientation.WEST, Orientation.NORTH.turnLeft());
        assertEquals(Orientation.SOUTH, Orientation.WEST.turnLeft());
        assertEquals(Orientation.EAST, Orientation.SOUTH.turnLeft());
        assertEquals(Orientation.NORTH, Orientation.EAST.turnLeft());
    }

    @Test
    void testToString() {
        assertEquals("N", Orientation.NORTH.toString());
        assertEquals("E", Orientation.EAST.toString());
        assertEquals("S", Orientation.SOUTH.toString());
        assertEquals("W", Orientation.WEST.toString());
    }
}
