package test;

/**
 * Call forTesting in every method in the project that should be used only for testing purpose.
 * This will break the program if it ever happens.
 * @author Tartiflotte
 *
 */
public class SafeMethod {
	
	private static boolean testing = false;
	
	/**
	 * Tell the class that we are now testing
	 */
	public static void testMethods(){
		testing = true;
	}

	/**
	 * Sould be called when using a test-only method
	 */
	public static void forTesting(){
		if(!testing){
			throw new UnsupportedOperationException("Calling a test-only method in program");
		}
	}

}
