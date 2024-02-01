package uo.cpm.mcdonalds;

import java.awt.EventQueue;

import javax.swing.UIManager;

import uo.cpm.mcdonalds.service.McDonalds;
import uo.cpm.mcdonalds.ui.MainWindow;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final McDonalds mcDonalds = new McDonalds();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					MainWindow frame = new MainWindow(mcDonalds);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
