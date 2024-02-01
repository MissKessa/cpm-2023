package ou.cpm.p1.userinterface;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * This class models a window (JFrame) with 2 buttons (Jbuttons) of Ok and cancel; and a text field (JTextField) to allow the user to
 * introduce the username. 
 * @author paula
 *
 */
public class MyWindow extends JFrame{

	//private JButton myButton = null;
	/**
	 * It's a JButton modeling the Ok button
	 */
	private JButton btnOk;
	/**
	 * It's a JButton modeling the Cancel button
	 */
	private JButton btnCancel;
	/**
	 * It's the JPanel that contains the 2 JButtons and the JTextField
	 */
	private JPanel contentPane = null;
	
	/**
	 * It's the JTextField that allows the user to write the name
	 */
	private JTextField txtIntroduceName;
	/**
	 * It's the JLabel that shows a label to explain what to write in the JTextField
	 */
	private JLabel lblIntroduceName;
	private static final long serialVersionUID = 1L;

	/**
	 * Main constructor that sets the size and title of the window; the color of the contentPane;
	 * the size, color and text of the buttons; the txtIntroduceName and lblIntroduceName
	 */
	public MyWindow() {
		this.setSize(400, 300); //400 pixels, 300 pixels
		this.setTitle("This is my first visual application");
		
		contentPane = new JPanel(); //you can see the content pane when you run but it has the same color as the window
		contentPane.setBackground(Color.PINK); //change the color to see it
		this.setContentPane(contentPane); 
		contentPane.setLayout(null); //Layout manager doesn't decide now the position of the button
		
		btnOk = new JButton();
		btnOk.setForeground(Color.BLUE);
		btnOk.setText("Ok");
		btnOk.setBounds(144, 220, 100, 30);
		contentPane.add(btnOk);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(260, 220, 100, 30);
		contentPane.add(btnCancel);
		
		txtIntroduceName = new JTextField();
		txtIntroduceName.setBounds(180, 88, 142, 19);
		contentPane.add(txtIntroduceName);
		txtIntroduceName.setColumns(10);
		
		lblIntroduceName = new JLabel("Introduce your name:");
		lblIntroduceName.setBounds(10, 91, 135, 13);
		contentPane.add(lblIntroduceName);
	}

	/**
	 * Initializes the window, sets the window at the center of the screen and makes it visible
	 * @param args
	 */
	public static void main(String[] args) {
		MyWindow myW = new MyWindow(); //add serialVersion to remove the warning. Nothing happens when you run when its only this line
		myW.setLocationRelativeTo(null); //will create the dialog in the center of the screen because it's the main window. You must do it before making it visible
		myW.setVisible(true); //to see the window

	}
}
