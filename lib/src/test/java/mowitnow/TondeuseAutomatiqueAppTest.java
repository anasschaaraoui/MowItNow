package mowitnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import mowitnow.Exceptions.ParsingException;
import mowitnow.enums.Orientation;
import mowitnow.models.Coordonnees;
import mowitnow.models.Tondeuse;

class TondeuseAutomatiqueAppTest {

	/**
	 * Test case to verify that the application prints usage instructions when no arguments are provided.
	 */
	@Test
	void testMain_InvalidArgs() {
		// Test with no arguments provided
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String[] args = {};
		TondeuseAutomatiqueApp.main(args);
		assertEquals("Usage: java Main <file_path>".trim(), outContent.toString().trim());
		System.setOut(System.out);
	}

	/**
	 * Test case to verify that the application prints an error message when the specified file is not found.
	 */
	@Test
	void testMain_FileNotFound() {
		// Test with non-existing file path
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String[] args = { "../non_existing_file.txt" };
		TondeuseAutomatiqueApp.main(args);
		assertEquals("File not found: ../non_existing_file.txt", outContent.toString().trim());
		System.setOut(System.out);
	}

	/**
	 * Test case to verify that the application prints a parsing exception when the input file has invalid content.
	 */
	@Test
	void testMain_ParsingException() {
		// Test with invalid file content (parsing exception)
		
	    final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	    System.setErr(new PrintStream(errContent));
		String[] args = { "./src/test/resources/invalid_input.txt" };
		TondeuseAutomatiqueApp.main(args);
		assertTrue(errContent.toString().contains("Tondeuse position doesn't matche the X Y (N|E|S|W) pattern"));
	    System.setErr(System.err);
	}

	/**
	 * Test case to verify the processing of a valid input file.
	 */
	@Test
	void testMain_ValidInput() throws FileNotFoundException {
		// Test with valid input file
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		String[] args = { "./src/test/resources/valid_input.txt" };
		TondeuseAutomatiqueApp.main(args);		
		assertTrue(outContent.toString().contains("1 3 N\r\n5 1 E"));
		System.setOut(System.out);
	}

	/**
	 * Test case to verify the processing of an input file with valid content.
	 */
	@Test
	void testProcessInputFile() throws FileNotFoundException, ParsingException {
		// Test with valid input file
		List<Tondeuse> expectedResults = Arrays.asList(new Tondeuse(new Coordonnees(1, 3), Orientation.NORTH),
				new Tondeuse(new Coordonnees(5, 1), Orientation.EAST));
		String filePath = "./src/test/resources/valid_input.txt";
		List<Tondeuse> results = TondeuseAutomatiqueApp.processInputFile(filePath);
		assertEquals(expectedResults, results);
	}

	/**
	 * Test case to verify that a FileNotFoundException is thrown when the specified file is not found.
	 */
	@Test
	void testProcessInputFile_FileNotFound() {
		// Test with non-existing file path
		assertThrows(FileNotFoundException.class,
				() -> TondeuseAutomatiqueApp.processInputFile("non_existing_file.txt"));
	}

	/**
	 * Test case to verify that a ParsingException is thrown when the input file has invalid content.
	 */
	@Test
	void testProcessInputFile_ParsingException() {
		// Test with invalid file content (parsing exception)
		assertThrows(ParsingException.class, () -> TondeuseAutomatiqueApp.processInputFile("./src/test/resources/invalid_input.txt"));
	}
}
