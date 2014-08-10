package atomicity;


/**
 * Non thread-safe class that have a read-modify-write race condition.
 * 
 * Field 'number' should be incremented once for each thread. However, due to the 
 * presence of a race condition, some updates may be lost, leading to incorrect results.
 * 
 * @author Xavier Padro
 *
 */
public class UnsafeReadModifyWrite {
	private int number;
	
	public void incrementNumber() {
		number++;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public static void main(String[] args) throws InterruptedException {
		final UnsafeReadModifyWrite rmw = new UnsafeReadModifyWrite();
		
		for (int i = 0; i < 1_000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					rmw.incrementNumber();
				}
				
			}, "T" + i).start();
		}
		
		Thread.sleep(6000);
		System.out.println("Final number (should be 1_000): " + rmw.getNumber());
	}
}
