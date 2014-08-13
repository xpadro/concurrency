package visibility;

/**
 * This program will end about three seconds after it started.
 * Reader thread can see the value updated from the main thread, 
 * since value is stored in a volatile field.
 * 
 * @author Xavier Padro
 *
 */
public class Visibility {
	private static volatile boolean ready;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (ready) {
						System.out.println("Reader Thread - Flag change received. Finishing thread.");
						break;
					}
				}
			}
		}).start();
		
		Thread.sleep(3000);
		System.out.println("Writer thread - Changing flag...");
		ready = true;
	}
}