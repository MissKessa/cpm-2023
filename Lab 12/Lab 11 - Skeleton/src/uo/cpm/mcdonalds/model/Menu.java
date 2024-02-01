package uo.cpm.mcdonalds.model;

import java.util.*;

import uo.cpm.mcdonalds.util.FileUtil;

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

	public List<Product> getProducts(){
		return productsList;
	}
	
}
