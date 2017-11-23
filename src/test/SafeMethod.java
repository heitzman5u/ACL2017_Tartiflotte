package test;

public class SafeMethod {
	
	private static boolean testing = false;
	
	public static void testMethods(){
		testing = true;
	}

	public static void forTesting(){
		if(!testing){
			throw new UnsupportedOperationException("Calling a test-only method in program");
		}
	}

}
