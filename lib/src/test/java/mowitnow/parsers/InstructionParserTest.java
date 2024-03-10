package mowitnow.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import mowitnow.Exceptions.ParsingException;
import mowitnow.enums.Instruction;

class InstructionParserTest {

	/**
	 * Tests the parse method of the InstructionParser class with valid input.
	 */
	@Test
	void testParse_ValidInput() {
		InstructionParser parser = new InstructionParser();

		// Test valid input
		String validInput = "GADAGD";
		try {
			List<Instruction> instructions = parser.parse(validInput);
			assertEquals(
					List.of(Instruction.LEFT, Instruction.FORWARD, Instruction.RIGHT, Instruction.FORWARD,
							Instruction.LEFT, Instruction.RIGHT),
					instructions, "Parsed instructions should match expected instructions");
		} catch (ParsingException e) {
			fail("Parsing should not throw an exception for valid input: " + e.getMessage());
		}
	}

	/**
	 * Tests the parse method of the InstructionParser class with invalid input.
	 */
	@Test
	void testParse_InvalidInput() {
		InstructionParser parser = new InstructionParser();

		// Test invalid input
		String invalidInput = "GADBXG";
		assertThrows(ParsingException.class, () -> parser.parse(invalidInput),
				"Parsing should throw ParsingException for invalid input");
	}

	/**
	 * Tests the parse method of the InstructionParser class with empty input.
	 */
	@Test
	void testParse_EmptyInput() {
		InstructionParser parser = new InstructionParser();

		// Test empty input
		String emptyInput = "";
		assertThrows(ParsingException.class, () -> parser.parse(emptyInput),
				"Parsing should throw ParsingException for empty input");
	}

	/**
	 * Tests the parse method of the InstructionParser class with input containing
	 * only spaces.
	 */
	@Test
	void testParse_SpacesInput() {
		InstructionParser parser = new InstructionParser();

		// Test input containing only spaces
		String spacesInput = "   ";
		assertThrows(ParsingException.class, () -> parser.parse(spacesInput),
				"Parsing should throw ParsingException for input containing only spaces");
	}

	/**
	 * Tests the parse method of the InstructionParser class with input containing
	 * non-ASCII characters.
	 */
	@Test
	void testParse_NonAsciiInput() {
		InstructionParser parser = new InstructionParser();

		// Test input containing non-ASCII characters
		String nonAsciiInput = "GADADG€";
		assertThrows(ParsingException.class, () -> parser.parse(nonAsciiInput),
				"Parsing should throw ParsingException for input containing non-ASCII characters");
	}

}
