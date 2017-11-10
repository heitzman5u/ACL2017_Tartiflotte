package exception;

/**
 * This exception should be thrown in place of IllegalArgumentException when the argument is illegal because it's null
 * @author Tartiflotte
 *
 */
public class NullArgumentException extends IllegalArgumentException {
	
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
