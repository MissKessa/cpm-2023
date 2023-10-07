package uo.cpm.p3.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.service.McDonalds;

/**
 * This class builds the main window of an McDonalds application with:
 * <p>
 * -labels for the logo and McDonalds
 * <p>
 * -a combo box with all the available products, with its corresponding label
 * <p>
 * -a spinner for introducing the number of units of the product you want to
 * add, with its corresponding label
 * <p>
 * -a text field for the price, with its corresponding label
 * <p>
 * -3 buttons: add (to add the product to the order), next (to go for the next
 * window) and cancel (to exit the program)
 * 
 * @author Paula
 *
 */
public class MainWindow extends JFrame {
	/**
	 * It's the label for the logo image
	 */
	private JLabel lblLogo;
	/**
	 * It's the label for the McDonalds
	 */
	private JLabel lblMcDonalds;
	/**
	 * It's the label for the combo box of the list of products
	 */
	private JLabel lblProducts;
	/**
	 * It's the combo box that shows all the available products
	 */
	private JComboBox<Product> cbProducts;
	/**
	 * It's the label for the spinner to put the number of units
	 */
	private JLabel lblUnits;
	/**
	 * It's the spinner to put the number of units
	 */
	private JSpinner spUnits;
	/**
	 * It's the button to add the products
	 */
	private JButton btnAdd;
	/**
	 * It's the label for the price
	 */
	private JLabel lblPrice;
	/**
	 * It's the text field that shows the final price
	 */
	private JTextField txtPrice;
	/**
	 * It's the button to go to the next window (registry form)
	 */
	private JButton btnNext;
	/**
	 * It's the button to close the window
	 */
	private JButton btnCancel;
	/**
	 * It's a reference of the next window (registry form)
	 */
	private RegistryForm rF = null;

	/**
	 * It's a reference to the back end (McDonalds)
	 */
	private McDonalds mcDonalds = new McDonalds();
	/**
	 * Create the frame that sets the background, title, icon, and adds all the
	 * labels, buttons, combo box, spinners, text fields....
	 * @param mcDonalds 
	 */
	public MainWindow(McDonalds mcDonalds) {
		this.mcDonalds = mcDonalds;
		
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setResizable(false);
		setTitle("McDonald's Spain");
		setBounds(100, 100, 501, 395);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblLogo());
		getContentPane().add(getLblMcDonalds());
		getContentPane().add(getLblProducts());
		getContentPane().add(getCbProducts());
		getContentPane().add(getLblUnits());
		getContentPane().add(getSpUnits());
		getContentPane().add(getBtnAdd());
		getContentPane().add(getLblPrice());
		getContentPane().add(getTxtPrice());
		getContentPane().add(getBtnNext());
		getContentPane().add(getBtnCancel());
		
