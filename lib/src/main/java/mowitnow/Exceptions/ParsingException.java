package mowitnow.Exceptions;

import static java.lang.String.format;

public class ParsingException extends Exception {
    /**
     * Constructs a new parsing exception with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     */
    public ParsingException(String message, Object...params) {
        super(format(message, params));
    }

    /**
     * Constructs a new parsing exception with the specified detail message
     * and cause.
     *
     * <p>Note that the detail message associated with {@code cause} is
     * <i>not</i> automatically incorporated into this exception's detail
     * message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method). (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent
     *                or unknown.)
     */
    public ParsingException(Throwable cause, String message, Object...params) {
        super(format(message, params), cause);
    }
}
