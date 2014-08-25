package threadsafety;

import java.util.UUID;

/**
 * This class uses ThreadLocal to store UUID values.
 * 
 * @author Xavier Padro
 *
 */
public class ClientRequestId {
	private static final ThreadLocal<String> id = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return UUID.randomUUID().toString();
		}
	};
	
	public static String get() {
		return id.get();
	}
}
