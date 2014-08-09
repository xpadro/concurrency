package atomicity;


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
	private int number;
	
	public synchronized void changeNumber() {
		if (number == 0) {
			System.out.println(Thread.currentThread().getName() + " | Changed");
			number = -1;
		}
		else {
			System.out.println(Thread.currentThread().getName() + " | Not changed");
		}
	}

	
	public static void main(String[] args) {
		final SafeCheckThenAct checkAct = new SafeCheckThenAct();
		
		for (int i = 0; i < 50; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					checkAct.changeNumber();
				}
				
			}, "T" + i).start();
		}
	}
}
