package uo.cpm.p3.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.p3.util.FileUtil;

/**
 * It models the order of the McDonalds app. It has a list of the products of
 * the order and the code to identify the order
 * 
 * @author paula
 *
 */
public class Order {
	/**
	 * It's the percent discount if the discount is applied
	 */
	public final static int DISCOUNT_VALUE = 10;
	/**
	 * It's the price at which the discount is applied
	 */
	public final static float PRICE_TO_HAVE_DISCOUNT = 60;

	/**
	 * It's the list of products of the order
	 */
	private List<Product> orderList = null;
	/**
	 * It's the code to identify the order
	 */
	private String code = "";

	/**
	 * It shows if the discount is applied
	 */
	boolean discountApplied = false;

	/**
	 * It indicates if the order is going to be taken on site or not
	 */
	boolean orderOnSite = true;

	/**
	 * Main constructor: it generates the code
	 */
	public Order() {
		orderList = new ArrayList<Product>();
		// We generate the new code for the order:
		generateCode();
	}

	/**
	 * Adds the given item to the list of products If the given item is already in
	 * the list, it sets the units to the ones before plus the given units. If not,
	 * it adds the given item with the given number of units to the list of
	 * products.
	 * 
	 * @param item  is the item to be added
	 * @param units is the number of units of the given item
	 */
	public void add(Product item, int units) {
		Product itemInOrder = null;

		for (Product a : orderList) {
			if (a.getCode().equals(item.getCode())) {
				itemInOrder = a;
				itemInOrder.setUnits(itemInOrder.getUnits() + units);
				break;
			}
		}

		if (itemInOrder == null) {
			Product itemToOrder = new Product(item);
			itemToOrder.setUnits(units);
			orderList.add(itemToOrder);
		}
	}

	/**
	 * 
	 * @return the code of the order
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @return the price of the order
	 */
	public float getPrice() {
		float total = 0.0f;
		for (Product a : orderList) {
			total += a.getPrice() * a.getUnits();
		}
		if (total >= PRICE_TO_HAVE_DISCOUNT) {
			discountApplied = true;
			return total * (1 - DISCOUNT_VALUE / 100);
		} else {
			discountApplied = false;
		}
		return total;
	}

	/**
	 * 
	 * @return if the discount is applied
	 */
	public boolean isDiscountApplied() {
		return discountApplied;
	}

	/**
	 * Sets if the order is taken on site by the given parameter
	 * 
	 * @param orderOnSite is the parameter that sets if the order is taken on site
	 */
	public void setOrderOnSite(boolean orderOnSite) {
		this.orderOnSite = orderOnSite;
	}

	/**
	 * Saves in a file with the name of the code, all the products in the order list
	 */
	public void saveOrder() {
		FileUtil.saveToFile(code, orderList, orderOnSite);
	}

	/**
	 * Clears all the list of products
	 */
	public void initialize() {
		orderList.clear();
	}

	/**
	 * Generates the code for the order
	 */
	private void generateCode() {
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for (int i = 0; i < longitudCodigo; i++) {
			int numero = (int) (Math.random() * (base.length()));
			code += base.charAt(numero);
		}
	}

	/**
	 * 
	 * @param selectedProduct is the product to check the units in the order
	 * @return the number of units of the selectedProduct in the order
	 */
	public int getUnits(Product selectedProduct) {
		for (Product a : orderList) {
			if (a.getCode().equals(selectedProduct.getCode())) {
				return a.getUnits();
			}
		}
		return 0;
	}

	/**
	 * @return a string that shows the information of the order: the information of
	 *         the products and the total price
	 */
	@Override
	public String toString() {
		String order = "";
		for (Product product : orderList) {
			order += product.showInformation() + "\n";
		}
		order += "Total: " + (String.format("%.2f", getPrice()) + " \u20AC");
		return order;

	}

	/**
	 * It reduces the units of a given product in the given units.
	 * 
	 * @param selectedItem is the given product
	 * @param units        is the units substracted from the product
	 */
	public void removeProduct(Product selectedItem, int units) {
		Product itemInOrder = null;
		if (selectedItem != null) {
			for (Product a : orderList) {
				if (a.getCode().equals(selectedItem.getCode())) {
					itemInOrder = a;
					itemInOrder.setUnits(itemInOrder.getUnits() - units);
					if (itemInOrder.getUnits() <= 0) {
						orderList.remove(itemInOrder);
					}
					break;
				}
			}

		}

	}
}
