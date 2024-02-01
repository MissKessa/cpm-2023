package uo.cpm.p3.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.p3.util.FileUtil;

public class Menu {

	private static final String PRODUCTS_FILE = "files/products.dat";
	private List<Product> productsList = null;

	public Menu() {
		productsList = new ArrayList<Product>();
		loadProducts();
	}

	private void loadProducts() {
		FileUtil.loadFile(PRODUCTS_FILE, productsList);
	}

	public Product[] getProducts() {
		Product[] products = productsList.toArray(new Product[productsList.size()]);
		return products;
	}

	public Product[] getProducts(TypeOfProduct type) {
		ArrayList<Product> products = new ArrayList<>();
		for (Product product : productsList) {
			if (product.getType().equals(type.toString())) {
				products.add(product);
			}
		}
		Product[] productsOfThatType = products.toArray(new Product[productsList.size()]);
		return productsOfThatType;
	}

}
