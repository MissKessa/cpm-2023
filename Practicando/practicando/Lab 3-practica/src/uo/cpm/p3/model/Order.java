package uo.cpm.p3.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.p3.util.FileUtil;

public class Order {

	private List<Product> orderList = null;
	private String code = "";

	private TypeOfOrder type;

	public Order() {
		orderList = new ArrayList<Product>();
		// We generate the new code for the order:
		generateCode();
	}

	public List<Product> getOrderList() {
		return orderList;
	}

	public String toStringOrderList() {
		String result = "";
		for (Product p : orderList) {
			result += p.toString() + "\n";
		}
		return result;
	}

	public void add(Product item, int units) {
		Product itemInOrder = null;

		for (Product a : orderList) {
			if (a.getCode().equals(item.getCode())) {
				itemInOrder = a;
				itemInOrder.setUnits(itemInOrder.getUnits() + units);
				break;
			}
		}

		if (itemInOrder == null) {
			Product itemToOrder = new Product(item);
			itemToOrder.setUnits(units);
			orderList.add(itemToOrder);
		}
	}

	public TypeOfOrder getType() {
		return type;
	}

	public void setType(TypeOfOrder type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public float getPrice() {
		float total = 0.0f;
		for (Product a : orderList) {
			total += a.getPrice() * a.getUnits();
		}
		return total;
	}

	public void saveOrder() {
		FileUtil.saveToFile(code, orderList, type);
	}

	public void initialize() {
		orderList.clear();
	}

	private void generateCode() {
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int longitudCodigo = 8;
		for (int i = 0; i < longitudCodigo; i++) {
			int numero = (int) (Math.random() * (base.length()));
			code += base.charAt(numero);
		}
	}

	public int getUnits(Product p) {
		Product itemInOrder = null;
		for (Product a : orderList) {
			if (a.getCode().equals(p.getCode())) {
				itemInOrder = a;
				return itemInOrder.getUnits();
			}
		}
		return 0;
	}

	public void remove(Product item, Integer units) {
		Product itemInOrder = null;

		for (int i = 0; i < orderList.size(); i++) {
			if (orderList.get(i).getCode().equals(item.getCode())) {
				itemInOrder = orderList.get(i);
				itemInOrder.setUnits(itemInOrder.getUnits() - units);
				if (itemInOrder.getUnits() <= 0) {
					orderList.remove(i);
				}
				break;
			}
		}

	}
}
