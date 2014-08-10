package atomicity;


/**
 * Thread-safe class.
 * 
 * Field 'number' will be incremented once for each thread, since access to 'number'
 * field is synchronized. Only one thread will access the field at a given time.
 * 
 * @author Xavier Padro
 *
 */
public class SafeReadModifyWriteSynchronized {
	private int number;
	
	public synchronized void incrementNumber() {
		number++;
	}
	
	public synchronized int getNumber() {
		return this.number;
	}
	
	public static void main(String[] args) throws InterruptedException {
		final SafeReadModifyWriteSynchronized rmw = new SafeReadModifyWriteSynchronized();
		
		for (int i = 0; i < 1_000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					rmw.incrementNumber();
				}
				
			}, "T" + i).start();
		}
		
		Thread.sleep(4000);
		System.out.println("Final number (should be 1_000): " + rmw.getNumber());
	}
}
