package atomicity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Non thread-safe class that have a check-then-act race condition.
 * 
 * Field 'number' should only be changed once, but the presence of a race condition 
 * may lead to incorrect results. 
 * 
 * @author Xavier Padro
 *
 */
public class UnsafeCheckThenAct {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private int number;
	
	public void changeNumber() {
		if (number == 0) {
			logger.info("{} - Changed", Thread.currentThread().getName());
			number = -1;
		}
		else {
			logger.info("{} - Not changed", Thread.currentThread().getName());
		}
	}

	public static void main(String[] args) {
		final UnsafeCheckThenAct checkAct = new UnsafeCheckThenAct();
		
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
