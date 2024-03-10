package mowitnow.common;

import mowitnow.Exceptions.ParsingException;

public interface Parser<InputType, OutputType> {
    /**
     * Parses input of type InputType and returns output of type OutputType.
     * 
     * @param input The input to be parsed.
     * @return The parsed output.
     * @throws ParsingException If an error occurs during parsing.
     */
    OutputType parse(InputType input) throws ParsingException;
}
