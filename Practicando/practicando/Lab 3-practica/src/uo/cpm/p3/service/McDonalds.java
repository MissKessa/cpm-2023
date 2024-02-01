package uo.cpm.p3.service;

import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;
import uo.cpm.p3.model.TypeOfOrder;
import uo.cpm.p3.model.TypeOfProduct;

public class McDonalds {

	Menu menu = new Menu();
	Order order = new Order();
	private boolean discountApplied;

	public McDonalds() {

	}

	public Product[] getMenuProducts() {
		return menu.getProducts();
	}

	public void initOrder() {
		order.initialize();
	}

	public String getOrderCode() {
		return order.getCode();
	}

	public void addToOrder(Product p, Integer units) {
		order.add(p, units);
	}

	public void saveOrder() {
		order.saveOrder();
	}

	public double getOrderTotal() {
		float price = order.getPrice();
		if (price >= 60) {
			discountApplied = true;
			return price * 0.9;
		}
		return price;
	}

	public boolean isDiscountApplied() {
		return discountApplied;
	}

	public int getUnits(Product p) {
		return order.getUnits(p);
	}

	public String getProduct(Product selectedItem) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeToOrder(Product selectedItem, Integer units) {
		order.remove(selectedItem, units);
		if (order.getPrice() < 60)
			discountApplied = false;

	}

	public void setTypeOfOrder(TypeOfOrder type) {

		order.setType(type);

	}

	public String toStringOrderList() {
		return order.toStringOrderList();
	}

	public Product[] getMenuProducts(TypeOfProduct type) {
		return menu.getProducts(type);
	}
}
