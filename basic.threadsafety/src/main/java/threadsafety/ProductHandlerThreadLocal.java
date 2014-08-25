package threadsafety;

import java.util.List;

/**
 * Sample of an stateless class
 * 
 * @author Xavier Padro
 *
 */
public class ProductHandlerThreadLocal {
	private static final int DISCOUNT = 90;
	
	public Product applyDiscount(Product p) {
		double finalPrice = p.getPrice() * DISCOUNT / 100;
		
		return new Product(p.getId(), p.getName(), finalPrice);
	}
	
	public double sumCart(List<Product> cart) {
		double total = 0.0;
		for (Product p : cart.toArray(new Product[0])) total += p.getPrice();
		
		return total;
	}
	
	public String generateOrderId() {
		return ClientRequestId.get();
	}
	
	public static void main(String[] args) {
		final ProductHandlerThreadLocal handler = new ProductHandlerThreadLocal();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++)
					System.out.println(Thread.currentThread().getName() + " - " + handler.generateOrderId());
				
			}
		};
		
		new Thread(runnable, "T1").start();
		new Thread(runnable, "T2").start();
		new Thread(runnable, "T3").start();
	}
}
