package mowitnow.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import mowitnow.enums.Orientation;

/**
 * Test class for the Tondeuse class.
 */
class TondeuseTest {

    /**
     * Tests the Tondeuse constructor.
     */
    @Test
    void testTondeuse() {
        // Create a Tondeuse at position (1, 2) facing North
        Coordonnees position = new Coordonnees(1, 2);
        Orientation orientation = Orientation.NORTH;
        Tondeuse tondeuse = new Tondeuse(position, orientation);
        
        // Ensure the position and orientation are correctly set
        assertEquals(position, tondeuse.getPosition(), "Position should be (1, 2)");
        assertEquals(orientation, tondeuse.getOrientation(), "Orientation should be North");
    }

    /**
     * Tests the addCommand method of the Tondeuse class.
     */
    @Test
    void testAddCommand() {
        // Create a Tondeuse
        Tondeuse tondeuse = new Tondeuse(new Coordonnees(1, 2), Orientation.NORTH);
        
        // Add a command
        Command command = new Command(Tondeuse::turnLeft);
        tondeuse.addCommand(command);
        
        // Ensure the command is added successfully
        List<Command> expectedCommands = new ArrayList<>();
        expectedCommands.add(command);
        assertEquals(expectedCommands, tondeuse.getCommands(), "Command should be added successfully");
    }

    /**
     * Tests the executeCommands method of the Tondeuse class.
     */
    @Test
    void testExecuteCommands() {
        // Create a Tondeuse
        Tondeuse tondeuse = new Tondeuse(new Coordonnees(1, 2), Orientation.NORTH);
        
        // Add commands to turn left and move forward
        tondeuse.addCommand(new Command(Tondeuse::turnLeft));
        tondeuse.addCommand(new Command(tondeuse1 -> tondeuse1.moveForward(new Pelouse(new Coordonnees(5, 5)))));
        
        // Execute commands
        tondeuse.executeCommands();
        
        // Ensure the position and orientation are updated correctly after executing commands
        assertEquals(new Coordonnees(0, 2), tondeuse.getPosition(), "Position should be (1, 3)");
        assertEquals(Orientation.WEST, tondeuse.getOrientation(), "Orientation should be West");
    }

    /**
     * Tests the getPosition method of the Tondeuse class.
     */
    @Test
    void testGetPosition() {
        // Create a Tondeuse at position (3, 4) facing South
        Coordonnees position = new Coordonnees(3, 4);
        Tondeuse tondeuse = new Tondeuse(position, Orientation.SOUTH);
        
        // Ensure the getPosition method returns the correct position
        assertEquals(position, tondeuse.getPosition(), "Position should be (3, 4)");
    }

    /**
     * Tests the getOrientation method of the Tondeuse class.
     */
    @Test
    void testGetOrientation() {
        // Create a Tondeuse facing East
        Orientation orientation = Orientation.EAST;
        Tondeuse tondeuse = new Tondeuse(new Coordonnees(0, 0), orientation);
        
        // Ensure the getOrientation method returns the correct orientation
        assertEquals(orientation, tondeuse.getOrientation(), "Orientation should be East");
    }

    /**
     * Tests the turnRight method of the Tondeuse class.
     */
    @Test
    void testTurnRight() {
        // Create a Tondeuse facing North
        Tondeuse tondeuse = new Tondeuse(new Coordonnees(0, 0), Orientation.NORTH);
        
        // Turn right
        tondeuse.turnRight();
        
        // Ensure the orientation is updated correctly
        assertEquals(Orientation.EAST, tondeuse.getOrientation(), "Orientation should be East after turning right");
    }

    /**
     * Tests the turnLeft method of the Tondeuse class.
     */
    @Test
    void testTurnLeft() {
        // Create a Tondeuse facing West
        Tondeuse tondeuse = new Tondeuse(new Coordonnees(0, 0), Orientation.WEST);
        
        // Turn left
        tondeuse.turnLeft();
        
        // Ensure the orientation is updated correctly
        assertEquals(Orientation.SOUTH, tondeuse.getOrientation(), "Orientation should be South after turning left");
    }

    /**
     * Tests the moveForward method of the Tondeuse class.
     */
    @Test
    void testMoveForward() {
        // Create a Tondeuse at position (2, 3) facing East
        Tondeuse tondeuse = new Tondeuse(new Coordonnees(2, 3), Orientation.EAST);
        Pelouse pelouse = new Pelouse(new Coordonnees(5, 5));
        
        // Move forward
        tondeuse.moveForward(pelouse);
        
        // Ensure the position is updated correctly
        assertEquals(new Coordonnees(3, 3), tondeuse.getPosition(), "Position should be (3, 3) after moving forward");
    }
}

