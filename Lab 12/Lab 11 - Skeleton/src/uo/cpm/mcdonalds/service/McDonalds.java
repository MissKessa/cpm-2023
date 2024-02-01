package uo.cpm.mcdonalds.service;

import java.util.List;

import uo.cpm.mcdonalds.model.Menu;
import uo.cpm.mcdonalds.model.Order;
import uo.cpm.mcdonalds.model.Product;

public class McDonalds {

	Menu menu = new Menu();
	Order order = new Order();
	
	public McDonalds() {
		
		
	}

	public List<Product> getMenuProducts()
	{
		return menu.getProducts();
	}

	public void initOrder()
	{
		order.initialize();
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

	public void remove(Product product, int i) {
		order.remove(product,i);
		
	}
}
