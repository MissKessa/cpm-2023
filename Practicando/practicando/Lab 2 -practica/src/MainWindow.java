
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MainWindow extends JFrame {
	private JButton btnCancel;
	private JButton btnNext;
	private JPanel pnCustomerInformation;
	private JPanel pnOrder;
	private JRadioButton rdbtnOnSite;
	private JRadioButton rdbtnTakeAway;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPassword;
	private JPasswordField psswPassword;
	private JPasswordField psswRepeated;
	private JLabel lblRepeated;
	private JLabel lblBirthdate;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Customer information");
		setBounds(100, 100, 569, 374);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnCancel());
		getContentPane().add(getBtnNext());
		getContentPane().add(getPnCustomerInformation());
		getContentPane().add(getPnOrder());
		this.getRootPane().setDefaultButton(getBtnNext());

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
			btnCancel.setBounds(441, 306, 85, 21);
		}
		return btnCancel;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setBounds(337, 306, 85, 21);
		}
		return btnNext;
	}

	private JPanel getPnCustomerInformation() {
		if (pnCustomerInformation == null) {
			pnCustomerInformation = new JPanel();
			pnCustomerInformation.setBorder(
					new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCustomerInformation.setBounds(23, 22, 507, 190);
			pnCustomerInformation.setLayout(null);
			pnCustomerInformation.add(getLblName());
			pnCustomerInformation.add(getTxtName());
			pnCustomerInformation.add(getLblPassword());
			pnCustomerInformation.add(getPsswPassword());
			pnCustomerInformation.add(getPsswRepeated());
			pnCustomerInformation.add(getLblRepeated());
			pnCustomerInformation.add(getLblBirthdate());
			pnCustomerInformation.add(getComboBox());
		}
		return pnCustomerInformation;
	}

	private JPanel getPnOrder() {
		if (pnOrder == null) {
			pnOrder = new JPanel();
			pnOrder.setBorder(new TitledBorder(null, "Order", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnOrder.setBounds(23, 267, 259, 60);
			pnOrder.setLayout(null);
			pnOrder.add(getRdbtnOnSite());
			pnOrder.add(getRdbtnTakeAway());
		}
		return pnOrder;
	}

	private JRadioButton getRdbtnOnSite() {
		if (rdbtnOnSite == null) {
			rdbtnOnSite = new JRadioButton("On site");
			rdbtnOnSite.setSelected(true);
			buttonGroup.add(rdbtnOnSite);
			rdbtnOnSite.setMnemonic('s');
			rdbtnOnSite.setBounds(17, 19, 103, 21);
		}
		return rdbtnOnSite;
	}

	private JRadioButton getRdbtnTakeAway() {
		if (rdbtnTakeAway == null) {
			rdbtnTakeAway = new JRadioButton("Take away");
			rdbtnTakeAway.setMnemonic('T');
			buttonGroup.add(rdbtnTakeAway);
			rdbtnTakeAway.setBounds(124, 19, 103, 21);
		}
		return rdbtnTakeAway;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name and surname:");
			lblName.setLabelFor(getTxtName());
			lblName.setDisplayedMnemonic('N');
			lblName.setBounds(10, 25, 116, 21);
		}
		return lblName;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.addFocusListener(new EmptyFocusListener());
			txtName.setBounds(159, 26, 175, 21);
			txtName.setColumns(10);
		}
		return txtName;
	}

	private class EmptyFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			JTextField field = (JTextField) e.getSource();
			if (field.getText().isEmpty() || field.getText().isBlank()) {
				JOptionPane.showMessageDialog(field, "The fields must not be empty",
						"Customer information: Empty field", JOptionPane.WARNING_MESSAGE);
				field.grabFocus();
			}
		}
	}

	private class EqualFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			if (!(String.valueOf(getPsswPassword().getPassword())
					.equals(String.valueOf(getPsswRepeated().getPassword())))) {
				JOptionPane.showMessageDialog(null, "The password must be the same",
						"Customer information: Not equal password", JOptionPane.WARNING_MESSAGE);
				getPsswPassword().grabFocus();
			}
		}
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setLabelFor(getPsswPassword());
			lblPassword.setDisplayedMnemonic('P');
			lblPassword.setBounds(10, 117, 116, 21);
		}
		return lblPassword;
	}

	private JPasswordField getPsswPassword() {
		if (psswPassword == null) {
			psswPassword = new JPasswordField();
			psswPassword.setBounds(159, 118, 175, 20);
			psswPassword.addFocusListener(new EmptyFocusListener());
		}
		return psswPassword;
	}

	private JPasswordField getPsswRepeated() {
		if (psswRepeated == null) {
			psswRepeated = new JPasswordField();
			psswRepeated.setBounds(159, 149, 175, 20);
			psswRepeated.addFocusListener(new EmptyFocusListener());
			psswRepeated.addFocusListener(new EqualFocusListener());
		}
		return psswRepeated;
	}

	private JLabel getLblRepeated() {
		if (lblRepeated == null) {
			lblRepeated = new JLabel("Repeated password:");
			lblRepeated.setLabelFor(getPsswRepeated());
			lblRepeated.setDisplayedMnemonic('P');
			lblRepeated.setBounds(10, 148, 116, 21);
		}
		return lblRepeated;
	}

	private JLabel getLblBirthdate() {
		if (lblBirthdate == null) {
			lblBirthdate = new JLabel("Birthdate:");
			lblBirthdate.setDisplayedMnemonic('B');
			lblBirthdate.setBounds(10, 67, 72, 29);
		}
		return lblBirthdate;
	}

	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			String[] years = new String[100];
			for (int i = 0; i < years.length; i++) {
				years[i] = "" + (i + 1900);
			}
			comboBox.setModel(new DefaultComboBoxModel(years));
			comboBox.setBounds(159, 71, 175, 25);
		}
		return comboBox;
	}
}
