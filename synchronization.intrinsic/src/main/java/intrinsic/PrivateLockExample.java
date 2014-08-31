package intrinsic;

/**
 * This class uses an internal object as a lock for its
 * synchronized methods.
 * 
 * @author Xavier Padro
 *
 */
public class PrivateLockExample {
	private Object myLock = new Object();

	public void executeTask() throws InterruptedException {
		synchronized(myLock) {
			System.out.println("executeTask - Entering...");
			Thread.sleep(3000);
			System.out.println("executeTask - Exiting...");
		}
	}
}
