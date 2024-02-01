package uo.cpm.p3.service;

import uo.cpm.p3.model.Menu;
import uo.cpm.p3.model.Order;
import uo.cpm.p3.model.Product;

public class McDonalds {

	Menu menu = new Menu();
	Order order = new Order();
	
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
		// TODO Auto-generated method stub
		return order.getPrice();
	}
}
