package uo.cpm.p6;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.rules.Game.Level;
import uo.cpm.p6.service.SpaceInvaders;
import uo.cpm.p6.ui.MainWindow;

public class Main {

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		Properties props = new Properties(); //standard container that has key-value
		props.put("logoString",""); //prevent seeing logo of JTattoo
		HiFiLookAndFeel.setCurrentTheme(props); //initialize skin
		UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		/*
		 * Adding the library physically is not enough (copy and paste) because it's not in the classpath, so you need to add it to the build path. Right button on class > Build path > Add to build path 
		 */
		final SpaceInvaders game = new SpaceInvaders(Level.INTERMEDIATE);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					MainWindow frame = new MainWindow(game);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
