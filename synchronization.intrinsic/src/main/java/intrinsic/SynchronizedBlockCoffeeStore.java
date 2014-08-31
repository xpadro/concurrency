package intrinsic;


/**
 * An improved version of the {@link SynchronizedCoffeeStore} example.
 * The long running process is left out of the synchronized block.
 * 
 * @author Xavier Padro
 *
 */
public class SynchronizedBlockCoffeeStore {
	private String lastClient;
	private int soldCoffees;

	private void someLongRunningProcess() throws InterruptedException {
		Thread.sleep(3000);
	}
	
	public void buyCoffee(String client) throws InterruptedException {
		someLongRunningProcess();
		
		synchronized(this) {
			lastClient = client;
			soldCoffees++;
			System.out.println(client + " bought some coffee");
		}
	}
	
	public synchronized int countSoldCoffees() {return soldCoffees;}
	
	public synchronized String getLastClient() {return lastClient;}
	
	public static void main(String[] args) throws InterruptedException {
		SynchronizedBlockCoffeeStore store = new SynchronizedBlockCoffeeStore();
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
		private final SynchronizedBlockCoffeeStore store;
		
		public Client(SynchronizedBlockCoffeeStore store, String name) {
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
