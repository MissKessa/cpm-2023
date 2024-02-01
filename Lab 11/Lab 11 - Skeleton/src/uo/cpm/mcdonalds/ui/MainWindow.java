package uo.cpm.mcdonalds.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import uo.cpm.mcdonalds.model.Product;
import uo.cpm.mcdonalds.service.McDonalds;
import uo.cpm.mcdonalds.util.FileUtil;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblLblname;
	private JPanel pnInfo1;
	private JPanel pnlLogo;
	private JPanel pnProducts;
	private ButtonAction aB;
	private JPanel pnBts1;
	private JPanel pnBts2;
	private JPanel pnBts3;
	private JPanel pnContents;
	private JPanel pn2;
	private JPanel pn3;
	private JPanel pnCustonerInfo;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblYear;
	private JComboBox<String> cbYears;
	private JLabel lbPasw1;
	private JPasswordField psw1;
	private JLabel lbPasw2;
	private JPasswordField psw2;
	private JPanel pn1;
	private JPanel pnForm;
	private JPanel pnOrderData;
	private JRadioButton rbOnsite;
	private JRadioButton rbTakeAway;
	private JPanel pnInfo2;
	private JPanel pnConfirmacion;
	private JPanel pnInfo3;
	private JLabel lblAdvise;
	private JLabel lbOk;
	private JLabel lblCode;
	private JTextField txtCode;
	private final ButtonGroup grOrder = new ButtonGroup();
	private McDonalds mcDonalds;
	private JTabbedPane pnOrder;
	private JScrollPane scFood;
	private JScrollPane scDrinks;
	private JList foodList;
	private JList drinksList;
	
	private DefaultListModel<Product> foodModel =null;
	private DefaultListModel<Product> drinksModel =null;
	private JTextField txtPrice;
	private JButton btnCancel;
	private JButton btnNext1;
	private JButton btnPrevious2;
	private JButton btnNext2;
	private JButton btnPrevious3;
	private JButton btnFinish;

	/**
	 * Create the frame.
	 */
	public MainWindow(McDonalds mcDonalds) {
		this.mcDonalds = mcDonalds;
		aB = new ButtonAction();
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainWindow.class.getResource("/img/logo.PNG")));
		setTitle("McDonald's Spain");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 820);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnlLogo(), BorderLayout.NORTH);
		contentPane.add(getPnContents(), BorderLayout.CENTER);

		// To change the size of the pictures when we resize the buttons.
		// This avoids the problem of the 0 size exception.
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				associateImagesToButtons();
			}
		});
	}

		
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setIcon(new ImageIcon(MainWindow.class
					.getResource("/img/logo.PNG")));
		}
		return lblLogo;
	}

	private JLabel getLblLblName() {
		if (lblLblname == null) {
			lblLblname = new JLabel("McDonald's");
			lblLblname.setFont(new Font("Arial Black", Font.PLAIN, 44));
			lblLblname.setForeground(Color.BLACK);
		}
		return lblLblname;
	}

	protected void initialize() {
		mcDonalds.initOrder();
		foodModel.clear();
		drinksModel.clear();
		getTxtPrice().setText("Price: 0.0");
		getBtnNext1().setEnabled(true);
		showPn1();
		
		//Reinitialize the rest of the forms
	}

	private JPanel getPnInfo1() {
		if (pnInfo1 == null) {
			pnInfo1 = new JPanel();
			pnInfo1.setBackground(Color.WHITE);
			pnInfo1.setLayout(new BorderLayout(0, 0));
			pnInfo1.add(getPnBts1(), BorderLayout.SOUTH);
			pnInfo1.add(getPnOrder(), BorderLayout.CENTER);

		}
		return pnInfo1;
	}

	private JPanel getPnlLogo() {
		if (pnlLogo == null) {
			pnlLogo = new JPanel();
			pnlLogo.setBackground(Color.WHITE);
			pnlLogo.setLayout(new GridLayout(1, 0, 0, 0));
			pnlLogo.add(getLblLogo());
			pnlLogo.add(getLblLblName());
		}
		return pnlLogo;
	}

	private JPanel getPnProducts() {
		if (pnProducts == null) {
			pnProducts = new JPanel();
			pnProducts.setBorder(new LineBorder(Color.ORANGE, 4));
			pnProducts.setBackground(Color.WHITE);
			pnProducts.setLayout(new GridLayout(0,5,3,3));
			createProductButtons();

		}
		return pnProducts;
	}

	
	private void createProductButtons() {
		getPnProducts().removeAll();
		for (Product p : mcDonalds.getMenuProducts()) {
			getPnProducts().add(newButton(mcDonalds.getMenuProducts().indexOf(p),p));
		}
		
	}


	private void adaptImage(JButton button, String imagePath) {
		ImageIcon tmpImagen = new ImageIcon(getClass().getResource(imagePath));
		float delta = ((button.getWidth() * 100) / tmpImagen.getIconWidth()) / 100f;
		if (tmpImagen.getIconHeight() > button.getHeight())
			delta = ((button.getHeight() * 100) / tmpImagen.getIconHeight()) / 100f;
		int ancho = (int) (tmpImagen.getIconWidth() * delta);
		int alto = (int) (tmpImagen.getIconHeight() * delta);
		button.setIcon(new ImageIcon(tmpImagen.getImage().getScaledInstance(
				ancho, alto, Image.SCALE_SMOOTH)));
	}

	private void associateImagesToButtons() {
		for (int i = 0; i < pnProducts.getComponents().length; i++) {
			JButton boton = (JButton) (pnProducts.getComponents()[i]);
			adaptImage(boton, mcDonalds.getMenuProducts().get(i).getImagePath());
		}
	}

	private JButton newButton(Integer position, Product p) {
		JButton button = new JButton("");
		button.setBackground(Color.white);
		button.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		button.setToolTipText(p.toString());
		button.setActionCommand(position.toString());
		button.addActionListener(aB);
		return button;
	}

	class ButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton bt = (JButton) e.getSource();
			addToOrder(Integer.parseInt(bt.getActionCommand()));
		}
	}

	private void addToOrder(int position) {
		Product p =mcDonalds.getMenuProducts().get(position);
		mcDonalds.addToOrder(p, 1);
		showInList(p);
		getTxtPrice().setText("Price: "+String.format("%.2f", mcDonalds.getOrderTotal()));
		btnNext1.setEnabled(true);
	}


	private void showInList(Product a) {
		if (a.getType().equals("Drink")) {
			drinksModel.addElement(a);
		} else {
			foodModel.addElement(a);
		}
	}

	private void showPn1() {
		getPnInfo1().add(getPnOrder());
		getPnBts1().add(getTxtPrice(),0);
		
		((CardLayout)getPnContents().getLayout()).show(pnContents,"pn1");
	}

	private void showPn2() {
		getPnInfo2().add(getPnOrder());
		getPnBts2().add(getTxtPrice(),0);
		
		((CardLayout)getPnContents().getLayout()).show(pnContents,"pn2");
	}

	private void showPn3() {
		if (checkFields())	{
			getPnInfo3().add(getPnOrder());
			getPnBts3().add(getTxtPrice(),0);
			
			((CardLayout)getPnContents().getLayout()).show(pnContents,"pn3");
		}
	}
	
	private JPanel getPnBts1() {
		if (pnBts1 == null) {
			pnBts1 = new JPanel();
			pnBts1.setBackground(Color.WHITE);
			pnBts1.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts1.add(getTxtPrice());
			pnBts1.add(getBtnCancel());
			pnBts1.add(getBtnNext1());
		}
		return pnBts1;
	}

	private JPanel getPnBts2() {
		if (pnBts2 == null) {
			pnBts2 = new JPanel();
			pnBts2.setBackground(Color.WHITE);
			pnBts2.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts2.add(getBtnPrevious2());
			pnBts2.add(getBtnNext2());
		}
		return pnBts2;
	}

	private JPanel getPnBts3() {
		if (pnBts3 == null) {
			pnBts3 = new JPanel();
			pnBts3.setBackground(Color.WHITE);
			pnBts3.setLayout(new GridLayout(1, 3, 0, 0));
			pnBts3.add(getBtnPrevious3());
			pnBts3.add(getBtnFinish());
		}
		return pnBts3;
	}

	private JPanel getPnContents() {
		if (pnContents == null) {
			pnContents = new JPanel();
			pnContents.setLayout(new CardLayout(0, 0));
			pnContents.add(getPn1(), "pn1");
			pnContents.add(getPn2(), "pn2");
			pnContents.add(getPn3(), "pn3");
		}
		return pnContents;
	}

	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(Color.WHITE);
			pn2.setLayout(new BorderLayout(0, 0));
			pn2.add(getPnForm(), BorderLayout.CENTER);
			pn2.add(getPnInfo2(), BorderLayout.SOUTH);
		}
		return pn2;
	}

	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setBackground(Color.WHITE);
			pn3.setLayout(new BorderLayout(0, 0));
			pn3.add(getPnConfirmation());
			pn3.add(getPnInfo3(), BorderLayout.SOUTH);
		}
		return pn3;
	}

	private JComboBox<String> getCbYears() {
		if (cbYears == null) {
			String[] years = new String[90];
			for (int i = 0; i < 90; i++) {
				String year = "" + ((90 - i) + 1920);
				years[i] = year;
			}
			cbYears = new JComboBox<String>();
			cbYears.setFont(new Font("Arial", Font.PLAIN, 14));
			cbYears.setModel(new DefaultComboBoxModel<String>(years));
			cbYears.setBounds(new Rectangle(193, 77, 157, 25));
		}
		return cbYears;
	}

	private boolean isEmpty() {
		return (txtName.getText().equals("")
				|| (String.valueOf(psw1.getPassword()).equals("")) || (String
					.valueOf(psw2.getPassword()).equals("")));

	}

	private boolean isWrong() {
		return (!String.valueOf(psw1.getPassword()).equals(
				String.valueOf(psw2.getPassword())));
	}

	private JPanel getPnCustomerInfo() {
		if (pnCustonerInfo == null) {
			pnCustonerInfo = new JPanel();
			pnCustonerInfo.setBounds(104, 58, 558, 224);
			pnCustonerInfo.setBorder(new TitledBorder(null,
					"Customer Information", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnCustonerInfo.setBackground(Color.WHITE);
			pnCustonerInfo.setLayout(null);
			pnCustonerInfo.add(getLbName());
			pnCustonerInfo.add(getTxtName());
			pnCustonerInfo.add(getLbYear());
			pnCustonerInfo.add(getCbYears());
			pnCustonerInfo.add(getLbPasw1());
			pnCustonerInfo.add(getPsw1());
			pnCustonerInfo.add(getLbPasw2());
			pnCustonerInfo.add(getPsw2());
		}
		return pnCustonerInfo;
	}

	private JLabel getLbName() {
		if (lblName == null) {
			lblName = new JLabel();
			lblName.setText("Name and Surname:");
			lblName.setFont(new Font("Arial", Font.PLAIN, 14));
			lblName.setDisplayedMnemonic('N');
			lblName.setBounds(30, 31, 132, 20);
		}
		return lblName;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Arial", Font.PLAIN, 14));
			txtName.setBounds(193, 24, 330, 25);
		}
		return txtName;
	}

	private JLabel getLbYear() {
		if (lblYear == null) {
			lblYear = new JLabel("Birth Year:");
			lblYear.setFont(new Font("Arial", Font.PLAIN, 14));
			lblYear.setDisplayedMnemonic('A');
			lblYear.setBounds(30, 81, 121, 16);
		}
		return lblYear;
	}

	private JLabel getLbPasw1() {
		if (lbPasw1 == null) {
			lbPasw1 = new JLabel();
			lbPasw1.setText("Password:");
			lbPasw1.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw1.setDisplayedMnemonic('P');
			lbPasw1.setBounds(new Rectangle(13, 123, 105, 16));
			lbPasw1.setBounds(30, 129, 105, 16);
		}
		return lbPasw1;
	}

	private JPasswordField getPsw1() {
		if (psw1 == null) {
			psw1 = new JPasswordField();
			psw1.setFont(new Font("Arial", Font.PLAIN, 14));
			psw1.setBounds(new Rectangle(176, 121, 218, 25));
			psw1.setBounds(193, 122, 218, 25);
		}
		return psw1;
	}

	private JLabel getLbPasw2() {
		if (lbPasw2 == null) {
			lbPasw2 = new JLabel();
			lbPasw2.setText("Repeat password:");
			lbPasw2.setFont(new Font("Arial", Font.PLAIN, 14));
			lbPasw2.setDisplayedMnemonic('R');
			lbPasw2.setBounds(new Rectangle(13, 167, 151, 16));
			lbPasw2.setBounds(30, 181, 151, 16);
		}
		return lbPasw2;
	}

	private JPasswordField getPsw2() {
		if (psw2 == null) {
			psw2 = new JPasswordField();
			psw2.setFont(new Font("Arial", Font.PLAIN, 14));
			psw2.setBounds(new Rectangle(176, 163, 218, 25));
			psw2.setBounds(193, 172, 218, 25);
		}
		return psw2;
	}

	public boolean checkFields() {
		if (isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: Some fields are empty");
			return false;
		} else {
			if (isWrong()) {
				JOptionPane.showMessageDialog(null,
						"Error: Passwords do not match");
				return false;
			}
		}
		return true;
	}


	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setLayout(new BorderLayout(0, 0));
			pn1.add(getPnProducts(), BorderLayout.CENTER);
			pn1.add(getPnInfo1(), BorderLayout.SOUTH);
		}
		return pn1;
	}

	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setBorder(new LineBorder(Color.ORANGE, 4));
			pnForm.setBackground(Color.WHITE);
			pnForm.setLayout(null);
			pnForm.add(getPnCustomerInfo());
			pnForm.add(getPnOrderData());
		}
		return pnForm;
	}

	private JPanel getPnOrderData() {
		if (pnOrderData == null) {
			pnOrderData = new JPanel();
			pnOrderData.setBorder(new TitledBorder(UIManager
					.getBorder("TitledBorder.border"), "Order Information",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0,
							0, 0)));
			pnOrderData.setBackground(Color.WHITE);
			pnOrderData.setBounds(112, 304, 204, 60);
			pnOrderData.add(getRbOnsite());
			pnOrderData.add(getRbTakeAway());
		}
		return pnOrderData;
	}

	private JRadioButton getRbOnsite() {
		if (rbOnsite == null) {
			rbOnsite = new JRadioButton();
			grOrder.add(rbOnsite);
			rbOnsite.setText("On site");
			rbOnsite.setSelected(true);
			rbOnsite.setMnemonic('L');
			rbOnsite.setFont(new Font("Arial", Font.PLAIN, 14));
			rbOnsite.setBounds(new Rectangle(17, 27, 94, 24));
			rbOnsite.setBackground(Color.WHITE);
		}
		return rbOnsite;
	}

	private JRadioButton getRbTakeAway() {
		if (rbTakeAway == null) {
			rbTakeAway = new JRadioButton();
			grOrder.add(rbTakeAway);
			rbTakeAway.setText("Take Away");
			rbTakeAway.setMnemonic('r');
			rbTakeAway.setFont(new Font("Arial", Font.PLAIN, 14));
			rbTakeAway.setBounds(new Rectangle(115, 27, 86, 24));
			rbTakeAway.setBackground(Color.WHITE);
		}
		return rbTakeAway;
	}

	private JPanel getPnInfo2() {
		if (pnInfo2 == null) {
			pnInfo2 = new JPanel();
			pnInfo2.setBackground(Color.WHITE);
			pnInfo2.setLayout(new BorderLayout(0, 0));
			pnInfo2.add(getPnBts2(), BorderLayout.SOUTH);
		}
		return pnInfo2;
	}

	private JPanel getPnConfirmation() {
		if (pnConfirmacion == null) {
			pnConfirmacion = new JPanel();
			pnConfirmacion.setBorder(new LineBorder(Color.ORANGE, 4));
			pnConfirmacion.setBackground(Color.WHITE);
			pnConfirmacion.setLayout(null);
			pnConfirmacion.add(getLblAdvise());
			pnConfirmacion.add(getLbOk());
			pnConfirmacion.add(getLblCode());
			pnConfirmacion.add(getTxtCode());
		}
		return pnConfirmacion;
	}

	private JPanel getPnInfo3() {
		if (pnInfo3 == null) {
			pnInfo3 = new JPanel();
			pnInfo3.setBackground(Color.WHITE);
			pnInfo3.setLayout(new BorderLayout(0, 0));
			pnInfo3.add(getPnBts3(), BorderLayout.SOUTH);
		}
		return pnInfo3;
	}

	private void finish() {
		mcDonalds.saveOrder();
		initialize();
	}

	private JLabel getLblAdvise() {
		if (lblAdvise == null) {
			lblAdvise = new JLabel("Your order is being processed");
			lblAdvise.setFont(new Font("Tahoma", Font.BOLD, 28));
			lblAdvise.setBounds(135, 104, 478, 35);
		}
		return lblAdvise;
	}

	private JLabel getLbOk() {
		if (lbOk == null) {
			lbOk = new JLabel("");
			lbOk.setIcon(new ImageIcon(MainWindow.class
					.getResource("/img/ok.png")));
			lbOk.setBounds(50, 91, 73, 52);
		}
		return lbOk;
	}

	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Your delivery code is:");
			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblCode.setBounds(138, 172, 191, 26);
		}
		return lblCode;
	}

	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setFont(new Font("Tahoma", Font.PLAIN, 20));
			txtCode.setEditable(false);
			txtCode.setText(FileUtil.setFileName());
			txtCode.setBounds(341, 161, 191, 45);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JTabbedPane getPnOrder() {
		if (pnOrder == null) {
			pnOrder = new JTabbedPane(JTabbedPane.TOP);
			pnOrder.addTab("Food", null, getScFood(), null);
			pnOrder.addTab("Drinks", null, getScDrinks(), null);
			pnOrder.setMnemonicAt(0, KeyEvent.VK_F); //Set mnemonic F for Food Tab
			pnOrder.setMnemonicAt(1, KeyEvent.VK_D); //Set mnemonic D for Drinks Tab
		}
		return pnOrder;
	}
	private JScrollPane getScFood() {
		if (scFood == null) {
			scFood = new JScrollPane();
			scFood.setViewportView(getFoodList());
		}
		return scFood;
	}
	private JScrollPane getScDrinks() {
		if (scDrinks == null) {
			scDrinks = new JScrollPane();
			scDrinks.setViewportView(getDrinksList());
		}
		return scDrinks;
	}
	private JList getFoodList() {
		if (foodList == null) {
			foodList = new JList();
			foodModel= new DefaultListModel<Product>();
			foodList.setModel(foodModel);
		}
		return foodList;
	}
	private JList getDrinksList() {
		if (drinksList == null) {
			drinksList = new JList();
			drinksModel= new DefaultListModel<Product>();
			drinksList.setModel(drinksModel);
		}
		return drinksList;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setEditable(false);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize();
				}
			});
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.setBackground(new Color(204, 0, 0));
		}
		return btnCancel;
	}
	private JButton getBtnNext1() {
		if (btnNext1 == null) {
			btnNext1 = new JButton("Next");
			btnNext1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPn2();
				}
			});
			btnNext1.setEnabled(false);
			btnNext1.setBackground(new Color(102, 204, 0));
		}
		return btnNext1;
	}
	private JButton getBtnPrevious2() {
		if (btnPrevious2 == null) {
			btnPrevious2 = new JButton("Previous");
			btnPrevious2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPn1();
				}
			});
			btnPrevious2.setMnemonic('P');
			btnPrevious2.setBackground(new Color(153, 204, 0));
		}
		return btnPrevious2;
	}
	private JButton getBtnNext2() {
		if (btnNext2 == null) {
			btnNext2 = new JButton("Next");
			btnNext2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPn3();
				}
			});
			btnNext2.setBackground(new Color(153, 204, 0));
		}
		return btnNext2;
	}
	private JButton getBtnPrevious3() {
		if (btnPrevious3 == null) {
			btnPrevious3 = new JButton("Previous");
			btnPrevious3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPn2();
				}
			});
			btnPrevious3.setBackground(new Color(153, 204, 0));
			btnPrevious3.setMnemonic('P');
		}
		return btnPrevious3;
	}
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					finish();
				}
			});
			btnFinish.setBackground(new Color(153, 204, 0));
		}
		return btnFinish;
	}
}
