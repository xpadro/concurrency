package intrinsic;

/**
 * Synchronized version of {@link CoffeeStore}. All operations during a
 * coffee sale are made atomic by synchronizing the whole method.
 * 
 * @author Xavier Padro
 *
 */
public class SynchronizedCoffeeStore {
	private String lastClient;
	private int soldCoffees;

	private void someLongRunningProcess() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	public synchronized void buyCoffee(String client) throws InterruptedException {
		someLongRunningProcess();
		
		lastClient = client;
		soldCoffees++;
		System.out.println(client + " bought some coffee");
	}
	
	public synchronized int countSoldCoffees() {return soldCoffees;}
	
	public synchronized String getLastClient() {return lastClient;}
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedCoffeeStore store = new SynchronizedCoffeeStore();
		Thread t1 = new Thread(new Client(store, "Mike"));
		Thread t2 = new Thread(new Client(store, "John"));
		Thread t3 = new Thread(new Client(store, "Anna"));
		Thread t4 = new Thread(new Client(store, "Steve"));

		long startTime = System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Sold coffee: " + store.countSoldCoffees());
		System.out.println("Last client: " + store.getLastClient());
		System.out.println("Total time: " + totalTime + " ms");
	}
	
	private static class Client implements Runnable {
		private final String name;
		private final SynchronizedCoffeeStore store;
		
		public Client(SynchronizedCoffeeStore store, String name) {
			this.store = store;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				store.buyCoffee(name);
			} catch (InterruptedException e) {
				System.out.println("interrupted sale");
			}
		}
	}
}
