package uo.cpm.p3.model;

/**
 * It models a product of the McDonalds app
 * 
 * @author paula
 *
 */
public class Product {
	/**
	 * It's the code that identifies the product
	 */
	private String code;
	/**
	 * It's the type of the product
	 */
	private String type;
	

	/**
	 * It's the name of the product
	 */
	private String name;
	/**
	 * It's the price of the product
	 */
	private float price;
	/**
	 * It's the number of units of the product
	 */
	private int units;

	/**
	 * Main constructor: it sets all the attributes to the given parameters
	 * 
	 * @param code  is the given code for the product
	 * @param type  is the given type for the product
	 * @param name  is the given name for the product
	 * @param price is the given price for the product
	 * @param units is the given number of unis for the product
	 */
	public Product(String code, String type, String name, float price, int units) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.price = price;
		this.units = units;
	}

	/**
	 * It's a constructor that creates a product from a given one (it will have the
	 * same attributes)
	 * 
	 * @param anotherItem is the product that will be "copied"
	 */
	public Product(Product anotherItem) {
		this(anotherItem.code, anotherItem.type, anotherItem.name, anotherItem.price, anotherItem.units);
	}

	/**
	 * 
	 * @return the name of the product
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the price of the product
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price of the product to the given one
	 * 
	 * @param price is the given price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * 
	 * @return the code of the product
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code of the product to the given one
	 * 
	 * @param code is the given code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return the number of units of the product
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * Sets the number of units of the product to the given one
	 * 
	 * @param units is the given number of units
	 */
	public void setUnits(int units) {
		this.units = units;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.type);
		buffer.append(" - ");
		buffer.append(this.name);
		buffer.append(" - ");
		buffer.append(this.price);
		buffer.append(" €");
		if (this.units != 0) {
			buffer.append(" (");
			buffer.append(this.units);
			buffer.append(" uds)");
		}
		return buffer.toString();
	}
	
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return a string with the name and the units of the product
	 */
	public String showInformation() {
		return "" + this.name + "-" + this.units + "uds";
	}
}
