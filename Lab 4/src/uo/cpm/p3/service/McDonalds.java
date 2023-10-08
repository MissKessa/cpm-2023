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
	 * It's the menu for the McDonalds
	 */
	Menu menu = new Menu();
	/**
	 * It's the order
	 */
	Order order = new Order();

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
	 * @return the price of the order
	 */
	public float getOrderTotal() {
		return order.getPrice();
	}

	/**
	 * 
	 * @return if the discount is applied
	 */
	public boolean isDiscountApplied() {
		return order.isDiscountApplied();
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

	/**
	 * 
	 * @return a string of the order with its information
	 */
	public String getOrder() {
		return order.toString();
	}

	/**
	 * Sets if the order is taken on site by the given parameter
	 * 
	 * @param orderOnSite is the parameter that sets if the order is taken on site
	 */
	public void setOrderOnSite(boolean orderOnSite) {
		order.setOrderOnSite(orderOnSite);
	}

	/**
	 * It reduces the units of a given product in the given units.
	 * 
	 * @param selectedItem is the given product
	 * @param units        is the units substracted from the product
	 */
	public void removeProduct(Product selectedItem, int value) {
		order.removeProduct(selectedItem, value);

	}

}
