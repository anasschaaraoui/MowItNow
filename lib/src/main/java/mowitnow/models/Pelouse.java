package mowitnow.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a lawn area defined by the coordinates of its top-right corner.
 */
public class Pelouse {
	private static final Logger logger = LogManager.getLogger(Pelouse.class);

	private Coordonnees coinSuperieurDroit;

	/**
	 * Constructs a Pelouse object with the specified top-right corner coordinates.
	 *
	 * @param coinSuperieurDroit The coordinates of the top-right corner of the
	 *                           lawn.
	 */
	public Pelouse(Coordonnees coinSuperieurDroit) {
		this.coinSuperieurDroit = coinSuperieurDroit;
		logger.info("Pelouse created with top-right corner at {}", coinSuperieurDroit);
	}

	/**
	 * Retrieves the coordinates of the top-right corner of the lawn.
	 *
	 * @return The coordinates of the top-right corner.
	 */
	public Coordonnees getCoinSuperieurDroit() {
		return coinSuperieurDroit;
	}

	/**
	 * Checks if the given coordinates are within the boundaries of the lawn.
	 *
	 * @param coord The coordinates to check.
	 * @return True if the coordinates are within the lawn boundaries, false
	 *         otherwise.
	 */
	public boolean isValidPosition(Coordonnees coord) {
		boolean isValid = coord.getX() >= 0 && coord.getX() <= coinSuperieurDroit.getX() && coord.getY() >= 0
				&& coord.getY() <= coinSuperieurDroit.getY();
		logger.info("Position {} is {} for Pelouse", coord, (isValid ? "valid" : "invalid"));
		return isValid;
	}

	/**
	 * Returns a string representation of the Pelouse object.
	 *
	 * @return A string representation containing the coordinates of the top-right
	 *         corner.
	 */
	@Override
	public String toString() {
		return "Pelouse with top-right corner at " + coinSuperieurDroit;
	}
}
