package mowitnow.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Orientation {
	NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

	private static final Logger logger = LogManager.getLogger(Orientation.class);

	private final String code;

	Orientation(String code) {
		this.code = code;
	}

	/**
	 * Parses the input string into the corresponding Orientation enum constant.
	 * Returns null if the string does not match any known orientation.
	 *
	 * @param code The input string representing the orientation.
	 * @return The Orientation enum constant corresponding to the input string, or
	 *         null if not found.
	 */
	public static Orientation parse(String code) {
		logger.debug("Attempting to parse orientation for code: {}", code);
		for (Orientation orientation : Orientation.values()) {
			if (orientation.code.equals(code)) {
				logger.debug("Orientation parsed successfully for code {}: {}", code, orientation);
				return orientation;
			}
		}
		logger.warn("Unknown orientation code: {}", code);
		return null; // Unknown orientation
	}

	public Orientation turnRight() {
		logger.debug("Turning right from orientation: {}", this);
		Orientation newOrientation = switch (this) {
		case NORTH -> EAST;
		case EAST -> SOUTH;
		case SOUTH -> WEST;
		case WEST -> NORTH;
		};
		logger.debug("Turned right, new orientation: {}", newOrientation);
		return newOrientation;
	}

	public Orientation turnLeft() {
		logger.debug("Turning left from orientation: {}", this);
		Orientation newOrientation = switch (this) {
		case NORTH -> WEST;
		case WEST -> SOUTH;
		case SOUTH -> EAST;
		case EAST -> NORTH;
		};
		logger.debug("Turned left, new orientation: {}", newOrientation);
		return newOrientation;
	}

	@Override
	public String toString() {
		return code;
	}
}
