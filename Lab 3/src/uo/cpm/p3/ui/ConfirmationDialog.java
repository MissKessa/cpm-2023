package uo.cpm.p3.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * This class builds the window with a confirmation dialog of an McDonalds
 * application with:
 * <p>
 * -labels for the image of the check, and the message that the order is being
 * processed
 * <p>
 * -a text field for the code, with its corresponding label
 * <p>
 * -a finish button that ends the application
 * <p>
 * -a label and text field for the final price
 * 
 * @author paula
 *
 */
public class ConfirmationDialog extends JDialog {
	/**
	 * It's the label for the code text field
	 */
	private JLabel lblOrderCode;
	/**
	 * It's the text field that shows the order code
	 */
	private JTextField txtCode;
	/**
	 * It's the button that finish the application
	 */
	private JButton btnFinish;
	/**
	 * It's the label that shows the Ok image
	 */
	private JLabel lblOk;
	/**
	 * It's the label that shows the message that the order is being processed
	 */
	private JLabel lblMessage;
	/**
	 * It's a reference to the parent window (registry form)
	 */
	private RegistryForm rF = null;
	private JLabel lblPrice;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ConfirmationDialog dialog = new ConfirmationDialog();
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the dialog. Sets the title, icon, and adds the button, labels, and
	 * text fields. When the window is closed, only this window is closed
	 * 
	 * @param rF is the reference to the parent window (registry form)
	 */
	public ConfirmationDialog(RegistryForm rF) {
		this.rF = rF;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/logo.PNG")));
		setTitle("McDonald's: Order Confirmation");
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		getContentPane().add(getLblOrderCode());
		getContentPane().add(getTxtCode());
		getContentPane().add(getBtnFinish());
		getContentPane().add(getLblOk());
		getContentPane().add(getLblMessage());

		getTxtCode().setText(rF.getMainWindow().getMcDonalds().getOrderCode());
		getContentPane().add(getLblPrice());
		getContentPane().add(getTxtPrice());

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

	}

	/**
	 * Sets the label for the text field of the code
	 * 
	 * @return the label for the text field of the code
	 */
	private JLabel getLblOrderCode() {
		if (lblOrderCode == null) {
			lblOrderCode = new JLabel("The order code is: ");
			lblOrderCode.setBounds(81, 146, 124, 14);
		}
		return lblOrderCode;
	}

	/**
	 * Sets the font for the text field that shows the order code, and to not
	 * editable
	 * 
	 * @return the text field that shows the order code
	 */
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setFont(new Font("Tahoma", Font.BOLD, 11));
			txtCode.setEditable(false);
			txtCode.setBounds(239, 143, 86, 20);
			txtCode.setColumns(10);
		}
		return txtCode;
	}

	/**
	 * Sets the text, foreground, background and action when you click on it (the
	 * program ends) for the finish button
	 * 
	 * @return the finish button
	 */
	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.setForeground(new Color(255, 255, 255));
			btnFinish.setBackground(new Color(0, 128, 0));
			btnFinish.setBounds(316, 227, 89, 23);
			btnFinish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rF.getMainWindow().getMcDonalds().saveOrder();
					System.exit(0); // to end the app if you click on cancel
				}
			});
		}
		return btnFinish;
	}

	/**
	 * Sets the image for the Ok label
	 * 
	 * @return hte Ok label
	 */
	private JLabel getLblOk() {
		if (lblOk == null) {
			lblOk = new JLabel("Ok");
			lblOk.setIcon(new ImageIcon(ConfirmationDialog.class.getResource("/uo/cpm/p3/ui/img/ok.png")));
			lblOk.setBounds(10, 72, 46, 35);
		}
		return lblOk;
	}

	/**
	 * Sets the name and font of the message label
	 * 
	 * @return the message label
	 */
	private JLabel getLblMessage() {
		if (lblMessage == null) {
			lblMessage = new JLabel("We are processing your order");
			lblMessage.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblMessage.setBounds(81, 72, 308, 45);
		}
		return lblMessage;
	}

	/**
	 * 
	 * @return the label for the price
	 */
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("Price: ");
			lblPrice.setBounds(81, 185, 46, 14);
		}
		return lblPrice;
	}

	/**
	 * 
	 * @return the text field for the price
	 */
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.setEditable(false);
			txtPrice.setText(String.format("%.2f", rF.getMainWindow().getMcDonalds().getOrderTotal()) + " \u20AC");
			txtPrice.setBounds(239, 183, 86, 19);
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
}
