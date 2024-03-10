package mowitnow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mowitnow.Exceptions.ParsingException;
import mowitnow.enums.Instruction;
import mowitnow.models.Command;
import mowitnow.models.Pelouse;
import mowitnow.models.Tondeuse;
import mowitnow.parsers.InstructionParser;
import mowitnow.parsers.PelouseParser;
import mowitnow.parsers.TondeuseParser;

/**
 * Main class for the Tondeuse Automatique application.
 */
public class TondeuseAutomatiqueApp {

	private static final Logger logger = LogManager.getLogger(TondeuseAutomatiqueApp.class);

	/**
	 * Main method to run the Tondeuse Automatique application. Accepts the path to
	 * the input file as a command line argument. Prints the final positions and
	 * orientations of the tondeuses after processing the input file.
	 *
	 * @param args Command line arguments. Expects the path to the input file.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
            logger.error("Usage: java TondeuseAutomatiqueApp <file_path>");
			System.out.println("Usage: java Main <file_path>");
			return;
		}

		String filePath = args[0];
		try {
			List<Tondeuse> results = processInputFile(filePath);
			for (Tondeuse tondeuse : results) {
				System.out.println(tondeuse.getPosition() + " " + tondeuse.getOrientation());
			}
		} catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath);
			System.out.println("File not found: " + filePath);
		} catch (ParsingException e) {
			logger.error("Error parsing input file: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Process the input file and return the final positions and orientations of the
	 * tondeuses.
	 *
	 * @param filePath Path to the input file.
	 * @return List of Tondeuse objects representing the final positions and
	 *         orientations.
	 * @throws FileNotFoundException If the input file is not found.
	 * @throws ParsingException      If an error occurs while parsing the input
	 *                               file.
	 */
	public static List<Tondeuse> processInputFile(String filePath) throws FileNotFoundException, ParsingException {
		logger.info("Processing input file: {}", filePath);

		List<Tondeuse> results = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filePath))) {
			PelouseParser pelouseParser = new PelouseParser();
			Pelouse pelouse = pelouseParser.parse(scanner.nextLine());
			logger.info("Parsed pelouse dimensions: {}", pelouse);

			while (scanner.hasNextLine()) {
				TondeuseParser tondeuseParser = new TondeuseParser();
				Tondeuse tondeuse = tondeuseParser.parse(scanner.nextLine());
				logger.info("Parsed tondeuse: {}", tondeuse);

				InstructionParser instructionParser = new InstructionParser();
				List<Instruction> instructions = instructionParser.parse(scanner.nextLine());
				logger.info("Parsed instructions for tondeuse: {}", instructions);

				// Execute instructions for tondeuse
				fillUpInstructions(instructions, tondeuse, pelouse);
				tondeuse.executeCommands();
				logger.info("Executed commands for tondeuse: {}", tondeuse);

				results.add(tondeuse);
			}
		} catch (FileNotFoundException e) {
			logger.error("File not found: {}", filePath);
			throw e;
		} catch (ParsingException e) {
			logger.error("Parsing exception occurred: {}", e.getMessage());
			throw e;
		}
		logger.info("Processed input file successfully");
		return results;
	}

	/**
	 * Fill up the list of commands for the given tondeuse based on the parsed
	 * instructions.
	 *
	 * @param instructions List of instructions to be executed by the tondeuse.
	 * @param tondeuse     The tondeuse object to which the commands are added.
	 * @param pelouse      The Pelouse object representing the lawn boundaries.
	 */
	public static void fillUpInstructions(List<Instruction> instructions, Tondeuse tondeuse, Pelouse pelouse) {
	    logger.info("Filling up instructions for tondeuse: {}", tondeuse);
	    
		for (Instruction instruction : instructions) {
			switch (instruction) {
			case LEFT:
				tondeuse.addCommand(new Command(Tondeuse::turnLeft));
                logger.debug("Added 'turnLeft' command to tondeuse");
				break;
			case RIGHT:
				tondeuse.addCommand(new Command(Tondeuse::turnRight));
                logger.debug("Added 'turnRight' command to tondeuse");
				break;
			case FORWARD:
				tondeuse.addCommand(new Command(tondeuse1 -> tondeuse1.moveForward(pelouse)));
                logger.debug("Added 'moveForward' command to tondeuse");
				break;
			}
		}
	}

}
