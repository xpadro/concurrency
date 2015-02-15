package xpadro.java.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
	public static void main(String[] args) {
		Worker worker = new Worker();
		
		Thread t1 = new Thread(worker, "Thread-1");
		Thread t2 = new Thread(worker, "Thread-2");
		t1.start();
		t2.start();
	}
	
	private static class Worker implements Runnable {
		private final ReentrantLock lock = new ReentrantLock();
		
		@Override
		public void run() {
			lock.lock();
			try {
				System.out.println(Thread.currentThread().getName() + " - 1");
				System.out.println(Thread.currentThread().getName() + " - 2");
				System.out.println(Thread.currentThread().getName() + " - 3");
			} finally {
				lock.unlock();
			}
		}
	}
}