package mowitnow.models;

import java.util.Objects;

/**
 * Represents a coordinate point on a two-dimensional plane.
 */
public class Coordonnees {
    private int x;
    private int y;

    /**
     * Constructs a new Coordonnees object with the specified x and y coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Coordonnees(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of this Coordonnees object.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this Coordonnees object.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Moves this Coordonnees object horizontally by the specified delta x.
     *
     * @param deltaX The change in the x-coordinate.
     * @return A new Coordonnees object with the updated x-coordinate.
     */
    public Coordonnees moveX(int deltaX) {
        return new Coordonnees(x + deltaX, y);
    }

    /**
     * Moves this Coordonnees object vertically by the specified delta y.
     *
     * @param deltaY The change in the y-coordinate.
     * @return A new Coordonnees object with the updated y-coordinate.
     */
    public Coordonnees moveY(int deltaY) {
        return new Coordonnees(x, y + deltaY);
    }
    
    /**
     * Computes a hash code for this Coordonnees object.
     *
     * @return The hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return true if this Coordonnees object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordonnees other = (Coordonnees) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Returns a string representation of this Coordonnees object.
     *
     * @return The string representation of the Coordonnees object in the format "x y".
     */
    @Override
    public String toString() {
        return x + " " + y;
    }
}