		this.getRootPane().setDefaultButton(getBtnNext());

	}

	/**
	 * Sets the name for the label and the image of the logo
	 * 
	 * @return the label for the logo
	 */
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("Image");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
			lblLogo.setBounds(0, 0, 209, 140);
		}
		return lblLogo;
	}

	/**
	 * Sets the name and the text of the McDonalds label
	 * 
	 * @return label for the McDonalds
	 */
	private JLabel getLblMcDonalds() {
		if (lblMcDonalds == null) {
			lblMcDonalds = new JLabel("McDonald's");
			lblMcDonalds.setFont(new Font("Tahoma", Font.BOLD, 42));
			lblMcDonalds.setBounds(201, 49, 272, 50);
		}
		return lblMcDonalds;
	}

	/**
	 * Sets the name of the label for the products
	 * 
	 * @return the label for the products
	 */
	private JLabel getLblProducts() {
		if (lblProducts == null) {
			lblProducts = new JLabel("Products:");
			lblProducts.setLabelFor(getCbProducts());
			lblProducts.setDisplayedMnemonic('P');
			lblProducts.setBounds(30, 162, 80, 26);
		}
		return lblProducts;
	}

	/**
	 * 
	 * @return the combo box for the list of products
	 */
	private JComboBox<Product> getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox<Product>(); //put a type to eliminate warnings
			cbProducts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					spUnits.setValue(1);
				}
			});
			cbProducts.setModel(new DefaultComboBoxModel<Product>(mcDonalds.getMenuProducts()));
			cbProducts.setBounds(30, 194, 225, 22);
		}
		return cbProducts;
	}

	/**
	 * Sets the name for the label of the spinner for the units
	 * 
	 * @return the label of the spinner
	 */
	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Units:");
			lblUnits.setDisplayedMnemonic('U');
			lblUnits.setLabelFor(getSpUnits());
			lblUnits.setBounds(285, 168, 46, 14);
		}
		return lblUnits;
	}

	/**
	 * Sets the model for the spinner for the units
	 * 
	 * @return the spinner for the units
	 */
	private JSpinner getSpUnits() {
		if (spUnits == null) {
			spUnits = new JSpinner();
			spUnits.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spUnits.setBounds(285, 195, 46, 20);
		}
		return spUnits;
	}

	/**
	 * Sets the label, background and foreground for the button for adding the
	 * products
	 * 
	 * @return the button for adding the products
	 */
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Product selectedProduct = (Product) cbProducts.getSelectedItem();
					Integer units = (Integer)spUnits.getValue(); //because we put it on the model of the spinner that works with Integers
					mcDonalds.addToOrder(selectedProduct, units);
					txtPrice.setText(mcDonalds.getOrderTotal()+ " \u20AC"); //" \u20AC" - unicode for €
					btnNext.setEnabled(true);
					spUnits.setValue(1);
				}
			});
			btnAdd.setMnemonic('A');
			btnAdd.setToolTipText("Adds the selected item to the order");
			btnAdd.setBackground(new Color(0, 128, 0));
			btnAdd.setForeground(new Color(255, 255, 255));
			btnAdd.setBounds(356, 194, 89, 23);
		}
		return btnAdd;
	}

	/**
	 * Sets the name for the label for the price
	 * 
	 * @return the label for the price
	 */
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Order price:");
			lblPrice.setBounds(285, 239, 80, 14);
		}
		return lblPrice;
	}

	/**
	 * Sets the background for the text field, and to not editable
	 * 
	 * @return the text field that shows the price
	 */
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setToolTipText("This is the final price for the order");
			txtPrice.setEditable(false);
			txtPrice.setBackground(new Color(211, 211, 211));
			txtPrice.setBounds(285, 264, 86, 20);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}

	/**
	 * Shows the next window (registry form) when the Next button is click, centers
	 * it to the parent window, and locks the parent window when the registry window
	 * is created
	 */
	private void showRegistryForm() {
		rF = new RegistryForm(this);
		rF.setLocationRelativeTo(this); // centered it to the parent window
		rF.setModal(true); // lock the parent window when registry window is created
		rF.setVisible(true);
	}

	/**
	 * Sets the name, foreground, background, and the action when the button is
	 * clicked (the Registry form window is shown)
	 * 
	 * @return the Next button
	 */
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setEnabled(false);
			btnNext.addActionListener(new ActionListener() { // creating an anonynomous inner class (can access the
																// private methods of the class out) in this code
				@Override
				public void actionPerformed(ActionEvent e) {
					// You cannot use: RegistryForm rF = new REgistryForm(this); because ^
					showRegistryForm(); // so we create an outside method to do it
				}
			});
			btnNext.setForeground(Color.WHITE);
			btnNext.setBackground(new Color(0, 128, 0));
			btnNext.setBounds(285, 310, 89, 23);
		}
		return btnNext;
	}

	/**
	 * Sets the name, foreground, background, and the action when the button is
	 * clicked (it closes the window, and the app is closed)
	 * 
	 * @return
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('C');
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(new Color(128, 0, 0));
			btnCancel.setBounds(384, 310, 89, 23);
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0); // to end the app if you click on cancel
				}
			});
		}
		return btnCancel;
	}

	public McDonalds getMcDonalds() {
		return mcDonalds;
	}
}
