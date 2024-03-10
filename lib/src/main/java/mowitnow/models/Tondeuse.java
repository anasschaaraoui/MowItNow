package mowitnow.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mowitnow.common.Walkable;
import mowitnow.enums.Orientation;

public class Tondeuse implements Walkable {

	private static final Logger logger = LogManager.getLogger(Tondeuse.class);
    private static final String MOVE_FORWARD_LOG = "Tondeuse moved forward to position {}";
    
	private Coordonnees position;
	private Orientation orientation;
	private List<Command> commands;

	/**
     * Constructs a Tondeuse with the specified initial position and orientation.
     *
     * @param position    The initial position of the Tondeuse.
     * @param orientation The initial orientation of the Tondeuse.
     */
	public Tondeuse(Coordonnees position, Orientation orientation) {
		this.position = position;
		this.orientation = orientation;
		this.commands = new ArrayList<>();
        logger.info("Tondeuse created at position {} with orientation {}", position, orientation);
	}

	/**
     * Adds a command to be executed by the Tondeuse.
     *
     * @param command The command to be added.
     */
	public void addCommand(Command command) {
		commands.add(command);
        logger.info("Command added: {}", command);
	}

	/**
     * Executes all the commands added to the Tondeuse.
     */
	public void executeCommands() {
		for (Command command : commands) {
			command.execute(this);
            logger.info("Command executed: {}", command);
		}
	}

	/**
     * Retrieves the current position of the Tondeuse.
     *
     * @return The current position of the Tondeuse.
     */
	public Coordonnees getPosition() {
		return position;
	}

	/**
     * Retrieves the current orientation of the Tondeuse.
     *
     * @return The current orientation of the Tondeuse.
     */
	public Orientation getOrientation() {
		return orientation;
	}
	
	/**
     * Retrieves the list of commands added to the Tondeuse.
     *
     * @return The list of commands added to the Tondeuse.
     */
	public List<Command> getCommands() {
		return commands;
	}

	/**
     * Turns the Tondeuse to the right (clockwise).
     */
	public void turnRight() {
		orientation = orientation.turnRight();
        logger.info("Tondeuse turned right. New orientation: {}", orientation);
	}

	/**
     * Turns the Tondeuse to the left (counter-clockwise).
     */
	public void turnLeft() {
		orientation = orientation.turnLeft();
        logger.info("Tondeuse turned left. New orientation: {}", orientation);
	}

	/**
     * Moves the Tondeuse forward by one unit in the direction it is facing.
     *
     * @param pelouse The lawn on which the Tondeuse is moving.
     */
	public void moveForward(Pelouse pelouse) {
		switch (orientation) {
			case NORTH -> {
				Coordonnees newPosition = position.moveY(1);
				if (pelouse.isValidPosition(newPosition)) {
					position = newPosition;
                    logger.info(MOVE_FORWARD_LOG, position);
				}
			}
			case EAST -> {
				Coordonnees newPosition = position.moveX(1);
				if (pelouse.isValidPosition(newPosition)) {
					position = newPosition;
                    logger.info(MOVE_FORWARD_LOG, position);
				}
			}
			case SOUTH -> {
				Coordonnees newPosition = position.moveY(-1);
				if (pelouse.isValidPosition(newPosition)) {
					position = newPosition;
                    logger.info(MOVE_FORWARD_LOG, position);
				}
			}
			case WEST -> {
				Coordonnees newPosition = position.moveX(-1);
				if (pelouse.isValidPosition(newPosition)) {
					position = newPosition;
                    logger.info(MOVE_FORWARD_LOG, position);
				}
			}
		}
	}
	
	/**
     * Generates the hash code for the Tondeuse based on its position and orientation.
     *
     * @return The hash code value for the Tondeuse.
     */
	@Override
    public int hashCode() {
        return Objects.hash(position, orientation);
    }

	/**
     * Checks if the Tondeuse is equal to another object. Two Tondeuses are considered equal if
     * they have the same position and orientation.
     *
     * @param obj The object to compare with.
     * @return True if the Tondeuses are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tondeuse other = (Tondeuse) obj;
        return Objects.equals(position, other.position) &&
               Objects.equals(orientation, other.orientation);
    }
	
	@Override
	public String toString() {
		return getPosition() + " " + getOrientation();
	}
}
