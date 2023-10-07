package uo.cpm.p3.service;

import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;

public class McDonalds {
	public final static int DISCOUNT_VALUE=10;
	public final static float PRICE_TO_HAVE_DISCOUNT=60;
	
	Menu menu = new Menu();
	Order order = new Order();
	boolean discountApplied=false;
	
	public McDonalds() {
		
		
	}

	public Product[] getMenuProducts()
	{
		return menu.getProducts();
	}

	public void initOrder()
	{
		order.initialize();
	}
	
	public String getOrderCode()
	{
		return order.getCode();
	}
	
	public void addToOrder ( Product p, Integer units )
	{
		order.add(p, units);
	}
	
	public void saveOrder()
	{
		order.saveOrder();
	}

	public Object getOrderTotal() {
		if (order.getPrice()>=PRICE_TO_HAVE_DISCOUNT) {
			discountApplied=true;
			return order.getPrice()*(1-DISCOUNT_VALUE/100);
		}
		return order.getPrice();
	}

	public boolean isDiscountApplied() {
		return discountApplied;
	}
	
}
