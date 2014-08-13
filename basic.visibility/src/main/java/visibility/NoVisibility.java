package visibility;

/**
 * This program may run forever. The reader thread may infinitely loop
 * since it may not see the value updated from the main thread.
 * 
 * @author Xavier Padro
 *
 */
public class NoVisibility {
	private static boolean ready;

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