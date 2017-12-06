package exception;

/**
 * This exception should be thrown when the operation requires loaded data, but the data wasn't loaded.
 * @author Tartiflotte
 *
 */
public class NotLoadedException extends TartiException {
	
	private static final long serialVersionUID = 6194470613327454864L;

	public NotLoadedException() {
		super("Some data wasn't loaded");
	}

	public NotLoadedException(String message) {
		super(message);
	}

	public NotLoadedException(Throwable cause) {
		super(cause);
	}

	public NotLoadedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotLoadedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
