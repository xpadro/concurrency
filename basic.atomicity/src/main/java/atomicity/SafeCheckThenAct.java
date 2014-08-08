package atomicity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread-safe number changer.
 * 
 * Since access to changeNumber method is serialized under an intrinsic lock, no race conditions
 * are present. Only one thread will be given access to the method at a given time.
 * 
 * @author Xavier Padro
 *
 */
public class SafeCheckThenAct {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private int number;
	
	public synchronized void changeNumber() {
		if (number == 0) {
			logger.info("{} - Changed", Thread.currentThread().getName());
			number = -1;
		}
		else {
			logger.info("{} - Not changed", Thread.currentThread().getName());
		}
	}

	
	public static void main(String[] args) {
		final SafeCheckThenAct checkAct = new SafeCheckThenAct();
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					checkAct.changeNumber();
				}
				
			}, "T" + i).start();
		}
	}
}
