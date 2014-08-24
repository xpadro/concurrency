package threadsafety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is intended to be immutable but it lets the categories
 * reference to escape out of the class scope.
 * 
 * @author Xavier Padro
 *
 */
public final class MutableProduct {
	private final String id;
	private final String name;
	private final double price;
	private final List<String> categories = new ArrayList<>();
	
	public MutableProduct(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.categories.add("A");
		this.categories.add("B");
		this.categories.add("C");
	}
	
	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public List<String> getCategories() {
		return this.categories;
	}
	
	public List<String> getCategoriesUnmodifiable() {
		return Collections.unmodifiableList(categories);
	}
	
	public String toString() {
		return new StringBuilder(this.id).append("-").append(this.name)
				.append(" (").append(this.price).append(")").toString();
	}
	
	public static void main(String[] args) {
		MutableProduct p = new MutableProduct("1", "a product", 43.00);
		
		System.out.println("Product categories");
		for (String c : p.getCategories()) System.out.println(c);
		
		p.getCategories().remove(0);
		System.out.println("\nModified Product categories");
		for (String c : p.getCategories()) System.out.println(c);
	}
}
