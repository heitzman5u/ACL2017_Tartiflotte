package exception;


/**
 * This exception should be thrown when a call to a method cannot complete properly 
 * because the instance isn't in a required state.
 * @author Tartiflotte
 *
 */
public class IllegalInstanceStateException extends IllegalStateException {

	private static final long serialVersionUID = 6050927848816198529L;

	public IllegalInstanceStateException() {
		super("The object is not in an appropriate state to call the method");
	}

	public IllegalInstanceStateException(String s) {
		super(s);
	}

	public IllegalInstanceStateException(Throwable cause) {
		super(cause);
	}

	public IllegalInstanceStateException(String message, Throwable cause) {
		super(message, cause);
	}

}
