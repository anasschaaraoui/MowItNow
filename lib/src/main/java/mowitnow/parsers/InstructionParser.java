package mowitnow.parsers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mowitnow.Exceptions.ParsingException;
import mowitnow.common.Parser;
import mowitnow.enums.Instruction;

public class InstructionParser implements Parser<String, List<Instruction>> {

	private static final Logger logger = LogManager.getLogger(InstructionParser.class);

	/**
	 * Parses the input string and returns a list of Instruction objects.
	 *
	 * @param input The input string representing the instructions. Each character
	 *              represents a single instruction (e.g., "LRLRLR").
	 * @return A list of Instruction objects representing the parsed instructions.
	 * @throws ParsingException If the input string is not in the correct format or
	 *                          if parsing fails for any other reason.
	 */
	@Override
	public List<Instruction> parse(String input) throws ParsingException {
		logger.info("Parsing instructions from input: {}", input);

		if (input == null || input.isEmpty()) {
			logger.error("Input string is empty");
			throw new ParsingException("Input string is empty");
		}

		try {
			List<Instruction> instructions = new ArrayList<>();
			for (char instructionCode : input.toCharArray()) {
				Instruction instruction = Instruction.parse(instructionCode);
				if (instruction != null) {
					instructions.add(instruction);
				} else {
					logger.error("Invalid instruction: {}", instructionCode);
					throw new ParsingException("Invalid instruction: " + instructionCode);
				}
			}
			logger.info("Successfully parsed instructions: {}", instructions);
			return instructions;
		} catch (ParsingException e) {
			logger.error("Failed to parse instructions from input: {}, {}", input, e);
			throw e;
		} catch (Exception e) {
			logger.error("Failed to parse instructions from input: {}, {}", input, e);
			throw new ParsingException("Failed to parse instructions from input: " + input, e);
		}
	}
}
