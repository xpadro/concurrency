package intrinsic;

/**
 * This class has a synchronized instance method. The program shows how
 * threads using different instances of this class do not share the
 * same lock.
 * 
 * @author Xavier Padro
 *
 */
public class InstanceMethodExample {
	private static long startTime;
	
	public void start() throws InterruptedException {
		doSomeTask();
	}
	
	public synchronized void doSomeTask() throws InterruptedException {
		long currentTime = System.currentTimeMillis() - startTime;
		System.out.println(Thread.currentThread().getName() + " | Entering method. Current Time: " + currentTime + " ms");
		Thread.sleep(3000);
		
		System.out.println(Thread.currentThread().getName() + " | Exiting method");
	}
	
	public static void main(String[] args) {
		InstanceMethodExample instance1 = new InstanceMethodExample();
		
		Thread t1 = new Thread(new Worker(instance1), "Thread-1");
		Thread t2 = new Thread(new Worker(instance1), "Thread-2");
		Thread t3 = new Thread(new Worker(new InstanceMethodExample()), "Thread-3");
		
		startTime = System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
	}
	
	private static class Worker implements Runnable {
		private final InstanceMethodExample instance;
		
		public Worker(InstanceMethodExample instance) {
			this.instance = instance;
		}

		@Override
		public void run() {
			try {
				instance.start();
			} catch (InterruptedException e) {
				System.out.println(Thread.currentThread().getName() + " interrupted");
			}
		}
	}
}
