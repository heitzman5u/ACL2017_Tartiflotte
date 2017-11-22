package exception;

public class TartiException extends Exception {

	public TartiException() {
		super("Exception thrown from Tartiflotte program");
	}

	public TartiException(String message) {
		super(message);
	}

	public TartiException(Throwable cause) {
		super(cause);
	}

	public TartiException(String message, Throwable cause) {
		super(message, cause);
	}

	public TartiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
