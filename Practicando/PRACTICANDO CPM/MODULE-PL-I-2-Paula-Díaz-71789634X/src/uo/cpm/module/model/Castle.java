package uo.cpm.module.model;

/**
 * It represents each castle and stores all the information of the castle
 * 
 * @author paula
 *
 */
public class Castle {

	/**
	 * Sequence of three letters and three numbers that uniquely identifies each
	 * castle
	 */
	private String code;
	/**
	 * Name of the castle
	 */
	private String name;
	/**
	 * Description of the castle
	 */
	private String description;
	/**
	 * Country of the castle
	 */
	private String country;
	/**
	 * Room price of the castle
	 */
	private float price;
	/**
	 * List of enchantments of the castle
	 */
	private String enchantments;

	/**
	 * Creates the castle with all the information given
	 * 
	 * @param code         of the castle
	 * @param name         of the castle
	 * @param description  of the castle
	 * @param country      of the castle
	 * @param price        of the castle
	 * @param enchantments of the castle
	 * @throw NullPointerException if one of the parameters is null
	 * @throw IllegalArgumentException if one of the parameters is blank or the
	 *        price is 0 or negative
	 */
	public Castle(String code, String name, String description, String country, float price, String enchantments) {
		if (code == null || name == null || description == null || country == null || enchantments == null) {
			throw new NullPointerException("Error creating castle: one of the parametes is null");
		}
		if (code.isBlank() || name.isBlank() || description.isBlank() || country.isBlank() || enchantments.isBlank()) {
			throw new IllegalArgumentException("Error creating castle: one of the parametes is blank");
		}
		if (price <= 0) {
			throw new IllegalArgumentException("Error creating castle: the price cannot be negative or zero");
		}
		this.code = code;
		this.name = name;
		this.description = description;
		this.country = country;
		this.price = price;
		this.enchantments = enchantments;
	}

	/**
	 * 
	 * @return the code of the castle
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return the name of the castle
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the description of the castle
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @return the code of the castle
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @return the price of the castle
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * 
	 * @return the enchantments of the castle
	 */
	public String getEnchantments() {
		return enchantments;
	}

}
