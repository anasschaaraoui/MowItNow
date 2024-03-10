package mowitnow.common;

import mowitnow.models.Pelouse;

/**
 * An interface representing a walkable entity, such as a tondeuse,
 * capable of turning left, turning right, and moving forward.
 */
public interface Walkable {
    /**
     * Turns the entity to the left.
     */
    void turnLeft();

    /**
     * Turns the entity to the right.
     */
    void turnRight();

    /**
     * Moves the entity forward.
     */
    void moveForward(Pelouse pelouse);
}
