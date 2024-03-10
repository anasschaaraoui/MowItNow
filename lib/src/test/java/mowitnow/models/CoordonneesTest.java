package mowitnow.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CoordonneesTest {

	@Test
	void testCoordonnees() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		assertEquals(3, coordonnees.getX());
		assertEquals(5, coordonnees.getY());
	}

	@Test
	void testGetX() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		assertEquals(3, coordonnees.getX());
	}

	@Test
	void testGetY() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		assertEquals(5, coordonnees.getY());
	}

	@Test
	void testMoveX() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		Coordonnees newCoordonnees = coordonnees.moveX(2);
		assertEquals(5, newCoordonnees.getX());
		assertEquals(5, newCoordonnees.getY());
	}

	@Test
	void testMoveY() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		Coordonnees newCoordonnees = coordonnees.moveY(-2);
		assertEquals(3, newCoordonnees.getX());
		assertEquals(3, newCoordonnees.getY());
	}

	@Test
	void testToString() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		assertEquals("3 5", coordonnees.toString());
	}

	@Test
	void testObject() {
		Coordonnees coordonnees1 = new Coordonnees(3, 5);
		Coordonnees coordonnees2 = new Coordonnees(3, 5);
		Coordonnees coordonnees3 = new Coordonnees(2, 4);
		assertTrue(coordonnees1.equals(coordonnees2));
		assertFalse(coordonnees1.equals(coordonnees3));
	}

	@Test
	void testGetClass() {
		Coordonnees coordonnees = new Coordonnees(3, 5);
		assertEquals(Coordonnees.class, coordonnees.getClass());
	}

	@Test
	void testHashCode() {
		Coordonnees coordonnees1 = new Coordonnees(3, 5);
		Coordonnees coordonnees2 = new Coordonnees(3, 5);
		assertEquals(coordonnees1.hashCode(), coordonnees2.hashCode());
	}

}
