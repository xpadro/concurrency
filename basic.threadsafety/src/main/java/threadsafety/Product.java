package threadsafety;

/**
 * Sample of an immutable class
 * 
 * @author Xavier Padro
 *
 */
public final class Product {
	private final String id;
	private final String name;
	private final double price;
	
	public Product(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
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
	
	public String toString() {
		return new StringBuilder(this.id).append("-").append(this.name)
				.append(" (").append(this.price).append(")").toString();
	}
	
	public boolean equals(Object x) {
		if (this == x) return true;
		if (x == null) return false;
		if (this.getClass() != x.getClass()) return false;
		Product that = (Product) x;
		if (!this.id.equals(that.id)) return false;
		if (!this.name.equals(that.name)) return false;
		if (this.price != that.price) return false;
		
		return true;
	}
	
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this.getId().hashCode();
		hash = 31 * hash + this.getName().hashCode();
		hash = 31 * hash + ((Double) this.getPrice()).hashCode();
		
		return hash;
	}
}
