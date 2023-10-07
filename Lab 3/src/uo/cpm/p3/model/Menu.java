package uo.cpm.p3.model;

import java.util.*;

import uo.cpm.p3.util.FileUtil;

public class Menu {
	
	private static final String PRODUCTS_FILE = "files/products.dat";
	private List<Product> productsList = null;
	
	public Menu(){
		productsList = new ArrayList<Product>();
		loadProducts();
	}

	private void loadProducts(){
		FileUtil.loadFile (PRODUCTS_FILE, productsList);
	  }

	public Product[] getProducts(){
		Product[] products = productsList.toArray(new Product[productsList.size()]);
		return products;	
	}
	
}
