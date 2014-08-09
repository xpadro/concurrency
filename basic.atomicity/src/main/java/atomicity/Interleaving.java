package atomicity;

/**
 * This program shows an example of Thread Interference.
 * 
 * The sequence of steps of each thread is overlapped. 
 * 
 * @author Xavier Padro
 *
 */
public class Interleaving {
	
	public void show() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + " - Number: " + i);
		}
	}

	public static void main(String[] args) {
		final Interleaving main = new Interleaving();
		
		Runnable runner = new Runnable() {
			@Override
			public void run() {
				main.show();
			}
		};
		
		new Thread(runner, "Thread 1").start();
		new Thread(runner, "Thread 2").start();
	}
}
