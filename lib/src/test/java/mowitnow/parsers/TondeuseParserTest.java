package mowitnow.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import mowitnow.Exceptions.ParsingException;
import mowitnow.enums.Orientation;
import mowitnow.models.Coordonnees;
import mowitnow.models.Tondeuse;

class TondeuseParserTest {

	/**
     * Tests the parse method of the TondeuseParser class with valid input.
     */
    @Test
    void testParse_ValidInput() {
        TondeuseParser parser = new TondeuseParser();
        
        // Test valid input
        String validInput = "3 4 N";
        try {
            Tondeuse tondeuse = parser.parse(validInput);
            assertEquals(new Coordonnees(3, 4), tondeuse.getPosition(), "Parsed Tondeuse position should match expected position");
            assertEquals(Orientation.NORTH, tondeuse.getOrientation(), "Parsed Tondeuse orientation should match expected orientation");
        } catch (ParsingException e) {
            fail("Parsing should not throw an exception for valid input: " + e.getMessage());
        }
    }

    /**
     * Tests the parse method of the TondeuseParser class with invalid input.
     */
    @Test
    void testParse_InvalidInput() {
        TondeuseParser parser = new TondeuseParser();
        
        // Test invalid input
        String invalidInput = "3 X N";
        assertThrows(ParsingException.class, () -> parser.parse(invalidInput), "Parsing should throw ParsingException for invalid input");
    }

    /**
     * Tests the parse method of the TondeuseParser class with missing parts in the input.
     */
    @Test
    void testParse_MissingParts() {
        TondeuseParser parser = new TondeuseParser();
        
        // Test input with missing parts
        String input = "3 4";
        assertThrows(ParsingException.class, () -> parser.parse(input), "Parsing should throw ParsingException for missing parts");
    }
    
    /**
     * Tests the parse method of the TondeuseParser class with empty input.
     */
    @Test
    void testParse_EmptyInput() {
        TondeuseParser parser = new TondeuseParser();

        // Test empty input
        String emptyInput = "";
        assertThrows(ParsingException.class, () -> parser.parse(emptyInput),
                "Parsing should throw ParsingException for empty input");
    }

    /**
     * Tests the parse method of the TondeuseParser class with null input.
     */
    @Test
    void testParse_NullInput() {
        TondeuseParser parser = new TondeuseParser();

        // Test null input
        String nullInput = null;
        assertThrows(ParsingException.class, () -> parser.parse(nullInput),
                "Parsing should throw ParsingException for null input");
    }

    /**
     * Tests the parse method of the TondeuseParser class with input containing negative coordinates.
     */
    @Test
    void testParse_NegativeCoordinates() {
        TondeuseParser parser = new TondeuseParser();

        // Test input with negative coordinates
        String input = "-3 -4 N";
        assertThrows(ParsingException.class, () -> parser.parse(input),
                "Parsing should throw ParsingException for input containing negative coordinates");
    }
}
