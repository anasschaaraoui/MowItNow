package mowitnow.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import mowitnow.Exceptions.ParsingException;
import mowitnow.models.Coordonnees;
import mowitnow.models.Pelouse;

class PelouseParserTest {

	/**
     * Tests the parse method of the PelouseParser class with valid input.
     */
    @Test
    void testParse_ValidInput() {
        PelouseParser parser = new PelouseParser();
        
        // Test valid input
        String validInput = "5 5";
        try {
            Pelouse pelouse = parser.parse(validInput);
            assertEquals(new Coordonnees(5, 5), pelouse.getCoinSuperieurDroit(), "Parsed Pelouse dimensions should match expected dimensions");
        } catch (ParsingException e) {
            fail("Parsing should not throw an exception for valid input: " + e.getMessage());
        }
    }

    /**
     * Tests the parse method of the PelouseParser class with invalid input.
     */
    @Test
    void testParse_InvalidInput() {
        PelouseParser parser = new PelouseParser();
        
        // Test invalid input
        String invalidInput = "5X5";
        assertThrows(ParsingException.class, () -> parser.parse(invalidInput), "Parsing should throw ParsingException for invalid input");
    }

    /**
     * Tests the parse method of the PelouseParser class with negative dimensions.
     */
    @Test
    void testParse_NegativeDimensions() {
        PelouseParser parser = new PelouseParser();
        
        // Test input with negative dimensions
        String input = "-5 -5";
        assertThrows(ParsingException.class, () -> parser.parse(input), "Parsing should throw ParsingException for negative dimensions");
    }

    /**
     * Tests the parse method of the PelouseParser class with missing dimensions.
     */
    @Test
    void testParse_MissingDimensions() {
        PelouseParser parser = new PelouseParser();
        
        // Test input with missing dimensions
        String input = "5";
        assertThrows(ParsingException.class, () -> parser.parse(input), "Parsing should throw ParsingException for missing dimensions");
    }
    
    /**
     * Tests the parse method of the PelouseParser class with empty input.
     */
    @Test
    void testParse_EmptyInput() {
        PelouseParser parser = new PelouseParser();
        
        // Test empty input
        String emptyInput = "";
        assertThrows(ParsingException.class, () -> parser.parse(emptyInput),
                "Parsing should throw ParsingException for empty input");
    }

    /**
     * Tests the parse method of the PelouseParser class with null input.
     */
    @Test
    void testParse_NullInput() {
        PelouseParser parser = new PelouseParser();
        
        // Test null input
        String nullInput = null;
        assertThrows(ParsingException.class, () -> parser.parse(nullInput),
                "Parsing should throw ParsingException for null input");
    }

}
