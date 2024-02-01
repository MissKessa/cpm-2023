package uo.cpm.p3.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.p3.service.TypeOfProduct;
import uo.cpm.p3.util.FileUtil;

/**
 * It models the menu for the mcDonalds. It loads all the products from a file
 * and adds it to a list
 * 
 * @author paula
 *
 */
public class Menu {

	/**
	 * It's the data path of the file that contains the products
	 */
	private static final String PRODUCTS_FILE = "files/products.dat";
	/**
	 * It's the list of products in the menu
	 */
	private List<Product> productsList = null;

	/**
	 * Main construct: it loads the products into the list of products
	 */
	public Menu() {
		productsList = new ArrayList<Product>();
		loadProducts();
	}

	/**
	 * Loads the products to the menu
	 */
	private void loadProducts() {
		FileUtil.loadFile(PRODUCTS_FILE, productsList);
	}

	/**
	 *
	 * 
	 * @return the list of products
	 */
	public Product[] getProducts() {
		Product[] products = productsList.toArray(new Product[productsList.size()]);
		return products;
	}

	public Product[] getProductsByType(TypeOfProduct type) {
		Product[] products = productsList.toArray(new Product[productsList.size()]);
		Product[] productsFiltered = new Product[productsList.size()];
		int i=0;
		for (Product product : products) {
			
			if (product.getType().equals(type.toString())) {
				productsFiltered[i]= product;
				i++;
			}
		}
		return productsFiltered;
	}

}
