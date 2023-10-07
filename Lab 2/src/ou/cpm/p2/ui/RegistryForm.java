package ou.cpm.p2.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistryForm extends JFrame {
	private JButton btnNext;
	private JButton btnCancel;
	private JPanel pnCustomerData;
	private JPanel pnOrder;
	private JLabel lblNameAndSurname;
	private JTextField txtNameAndSurname;
	private JComboBox cdBirthYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() { //splitting the thread
			public void run() {
				try {
					RegistryForm frame = new RegistryForm();
					frame.setLocationRelativeTo(null);
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
	public RegistryForm() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnNext());
		getContentPane().add(getBtnCancel());
		getContentPane().add(getPnCustomerData());
		getContentPane().add(getPnOrder());
		setTitle("Customer Information");
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //telling what the application when clicking the x. Before we only hide it, and as it was the main window the the program stops

	}
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setForeground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNext.setBackground(new Color(50, 205, 50));
			btnNext.setBounds(355, 313, 89, 23);
		}
		return btnNext;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setBackground(new Color(220, 20, 60));
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0); //to end the app if you click on cancel
				}
			});
			btnCancel.setBounds(455, 313, 89, 23);
		}
		return btnCancel;
	}
	private JPanel getPnCustomerData() {
		if (pnCustomerData == null) {
			pnCustomerData = new JPanel();
			pnCustomerData.setBorder(new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCustomerData.setBackground(new Color(255, 255, 255));
			pnCustomerData.setBounds(23, 11, 539, 175);
			pnCustomerData.setLayout(null);
			pnCustomerData.add(getLblNameAndSurname());
			pnCustomerData.add(getTxtNameAndSurname());
			pnCustomerData.add(getCdBirthYear());
		}
		return pnCustomerData;
	}
	private JPanel getPnOrder() {
		if (pnOrder == null) {
			pnOrder = new JPanel();
			pnOrder.setBounds(23, 255, 277, 79);
		}
		return pnOrder;
	}
	private JLabel getLblNameAndSurname() {
		if (lblNameAndSurname == null) {
			lblNameAndSurname = new JLabel("Name and Surname:");
			lblNameAndSurname.setBounds(21, 26, 146, 14);
		}
		return lblNameAndSurname;
	}
	private JTextField getTxtNameAndSurname() {
		if (txtNameAndSurname == null) {
			txtNameAndSurname = new JTextField();
			txtNameAndSurname.setBounds(182, 23, 231, 20);
			txtNameAndSurname.setColumns(10);
		}
		return txtNameAndSurname;
	}
	private JComboBox getCdBirthYear() {
		if (cdBirthYear == null) {
			cdBirthYear = new JComboBox();
			cdBirthYear.setModel(new DefaultComboBoxModel(new String[] {"1923", "1924"}));
			cdBirthYear.setBounds(182, 54, 78, 22);
		}
		return cdBirthYear;
	}
}
