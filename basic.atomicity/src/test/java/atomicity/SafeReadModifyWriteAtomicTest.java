package atomicity;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This test will always succeed since the class used by
 * worker threads is thread-safe.
 * 
 * @author Xavier Padro
 *
 */
public class SafeReadModifyWriteAtomicTest {

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void testResultingNumber() throws InterruptedException {
		final SafeReadModifyWriteAtomic rmw = new SafeReadModifyWriteAtomic();
		
		for (int i=0; i<1_000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					rmw.incrementNumber();
				}
				
			}, "T"+i).start();
		}
		
		Thread.sleep(4000);
		assertEquals(1_000, rmw.getNumber());
	}
}
