package mowitnow.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PelouseTest {

	/**
     * Tests the Pelouse constructor.
     */
    @Test
    void testPelouse() {
        // Create a Pelouse with corner coordinates (5, 5)
        Coordonnees coinSuperieurDroit = new Coordonnees(5, 5);
        Pelouse pelouse = new Pelouse(coinSuperieurDroit);
        
        // Ensure the corner coordinates are correctly set
        assertEquals(coinSuperieurDroit, pelouse.getCoinSuperieurDroit());
    }

    /**
     * Tests the isValidPosition method of the Pelouse class.
     */
    @Test
    void testIsValidPosition() {
        // Create a Pelouse with corner coordinates (5, 5)
        Coordonnees coinSuperieurDroit = new Coordonnees(5, 5);
        Pelouse pelouse = new Pelouse(coinSuperieurDroit);
        
        // Test valid positions within the bounds of the pelouse
        assertTrue(pelouse.isValidPosition(new Coordonnees(0, 0)));
        assertTrue(pelouse.isValidPosition(new Coordonnees(4, 4)));
        assertTrue(pelouse.isValidPosition(new Coordonnees(2, 3)));
        
        // Test invalid positions outside the bounds of the pelouse
        assertFalse(pelouse.isValidPosition(new Coordonnees(-1, 0)));
        assertFalse(pelouse.isValidPosition(new Coordonnees(6, 5)));
        assertFalse(pelouse.isValidPosition(new Coordonnees(3, -2)));
    }

}
