package uo.cpm.p3.ui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import uo.cpm.p3.model.Product;
import uo.cpm.p3.model.TypeOfProduct;
import uo.cpm.p3.service.McDonalds;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private McDonalds mc;
	private RegistryForm rF;

	private JLabel lblLogo;
	private JLabel lblMcDonalds;
	private JLabel lblProducts;
	private JComboBox<Product> cbProducts;
	private JLabel lblUnits;
	private JSpinner spUnits;
	private JButton btnAdd;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JButton btnCancel;
	private JButton btnNext;
	private JLabel lblTotalUnits;
	private JTextField txtTotalUnits;
	private JLabel lblDiscount;
	private JLabel lblImageProduct;
	private JButton btnRemove;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblOrder;
	private JPanel pnFilters;
	private JButton btnFilterBurguers;
	private JButton btnFilterDrinks;
	private JButton btnFilterExtras;
	private JButton btnFilterDesserts;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setTitle("McDonald's");
		mc = new McDonalds();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblLogo());
		contentPane.add(getLblMcDonalds());
		contentPane.add(getLblProducts());
		contentPane.add(getCbProducts());
		contentPane.add(getLblUnits());
		contentPane.add(getSpUnits());
		contentPane.add(getBtnAdd());
		contentPane.add(getLblPrice());
		contentPane.add(getTxtPrice());
		contentPane.add(getBtnCancel());
		contentPane.add(getBtnNext());
		this.getRootPane().setDefaultButton(getBtnNext());
		contentPane.add(getLblTotalUnits());
		contentPane.add(getTxtTotalUnits());
		contentPane.add(getLblDiscount());
		contentPane.add(getLblImageProduct());
		contentPane.add(getBtnRemove());
		contentPane.add(getScrollPane());
		contentPane.add(getLblOrder());
		contentPane.add(getPnFilters());

	}

	public McDonalds getMc() {
		return mc;
	}

	public void initialize() {
		if (rF != null) {
			if (rF.getCd() != null) {
				rF.getCd().dispose();
			}
			rF.dispose();
		}
		mc = new McDonalds();
		getCbProducts().setSelectedIndex(0);
		getTxtTotalUnits().setText("" + 0);
		getTxtPrice().setText("" + 0);
		getSpUnits().setValue(1);
		getLblDiscount().setIcon(null);
		getLblImageProduct().setIcon(new ImageIcon(MainWindow.class.getResource(
				"/uo/cpm/p3/ui/img/products/" + ((Product) cbProducts.getSelectedItem()).getCode() + ".png")));
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setToolTipText("Logo");
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
			lblLogo.setBounds(164, 10, 197, 134);
		}
		return lblLogo;
	}

	private JLabel getLblMcDonalds() {
		if (lblMcDonalds == null) {
			lblMcDonalds = new JLabel("McDonald's");
			lblMcDonalds.setToolTipText("McDonald's");
			lblMcDonalds.setBounds(387, 50, 222, 47);
		}
		return lblMcDonalds;
	}

	private JLabel getLblProducts() {
		if (lblProducts == null) {
			lblProducts = new JLabel("Products:");
			lblProducts.setDisplayedMnemonic('P');
			lblProducts.setToolTipText("Products");
			lblProducts.setLabelFor(getCbProducts());
			lblProducts.setBounds(174, 154, 79, 19);
		}
		return lblProducts;
	}

	private JComboBox<Product> getCbProducts() {
		if (cbProducts == null) {
			cbProducts = new JComboBox<Product>();
			cbProducts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getLblImageProduct()
							.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/products/"
									+ ((Product) cbProducts.getSelectedItem()).getCode() + ".png")));

					if (mc.getUnits(((Product) cbProducts.getSelectedItem())) == 0) {
						getBtnRemove().setEnabled(false);
					} else
						getBtnRemove().setEnabled(true);
				}
			});
			cbProducts.setModel(new DefaultComboBoxModel<Product>(mc.getMenuProducts()));
			cbProducts.setBounds(174, 175, 259, 26);
		}
		return cbProducts;
	}

	private JLabel getLblUnits() {
		if (lblUnits == null) {
			lblUnits = new JLabel("Units:");
			lblUnits.setDisplayedMnemonic('U');
			lblUnits.setLabelFor(getSpUnits());
			lblUnits.setBounds(465, 152, 54, 16);
		}
		return lblUnits;
	}

	private JSpinner getSpUnits() {
		if (spUnits == null) {
			spUnits = new JSpinner();
			spUnits.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spUnits.setToolTipText("Units");
			spUnits.setBounds(464, 176, 55, 19);
		}
		return spUnits;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mc.addToOrder((Product) getCbProducts().getSelectedItem(), (Integer) getSpUnits().getValue());
					getTxtTotalUnits().setText("" + mc.getUnits((Product) getCbProducts().getSelectedItem()));
					getTxtPrice().setText("" + mc.getOrderTotal());

					getCbProducts().setSelectedIndex(0);
					getSpUnits().setValue(1);
					if (mc.isDiscountApplied()) {
						getLblDiscount().setIcon(new ImageIcon(
								MainWindow.class.getResource("/uo/cpm/p3/ui/img/Lab 3HappyOffer22_23.png")));
					}
					getBtnNext().setEnabled(true);
					// getBtnRemove().setEnabled(true);

				}
			});
			btnAdd.setToolTipText("Add product");
			btnAdd.setMnemonic('A');
			btnAdd.setBounds(532, 177, 85, 21);
		}
		return btnAdd;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Order price:");
			lblPrice.setLabelFor(getTxtPrice());
			lblPrice.setBounds(464, 237, 68, 19);
		}
		return lblPrice;
	}

	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setText("0");
			txtPrice.setToolTipText("Order price");
			txtPrice.setEditable(false);
			txtPrice.setBounds(464, 255, 68, 26);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnCancel.setMnemonic('C');
			btnCancel.setToolTipText("Cancel");
			btnCancel.setBounds(619, 315, 85, 21);
		}
		return btnCancel;
	}

	private void showConfirmationWindow() {
		rF = new RegistryForm(this);
		rF.setLocationRelativeTo(this);
		rF.setModal(false);
		rF.setVisible(true);

	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setEnabled(false);
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showConfirmationWindow();
				}

			});
			btnNext.setToolTipText("Next");
			btnNext.setBounds(524, 315, 85, 21);
		}
		return btnNext;
	}

	private JLabel getLblTotalUnits() {
		if (lblTotalUnits == null) {
			lblTotalUnits = new JLabel("Total units:");
			lblTotalUnits.setLabelFor(getTxtTotalUnits());
			lblTotalUnits.setBounds(164, 240, 68, 26);
		}
		return lblTotalUnits;
	}

	private JTextField getTxtTotalUnits() {
		if (txtTotalUnits == null) {
			txtTotalUnits = new JTextField();
			txtTotalUnits.setText("0");
			txtTotalUnits.setEditable(false);
			txtTotalUnits.setBounds(164, 269, 62, 26);
			txtTotalUnits.setColumns(10);
		}
		return txtTotalUnits;
	}

	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("");
			lblDiscount.setBounds(243, 225, 180, 111);
		}
		return lblDiscount;
	}

	private JLabel getLblImageProduct() {
		if (lblImageProduct == null) {
			lblImageProduct = new JLabel("");
			lblImageProduct.setBounds(538, 208, 180, 96);
			getLblImageProduct().setIcon(new ImageIcon(MainWindow.class.getResource(
					"/uo/cpm/p3/ui/img/products/" + ((Product) cbProducts.getSelectedItem()).getCode() + ".png")));
		}
		return lblImageProduct;
	}

	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Remove");
			btnRemove.setEnabled(false);
			btnRemove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mc.removeToOrder((Product) getCbProducts().getSelectedItem(), (Integer) getSpUnits().getValue());
					getTxtTotalUnits().setText("" + mc.getUnits((Product) getCbProducts().getSelectedItem()));
					getTxtPrice().setText("" + mc.getOrderTotal());
					getCbProducts().setSelectedIndex(0);
					getSpUnits().setValue(1);
					if (!mc.isDiscountApplied()) {
						getLblDiscount().setIcon(null);
					}
					if (mc.getOrderTotal() == 0)
						getBtnNext().setEnabled(false);
				}
			});
			btnRemove.setBounds(633, 177, 85, 21);
		}
		return btnRemove;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setVisible(false);
			scrollPane.setBounds(477, 50, 227, 94);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setText(mc.toStringOrderList() + "\nTotal: " + mc.getOrderTotal());
		}
		return textArea;
	}

	private JLabel getLblOrder() {
		if (lblOrder == null) {
			lblOrder = new JLabel("");
			lblOrder.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					getScrollPane().setVisible(true);
					getTextArea().setText(mc.toStringOrderList() + "\nTotal: " + mc.getOrderTotal());
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					getScrollPane().setVisible(false);
					getTextArea().setText(mc.toStringOrderList() + "\nTotal: " + mc.getOrderTotal());
				}
			});
			lblOrder.setIcon(new ImageIcon(MainWindow.class.getResource("/uo/cpm/p3/ui/img/order.png")));
			lblOrder.setLabelFor(getScrollPane());
			lblOrder.setToolTipText("Order");
			lblOrder.setBounds(595, 10, 109, 40);
		}
		return lblOrder;
	}

	private JPanel getPnFilters() {
		if (pnFilters == null) {
			pnFilters = new JPanel();
			pnFilters.setBounds(10, 10, 131, 326);
			pnFilters.setLayout(new GridLayout(0, 1, 0, 0));
			pnFilters.add(getBtnFilterBurguers());
			pnFilters.add(getBtnFilterDrinks());
			pnFilters.add(getBtnFilterExtras());
			pnFilters.add(getBtnFilterDesserts());
		}
		return pnFilters;
	}

	private JButton getBtnFilterBurguers() {
		if (btnFilterBurguers == null) {
			btnFilterBurguers = new JButton("Burguer");
			btnFilterBurguers.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mc.getMenuProducts(TypeOfProduct.Burger)));
				}
			});
			btnFilterBurguers.setMnemonic('B');
		}
		return btnFilterBurguers;
	}

	private JButton getBtnFilterDrinks() {
		if (btnFilterDrinks == null) {
			btnFilterDrinks = new JButton("Drinks");
			btnFilterDrinks.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mc.getMenuProducts(TypeOfProduct.Drink)));
				}
			});
			btnFilterDrinks.setMnemonic('D');
		}
		return btnFilterDrinks;
	}

	private JButton getBtnFilterExtras() {
		if (btnFilterExtras == null) {
			btnFilterExtras = new JButton("Extras");
			btnFilterExtras.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mc.getMenuProducts(TypeOfProduct.Side)));
				}
			});
			btnFilterExtras.setMnemonic('E');
		}
		return btnFilterExtras;
	}

	private JButton getBtnFilterDesserts() {
		if (btnFilterDesserts == null) {
			btnFilterDesserts = new JButton("Desserts");
			btnFilterDesserts.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cbProducts.setModel(new DefaultComboBoxModel<Product>(mc.getMenuProducts(TypeOfProduct.Dessert)));
				}
			});
			btnFilterDesserts.setMnemonic('D');
		}
		return btnFilterDesserts;
	}
}
