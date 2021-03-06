package exception;

/**
 * This exception should be thrown in place of InvalidArgumentException when the argument is invalid because it's null
 * @author Tartiflotte
 */
public class NullArgumentException extends InvalidArgumentException {
	
	private static final long serialVersionUID = 7860837849244466469L;

	public NullArgumentException() {
		super("Expected a non null argument");
	}

	public NullArgumentException(String s) {
		super(s);
	}

	public NullArgumentException(Throwable cause) {
		super(cause);
	}

	public NullArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

}
