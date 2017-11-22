package exception;

/**
 * This exception should be thrown when passing an invalid argument to a method.
 * @author Tartiflotte
 *
 */
public class InvalidArgumentException extends TartiException {

	private static final long serialVersionUID = -944450205198181793L;

	public InvalidArgumentException() {
		super("The argument is invalid");
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
