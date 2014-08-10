package atomicity;

import java.util.concurrent.CountDownLatch;


/**
 * Non thread-safe class that have a read-modify-write race condition.
 * 
 * Field 'number' should be incremented once for each thread. However, due to the 
 * presence of a race condition, some updates may be lost, leading to incorrect results.
 * 
 * @author Xavier Padro
 *
 */
public class UnsafeReadModifyWriteWithLatch {
	private static final int NUM_THREADS = 1_000;
	private int number;
	private final CountDownLatch startSignal = new CountDownLatch(1);
	private final CountDownLatch endSignal = new CountDownLatch(NUM_THREADS);
	
	public void incrementNumber() {
		number++;
	}
	
	public int getNumber() {
		return number;
	}
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			test();
		}
	}
	
	public static void test() throws InterruptedException {
		final UnsafeReadModifyWriteWithLatch rmw = new UnsafeReadModifyWriteWithLatch();
		
		for (int i = 0; i < NUM_THREADS; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						rmw.startSignal.await();
						rmw.incrementNumber();
					} catch (InterruptedException e) { 
						
					} finally {
						rmw.endSignal.countDown();
					}
					
				}
			}, "T" + i).start();
		}
		
		Thread.sleep(2000);
		rmw.startSignal.countDown();
		rmw.endSignal.await();
		System.out.println("Final number (should be 1_000): " + rmw.getNumber());
	}
}
