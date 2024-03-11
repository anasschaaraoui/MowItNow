package mowitnow.parsers;

import static org.apache.commons.lang3.StringUtils.split;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mowitnow.Exceptions.ParsingException;
import mowitnow.common.Parser;
import mowitnow.models.Coordonnees;
import mowitnow.models.Pelouse;

public class PelouseParser implements Parser<String, Pelouse> {
	
    private static final Logger logger = LogManager.getLogger(PelouseParser.class);
        
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
        
        if(input != null && !input.trim().matches("\\d+ +\\d+")) {
        	String errorMsg = "Pelouse dimensions doesn't matche the X Y pattern";
            logger.error(errorMsg);
			throw new ParsingException(errorMsg);
        }
        
		Pelouse pelouse;
		try {
			String[] dimensions = split(input);
			
			if(dimensions.length != 2) {
				String errorMsg = "Pelouse dimensions must have x and y";
                logger.error(errorMsg);
				throw new ParsingException(errorMsg);
			}
			
			int width = Integer.parseInt(dimensions[0]);
			int height = Integer.parseInt(dimensions[1]);
			
			if (width < 0 || height < 0) {
				String errorMsg = "Pelouse dimensions cannot be negative: width: {}, height: {}";
                logger.error(errorMsg, width, height);
				throw new ParsingException("Pelouse dimensions cannot be negative: width: %s, height: %s", width, height);
			}

			Coordonnees coinSuperieurDroit = new Coordonnees(width, height);
			pelouse = new Pelouse(coinSuperieurDroit);
            logger.info("Successfully parsed Pelouse: {}", pelouse);
		} catch (Exception e) {
			logger.error("Failed to parse Pelouse from input: {}", input);
			throw new ParsingException(e, "Failed to parse Pelouse from input: %s", input);
		}
		
		return pelouse;
	}
}
