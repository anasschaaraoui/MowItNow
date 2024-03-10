package mowitnow.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Instruction {
    LEFT('G'), RIGHT('D'), FORWARD('A');

    private static final Logger logger = LogManager.getLogger(Instruction.class);
    
    private final char code;

    Instruction(char code) {
        this.code = code;
    }
    
    public char getCode() {
		return code;
	}

    /**
     * Parses the input character into the corresponding Instruction enum constant.
     * Returns null if the character does not match any known instruction.
     *
     * @param code The input character representing the instruction.
     * @return The Instruction enum constant corresponding to the input character, or null if not found.
     */
    public static Instruction parse(char code) {
        logger.debug("Attempting to parse instruction for code: {}", code);
        for (Instruction instruction : Instruction.values()) {
            if (instruction.code == code) {
                logger.debug("Instruction parsed successfully for code {}: {}", code, instruction);
                return instruction;
            }
        }
        logger.warn("Unknown instruction code: {}", code);
        return null; // Unknown instruction
    }
}
