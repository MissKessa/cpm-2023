package ou.cpm.p1.userinterface;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyWindow extends JFrame{

	private JButton myButton = null;
	private JPanel contentPane = null;
	private static final long serialVersionUID = 1L;

	public MyWindow() {
		this.setSize(400, 300); //400 pixels, 300 pixels
		this.setTitle("This is my first visual application");
		
		contentPane = new JPanel(); //you can see the content pane when you run but it has the same color as the window
		contentPane.setBackground(Color.PINK); //change the color to see it
		this.setContentPane(contentPane); 
		contentPane.setLayout(null); //Layout manager doesn't decide now the position of the button
		
		myButton = new JButton();
		myButton.setText("Ok");
		myButton.setBounds(170, 220, 100, 30);
		contentPane.add(myButton);
	}

	public static void main(String[] args) {
		MyWindow myW = new MyWindow(); //add serialVersion to remove the warning. Nothing happens when you run when its only this line
		myW.setLocationRelativeTo(null); //will create the dialog in the center of the screen because it's the main window. You must do it before making it visible
		myW.setVisible(true); //to see the window

	}

}
