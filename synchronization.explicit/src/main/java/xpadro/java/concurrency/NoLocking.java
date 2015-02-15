package xpadro.java.concurrency;


public class NoLocking {
	public static void main(String[] args) {
		Worker worker = new Worker();
		
		Thread t1 = new Thread(worker, "Thread-1");
		Thread t2 = new Thread(worker, "Thread-2");
		t1.start();
		t2.start();
	}
	
	private static class Worker implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " - 1");
			System.out.println(Thread.currentThread().getName() + " - 2");
			System.out.println(Thread.currentThread().getName() + " - 3");
		}
	}
}
