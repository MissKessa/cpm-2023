package uo.cpm.p3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.service.McDonalds;
import uo.cpm.p3.service.TypeOfProduct;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * This class builds the main window of an McDonalds application with:
 * <p>
 * -labels for the logo, discount image and McDonalds
 * <p>
 * -a combo box with all the available products, with its corresponding label
 * <p>
 * -a spinner for introducing the number of units of the product you want to
 * add, with its corresponding label
 * <p>
 * -a text field for the price, with its corresponding label
 * <p>
 * -4 buttons: add (to add the product to the order), next (to go for the next
 * window), remove (to remove a product) and cancel (to exit the program)
 * <p>
 * -a text field for the units added and it's label
 * <p>
 * -a scroll pane with a text area to show the order
 * <p>
 * -a label that shows the order (text area) when you click on it
 * 
 * 
 * @author Paula
 *
 */
public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * It's the text field that shows the units added
	 */
	private JTextField txtUnitsAdded;
	/**
	 * It's the label for the units added
	 */
	private JLabel lblUnitsAdded;
	/**
	 * It's the label for the discount image
	 */
	private JLabel lblDiscountImage;
	/**
	 * It's the button for removing products
	 */
	private JButton btnRemove;
	/**
	 * It's the scrollPane that contains the text area for showing the order
	 */
	private JScrollPane scrollPane;
	/**
	 * It's the label that shows the order when you click on it
	 */
	private JLabel lblOrder;
	/**
	 * It's the text area that shows the order
	 */
	private JTextArea txtAreaOrder;
	private JMenuBar menuBar;
	private JMenu mnOrder;
	private JMenuItem mntmNewOrder;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenu mnHelp;
	private JMenuItem mntmContents;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;
	private JLabel lblProductPicture;
	private JPanel panel;
	private JButton btnBurguerFilter;
	private JButton btnDrinksFilter;
	private JButton btnSidesFilter;
	private JButton btnDessertsFilter;

	/**
	 * Create the frame that sets the background, title, icon, and adds all the
	 * labels, buttons, combo box, spinners, text fields....
	 * 
	 * @param mcDonalds
	 */
	public MainWindow(McDonalds mcDonalds) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (checkExit()) {
					System.exit(0);
				}
			}
		});
		this.mcDonalds = mcDonalds;

		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setResizable(false);
		setTitle("McDonald's Spain");
		setBounds(100, 100, 748, 480);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		getContentPane().add(getTxtUnitsAdded());
		getContentPane().add(getLblUnitsAdded());
		getContentPane().add(getLblDiscountImage());
		getContentPane().add(getBtnRemove());
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblOrder());
		getContentPane().add(getLblProductPicture());
		getContentPane().add(getPanel());
		setJMenuBar(getMenuBar_1());

	}

	/**
	 * Sets the name for the label and the image of the logo
	 * 
	 * @return the label for the logo
	 */
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("Image");
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
			lblLogo.setBounds(143, -1, 183, 140);
		}
		return lblLogo;
	}
	
	private void showProductPicture() {
		getLblProductPicture().setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/products/"+((Product)getCbProducts().getSelectedItem()).getCode()+".PNG")));
	}

	/**
	 * Sets the name and the text of the McDonalds label
	 * 
	 * @return label for the McDonalds
	 */
	private JLabel getLblMcDonalds() {
		if (lblMcDonalds == null) {
			lblMcDonalds = new JLabel("McDonald's");
			lblMcDonalds.setFont(new Font("Tahoma", Font.BOLD, 38));
			lblMcDonalds.setBounds(325, 77, 225, 50);
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
			lblProducts.setBounds(153, 150, 80, 26);
		}
		return lblProducts;
	}

	/**
	 * 
	 * @return the combo box for the list of products
	 */
	private JComboBox<Product> getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox<Product>(); // put a type to eliminate warnings
			cbProducts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					spUnits.setValue(1);
					txtUnitsAdded.setText(null);

					if (Integer.parseInt(getMcDonalds().getUnits((Product) cbProducts.getSelectedItem())) <= 0)
						btnRemove.setEnabled(false);
					btnRemove.setEnabled(true);
					showProductPicture();
				}
			});
			cbProducts.setModel(new DefaultComboBoxModel<Product>(mcDonalds.getMenuProducts()));
			showProductPicture();
			cbProducts.setBounds(153, 181, 225, 27);
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
			lblUnits.setBounds(442, 156, 46, 14);
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
			spUnits.setBounds(442, 180, 62, 28);
		}
		return spUnits;
	}

	/**
	 * Sets the name for the label for the price
	 * 
	 * @return the label for the price
	 */
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Order price:");
			lblPrice.setBounds(442, 226, 80, 14);
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
			txtPrice.setBounds(442, 251, 86, 27);
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
			btnNext.setBounds(534, 385, 89, 23);
		}
		return btnNext;
	}

	/**
	 * Sets the name, foreground, background, and the action when the button is
	 * clicked (it closes the window, and the app is closed)
	 * 
	 * @return the cancel button
	 */
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('C');
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setBackground(new Color(128, 0, 0));
			btnCancel.setBounds(633, 385, 89, 23);
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					/// System.exit(0); // to end the app if you click on cancel
					initialize();
				}
			});
		}
		return btnCancel;
	}

	/**
	 * 
	 * @return a reference to the mcDonalds
	 */
	public McDonalds getMcDonalds() {
		return mcDonalds;
	}

	/**
	 * 
	 * @return the text field for the units added
	 */
	private JTextField getTxtUnitsAdded() {
		if (txtUnitsAdded == null) {
			txtUnitsAdded = new JTextField();
			txtUnitsAdded.setEditable(false);
			txtUnitsAdded.setBounds(153, 252, 62, 26);
			txtUnitsAdded.setColumns(10);
		}
		return txtUnitsAdded;
	}

	/**
	 * 
	 * @return the label for the units added
	 */
	private JLabel getLblUnitsAdded() {
		if (lblUnitsAdded == null) {
			lblUnitsAdded = new JLabel("Units added of that product:");
			lblUnitsAdded.setBounds(153, 228, 173, 13);
		}
		return lblUnitsAdded;
	}

	/**
	 * 
	 * @return the label for the discount image
	 */
	private JLabel getLblDiscountImage() {
		if (lblDiscountImage == null) {
			lblDiscountImage = new JLabel("");
			lblDiscountImage
					.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/HappyOffer22_23.png")));
			lblDiscountImage.setVisible(false);
			lblDiscountImage.setBounds(507, 226, 225, 122);
		}
		return lblDiscountImage;
	}

	/**
	 * Checks if the user wants to exit the program by showing a confirmation window
	 * 
	 * @return true if the user wants to exit the program (clicks on the Yes button)
	 */
	private boolean checkExit() {
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to leave and cancel the order?") == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	/**
	 * It initialize the program for the next costumer (it sets all the components
	 * to their initial value and closes all the dialogs)
	 */
	public void initialize() {
		if (rF != null) { // 2º dialog is open, so I need to close it
			if (rF.getcD() != null) { // 3º dialog is open, so I need to close it
				rF.getcD().dispose(); // close the 3º dialog and tell the garbage collector to take all the resources
			}
			rF.dispose(); // close the 2º dialog and tell the garbage collector to take all the resources
		}
		// now all the dialogs are closed
		// now reinitialize the mainWidnow
		mcDonalds.initOrder();
		getCbProducts().setSelectedIndex(0);
		getSpUnits().setValue(1);
		getTxtPrice().setText("");
		getBtnNext().setEnabled(false);

	}

	/**
	 * Sets the label, background and foreground for the button for adding the
	 * products, action when you click...
	 * 
	 * @return the button for adding the products
	 */
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Product selectedProduct = (Product) cbProducts.getSelectedItem();
					Integer units = (Integer) spUnits.getValue(); // because we put it on the model of the spinner that
																	// works with Integers
					mcDonalds.addToOrder(selectedProduct, units);
					txtPrice.setText(String.format("%.2f", mcDonalds.getOrderTotal()) + " \u20AC"); // " \u20AC"
																									// -unicode for €
					if (mcDonalds.isDiscountApplied()) {
						lblDiscountImage.setVisible(true);
					}
					btnRemove.setEnabled(true);
					btnNext.setEnabled(true);
					spUnits.setValue(1);
					txtUnitsAdded.setText(mcDonalds.getUnits(selectedProduct));
				}
			});
			btnAdd.setMnemonic('A');
			btnAdd.setToolTipText("Adds the selected item to the order");
			btnAdd.setBackground(new Color(0, 128, 0));
			btnAdd.setForeground(new Color(255, 255, 255));
			btnAdd.setBounds(525, 182, 89, 23);
		}
		return btnAdd;
	}

	/**
	 * 
	 * @return the remove button
	 */
	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.setEnabled(false);
			btnRemove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getMcDonalds().removeProduct((Product) cbProducts.getSelectedItem(), (Integer) spUnits.getValue());
					if (!mcDonalds.isDiscountApplied()) {
						lblDiscountImage.setVisible(false);
					}
					if (getMcDonalds().getOrderTotal() == 0) {
						btnNext.setEnabled(false);
						btnRemove.setEnabled(false);
					}
					txtPrice.setText(String.format("%.2f", mcDonalds.getOrderTotal()) + " \u20AC");
					spUnits.setValue(1);
					txtUnitsAdded.setText(mcDonalds.getUnits((Product) cbProducts.getSelectedItem()));
				}
			});
			btnRemove.setToolTipText("Elimates the selected item to the order");
			btnRemove.setMnemonic('R');
			btnRemove.setForeground(Color.WHITE);
			btnRemove.setBackground(new Color(0, 128, 0));
			btnRemove.setBounds(624, 182, 89, 23);
		}
		return btnRemove;
	}

	/**
	 * 
	 * @return the scrollPane that contains the text area for showing the order
	 */
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(562, 56, 151, 105);
			scrollPane.setViewportView(getTxtAreaOrder());
			scrollPane.setVisible(false);
		}
		return scrollPane;
	}

	/**
	 * 
	 * @return the label that shows the order when you click on it (pressed the
	 *         mouse)
	 */
	private JLabel getLblOrder() {
		if (lblOrder == null) {
			lblOrder = new JLabel("Order:");
			lblOrder.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					scrollPane.setVisible(true);
					txtAreaOrder.setText(getMcDonalds().getOrder());
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					scrollPane.setVisible(false);
				}
			});
			lblOrder.setLabelFor(getTxtAreaOrder());
			lblOrder.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/pedido.png")));
			lblOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblOrder.setBounds(580, 11, 138, 42);
		}
		return lblOrder;
	}

	/**
	 * 
	 * @return the text area contains and showing the information of the order
	 */
	private JTextArea getTxtAreaOrder() {
		if (txtAreaOrder == null) {
			txtAreaOrder = new JTextArea();
			txtAreaOrder.setEditable(false);
			txtAreaOrder.setText(getMcDonalds().getOrder());
		}
		return txtAreaOrder;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnOrder());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}
	private JMenu getMnOrder() {
		if (mnOrder == null) {
			mnOrder = new JMenu("Order");
			mnOrder.setMnemonic('O');
			mnOrder.add(getMntmNewOrder());
			mnOrder.add(getSeparator());
			mnOrder.add(getMntmExit());
		}
		return mnOrder;
	}
	private JMenuItem getMntmNewOrder() {
		if (mntmNewOrder == null) {
			mntmNewOrder = new JMenuItem("New Order");
			mntmNewOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to discard the current order?")==JOptionPane.YES_OPTION) {
						initialize();
					}
				}
			});
			mntmNewOrder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			mntmNewOrder.setMnemonic('N');
		}
		return mntmNewOrder;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkExit()) {
						System.exit(0);
					}
				}
			});
			mntmExit.setMnemonic('E');
		}
		return mntmExit;
	}
	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}
	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setMnemonic('t');
		}
		return mntmContents;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}
	private JLabel getLblProductPicture() {
		if (lblProductPicture == null) {
			lblProductPicture = new JLabel("");
			lblProductPicture.setHorizontalAlignment(SwingConstants.CENTER);
			lblProductPicture.setBounds(239, 245, 183, 165);

		}
		return lblProductPicture;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 0, 143, 419);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getBtnBurguerFilter());
			panel.add(getBtnDrinksFilter());
			panel.add(getBtnSidesFilter());
			panel.add(getBtnDessertsFilter());
		}
		return panel;
	}
	private JButton getBtnBurguerFilter() {
		if (btnBurguerFilter == null) {
			btnBurguerFilter = new JButton("Burguers");
			btnBurguerFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mcDonalds.getMenuProductsByType(TypeOfProduct.Burger)));
				}
			});
			btnBurguerFilter.setBackground(Color.WHITE);
			btnBurguerFilter.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnBurguerFilter.setHorizontalTextPosition(SwingConstants.CENTER);
			btnBurguerFilter.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/filter/Burguer.png")));
		}
		return btnBurguerFilter;
	}
	private JButton getBtnDrinksFilter() {
		if (btnDrinksFilter == null) {
			btnDrinksFilter = new JButton("Drinks");
			btnDrinksFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mcDonalds.getMenuProductsByType(TypeOfProduct.Drink)));
				}
			});
			btnDrinksFilter.setBackground(Color.WHITE);
			btnDrinksFilter.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnDrinksFilter.setHorizontalTextPosition(SwingConstants.CENTER);
			btnDrinksFilter.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/filter/Drink.png")));
		}
		return btnDrinksFilter;
	}
	private JButton getBtnSidesFilter() {
		if (btnSidesFilter == null) {
			btnSidesFilter = new JButton("Sides");
			btnSidesFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mcDonalds.getMenuProductsByType(TypeOfProduct.Side)));
				}
			});
			btnSidesFilter.setBackground(Color.WHITE);
			btnSidesFilter.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnSidesFilter.setHorizontalTextPosition(SwingConstants.CENTER);
			btnSidesFilter.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/filter/Sides.png")));
		}
		return btnSidesFilter;
	}
	private JButton getBtnDessertsFilter() {
		if (btnDessertsFilter == null) {
			btnDessertsFilter = new JButton("Desserts");
			btnDessertsFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mcDonalds.getMenuProductsByType(TypeOfProduct.Dessert)));
				}
			});
			btnDessertsFilter.setBackground(Color.WHITE);
			btnDessertsFilter.setHorizontalTextPosition(SwingConstants.CENTER);
			btnDessertsFilter.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnDessertsFilter.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/filter/Dessert.png")));
		}
		return btnDessertsFilter;
	}
}
