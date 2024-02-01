package uo.cpm.module.model;

/**
 * It represents the discount: it has an id associated and its value (the code)
 * 
 * @author paula
 *
 */
public class Discount {
	/**
	 * It's the value of the discount
	 */
	private DiscountCode code;
	/**
	 * It's the id associated to it
	 */
	private String id;

	/**
	 * Creates the discount with the given parameters
	 * 
	 * @param id   associated to it
	 * @param code of the discount
	 * @throw NullPointerException if one of the parameters is null
	 * @throw IllegalArgumentException if the id is blank
	 */
	public Discount(String id, DiscountCode code) {
		if (id == null || code == null)
			throw new NullPointerException("Error creating discount: one of the parameters is null");
		if (id.isBlank())
			throw new IllegalArgumentException("Error creating discount: the id cannot be blank");
		this.id = id;
		this.code = code;
	}

	/**
	 * Parses the code of the discount
	 * 
	 * @param code of the discount as an String
	 * @return the code parsed
	 * @throw NullPointerException if the code is null
	 */
	public static DiscountCode parseDiscount(String code) {
		if (code == null)
			throw new NullPointerException("Parse discount error: The value of the code cannot be null");

//		if (code.equals(DiscountCode.EXTRA10.toString()))
//			return DiscountCode.EXTRA10;
//		else if (code.equals(DiscountCode.EXTRA25.toString()))
//			return DiscountCode.EXTRA25;
		return DiscountCode.valueOf(code);
//		throw new IllegalArgumentException("Parse discount error: The value of the code for the discount is invalid");
	}

	/**
	 * 
	 * @return the code of the discount
	 */
	public DiscountCode getCode() {
		return code;
	}

	/**
	 * 
	 * @return the id of the discount
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * Sets the id to the one passed as parameter
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the discount as: id;code
	 */
	@Override
	public String toString() {// ID;Discount code
		return id + ";" + code.toString();
	}

}
