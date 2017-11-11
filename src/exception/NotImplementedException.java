package exception;

/**
 * This exception should be thrown when a method is not implemented, but will likely be in the future.
 * Such an operation should be marked as "deprecated"!;
 * @author Tartiflotte
 */
public class NotImplementedException extends UnsupportedOperationException {

	private static final long serialVersionUID = -6602260355052908365L;

	public NotImplementedException() {
		super("The operation is not implemented yet");
	}

	public NotImplementedException(String message) {
		super(message);
	}

	public NotImplementedException(Throwable cause) {
		super(cause);
	}

	public NotImplementedException(String message, Throwable cause) {
		super(message, cause);
	}

}
