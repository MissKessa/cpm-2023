package uo.cpm.module;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import uo.cpm.module.service.Castlevania;
import uo.cpm.module.ui.MainWindow;

public class Main {

	/**
	 * Launch the application: it creates the service, the main window and it sets a
	 * look and feel to it
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

					Castlevania service = new Castlevania();
					MainWindow frame = new MainWindow(service);

					frame.setLocationRelativeTo(null);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
