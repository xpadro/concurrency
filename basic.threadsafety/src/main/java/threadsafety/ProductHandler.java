package threadsafety;

import java.util.List;

/**
 * Sample of an stateless class
 * 
 * @author Xavier Padro
 *
 */
public class ProductHandler {
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
}
