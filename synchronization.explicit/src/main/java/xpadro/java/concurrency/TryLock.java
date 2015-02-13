package xpadro.java.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryLock {
	private static final Logger LOGGER = LoggerFactory.getLogger(TryLock.class);
	private final ReentrantLock lock1 = new ReentrantLock();
	private final ReentrantLock lock2 = new ReentrantLock();
	
	public static void main(String[] args) {
		TryLock app = new TryLock();
		Thread t1 = new Thread(new Worker1(app), "Thread-1");
		Thread t2 = new Thread(new Worker2(app), "Thread-2");
		t1.start();
		t2.start();
	}
	
	public void lockWithTry() {
		LOGGER.info("{}|Trying to acquire lock1...", Thread.currentThread().getName());
		lock1.lock();
		try {
			LOGGER.info("{}|Lock1 acquired. Trying to acquire lock2...", Thread.currentThread().getName());
			boolean acquired = lock2.tryLock(4, TimeUnit.SECONDS);
			if (acquired) {
				try {
					LOGGER.info("{}|Both locks acquired", Thread.currentThread().getName());
				} finally {
					lock2.unlock();
				}
			}
			else {
				LOGGER.info("{}|Failed acquiring lock2. Releasing lock1", Thread.currentThread().getName());
			}
		} catch (InterruptedException e) {
			//handle interrupted exception
		} finally {
			lock1.unlock();
		}
	}
	
	public void lockBlocking() {
		LOGGER.info("{}|Trying to acquire lock2...", Thread.currentThread().getName());
		lock2.lock();
		try {
			LOGGER.info("{}|Lock2 acquired. Trying to acquire lock1...", Thread.currentThread().getName());
			lock1.lock();
			try {
				LOGGER.info("{}|Both locks acquired", Thread.currentThread().getName());
			} finally {
				lock1.unlock();
			}
		} finally {
			lock2.unlock();
		}
	}
	
	private static class Worker1 implements Runnable {
		private final TryLock app;
		
		public Worker1(TryLock app) {
			this.app = app;
		}
		
		@Override
		public void run() {
			app.lockWithTry();
		}
	}
	
	private static class Worker2 implements Runnable {
		private final TryLock app;
		
		public Worker2(TryLock app) {
			this.app = app;
		}
		
		@Override
		public void run() {
			app.lockBlocking();
		}
	}
}
