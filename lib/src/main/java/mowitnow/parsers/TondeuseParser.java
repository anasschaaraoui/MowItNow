package mowitnow.parsers;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mowitnow.Exceptions.ParsingException;
import mowitnow.common.Parser;
import mowitnow.enums.Orientation;
import mowitnow.models.Coordonnees;
import mowitnow.models.Tondeuse;

public class TondeuseParser implements Parser<String, Tondeuse> {
	
    private static final Logger logger = LogManager.getLogger(TondeuseParser.class);

    /**
     * Parses the input string and returns a Tondeuse object.
     *
     * @param input The input string representing the tondeuse's position and orientation.
     *             The format should be "x y orientation", e.g., "3 4 N".
     * @return A Tondeuse object representing the parsed position and orientation.
     * @throws ParsingException If the input string is not in the correct format or
     *                          if parsing fails for any other reason.
     */
    @Override
    public Tondeuse parse(String input) throws ParsingException {
        logger.info("Parsing Tondeuse from input: {}", input);
        if(input != null && !input.trim().matches("\\d+ +\\d+ +[A-Z]")) {
        	String errorMsg = "Pelouse dimensions doesn't matche the X Y (N|E|S|W) pattern";
            logger.error(errorMsg);
			throw new ParsingException(errorMsg);
        }
        
        try {
            String[] parts = StringUtils.split(input);
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            
            if(parts.length != 3) {
				String errorMsg = "Pelouse dimensions must have x and y";
                logger.error(errorMsg);
				throw new ParsingException(errorMsg);
			}
            
            if (x < 0 || y < 0) {
                throw new ParsingException("Negative coordinates are not allowed");
            }
            
            Orientation orientation = Orientation.parse(parts[2]);
            return new Tondeuse(new Coordonnees(x, y), orientation);
        } catch (Exception e) {
            throw new ParsingException(e, "Failed to parse Tondeuse from input: %s", input);
        }
    }
}
