package uo.cpm.p3.service;

import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;

/**
 * It models a McDonalds app with a menu and order
 * 
 * @author paula
 *
 */
public class McDonalds {
	/**
	 * It's the percent discount if the discount is applied
	 */
	public final static int DISCOUNT_VALUE = 10;
	/**
	 * It's the price at which the discount is applied
	 */
	public final static float PRICE_TO_HAVE_DISCOUNT = 60;

	/**
	 * It's the menu for the McDonalds
	 */
	Menu menu = new Menu();
	/**
	 * It's the order
	 */
	Order order = new Order();
	/**
	 * It shows if the discount is applied
	 */
	boolean discountApplied = false;

	public McDonalds() {

	}

	/**
	 * 
	 * @return the list of products in the menu
	 */
	public Product[] getMenuProducts() {
		return menu.getProducts();
	}

	/**
	 * It's initialize the order
	 */
	public void initOrder() {
		order.initialize();
	}

	/**
	 * @return the code for the order
	 */
	public String getOrderCode() {
		return order.getCode();
	}

	/**
	 * Adds a given product to the order
	 * 
	 * @param p     is the product to add to the order
	 * @param units is the number of units of that product
	 */
	public void addToOrder(Product p, Integer units) {
		order.add(p, units);
	}

	/**
	 * Saves the order
	 */
	public void saveOrder() {
		order.saveOrder();
	}

	/**
	 * 
	 * @return the price (the discount is calculated if it's applied)
	 */
	public Object getOrderTotal() {
		if (order.getPrice() >= PRICE_TO_HAVE_DISCOUNT) {
			discountApplied = true;
			return order.getPrice() * (1 - DISCOUNT_VALUE / 100);
		}
		return order.getPrice();
	}

	/**
	 * 
	 * @return if the discount is applied
	 */
	public boolean isDiscountApplied() {
		return discountApplied;
	}

	/**
	 * 
	 * @param selectedProduct is the given product to check its number of units
	 * @return the number of units of the given product
	 */
	public String getUnits(Product selectedProduct) {
		if (selectedProduct == null) {
			throw new IllegalArgumentException("The selected product cannot be null");
		}
		return "" + order.getUnits(selectedProduct);
	}

}
