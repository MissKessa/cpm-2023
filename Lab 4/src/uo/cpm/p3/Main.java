package uo.cpm.p3;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import uo.cpm.p3.service.McDonalds;
import uo.cpm.p3.ui.MainWindow;

public class Main {

//	/**
//	 * Simulates the behaviour of the McDnalds project: shows the list of products,
//	 * add to the order 5 items, saves the orders, gets the order code. Then
//	 * initialize again the order, and then, a item is add. After each time an item
//	 * is add, the total price is printed
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		McDonalds mc = new McDonalds();
//
//		Product[] listOfProducts = mc.getMenuProducts();
//		for (Product product : listOfProducts)
//			System.out.println(product);
//
//		mc.initOrder();
//		mc.addToOrder(listOfProducts[0], 1);
//		System.out.println(mc.getOrderTotal());
//
//		mc.addToOrder(listOfProducts[1], 1);
//		System.out.println(mc.getOrderTotal());
//
//		mc.addToOrder(listOfProducts[0], 2);
//		System.out.println(mc.getOrderTotal());
//
//		mc.saveOrder();
//		System.out.println(mc.getOrderCode());
//
//		mc.initOrder();
//		System.out.println(mc.getOrderTotal());
//
//		mc.addToOrder(listOfProducts[0], 1);
//		System.out.println(mc.getOrderTotal());
//	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		McDonalds mcDonalds = new McDonalds();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true); //accept change in their skin
					JDialog.setDefaultLookAndFeelDecorated(true); //accept change in their skin
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); //change the skin to a specific one. Looks for this class in the classpath (loaded dynamic)
					
					MainWindow frame = new MainWindow(mcDonalds);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
