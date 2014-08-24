package threadsafety;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ProductHandlerTest {
	
	@Test
	public void discountIsApplied() {
		ProductHandler handler = new ProductHandler();
		Product aProduct = new Product("1", "a product", 60.0);
		Product newProduct = handler.applyDiscount(aProduct);
		
		assertEquals(54.0, newProduct.getPrice(), 0.01);
		assertNotSame(aProduct, newProduct);
	}
	
	@Test
	public void sumSeveralProducts() {
		ProductHandler handler = new ProductHandler();
		List<Product> cart = new ArrayList<>();
		cart.add(new Product("1", "one product", 54.0));
		cart.add(new Product("2", "another product", 46.50));
		cart.add(new Product("3", "yet another product", 19.5));
		
		assertEquals(120.0, handler.sumCart(cart), 0.01);
	}
}
