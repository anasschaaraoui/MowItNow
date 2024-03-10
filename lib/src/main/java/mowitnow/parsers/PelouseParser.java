package mowitnow.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mowitnow.Exceptions.ParsingException;
import mowitnow.common.Parser;
import mowitnow.models.Coordonnees;
import mowitnow.models.Pelouse;

public class PelouseParser implements Parser<String, Pelouse> {
	
    private static final Logger logger = LogManager.getLogger(PelouseParser.class);
    
	private static final String SPACE_DELIMITER = " ";

	/**
	 * Parses the input string and returns a Pelouse object.
	 *
	 * @param input The input string representing the dimensions of the lawn. The
	 *              format should be "width height", e.g., "5 5".
	 * @return A Pelouse object representing the parsed lawn dimensions.
	 * @throws ParsingException If the input string is not in the correct format or
	 *                          if parsing fails for any other reason.
	 */
	@Override
	public Pelouse parse(String input) throws ParsingException {
        logger.info("Parsing Pelouse from input: {}", input);
		try {
			String[] dimensions = input.split(SPACE_DELIMITER);
			int width = Integer.parseInt(dimensions[0]);
			int height = Integer.parseInt(dimensions[1]);
			
			if (width < 0 || height < 0) {
				String errorMsg = "Pelouse dimensions cannot be negative: width: {}, height: {}";
                logger.error(errorMsg, width, height);
				throw new ParsingException("Pelouse dimensions cannot be negative: width: %s, height: %s", width, height);
			}

			Coordonnees coinSuperieurDroit = new Coordonnees(width, height);
			Pelouse pelouse = new Pelouse(coinSuperieurDroit);
            logger.info("Successfully parsed Pelouse: {}", pelouse);
            return pelouse;
		} catch (Exception e) {
			logger.error("Failed to parse Pelouse from input: {}", input);
			throw new ParsingException(e, "Failed to parse Pelouse from input: %s", input);
		}
	}
}
