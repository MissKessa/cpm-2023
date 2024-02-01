package uo.cpm.p3.model;

public class Product {

	private String code;
	private String type;
	private String name;
	private float price;
	private int units;

	public Product(String code, String type, String name, float price, int units) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.price = price;
		this.units = units;
	}

	public Product(Product anotherItem) {
		this(anotherItem.code, anotherItem.type, anotherItem.name, anotherItem.price, anotherItem.units);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getUnits() {
		return units;
	}

	public String getType() {
		return type;
	}

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
}
