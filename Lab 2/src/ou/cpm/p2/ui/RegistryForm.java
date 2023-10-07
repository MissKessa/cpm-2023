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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;

/**
 * This class builds an application with a user register form with:
 * -2 buttons: Cancel (terminates the application) and Next (checks that the text fields are not empty and that both
 *  password fields have the same content).
 * -a JPanel for the customer information containing:
 *   -a name and surname text field, with a label indicating it.
 *   -a combo box to select the birth year between MIN_BIRTHYEAR and MAX_BIRTHYEAR, with a label indicating it.
 *   -2 password fields, with a label indicating it.
 * -another JPanel for the order information containing: 2 radio buttons (on site, and take away). When one is activated, the other
 *  is deactivated.
 * 
 * @author Paula
 *
 */
public class RegistryForm extends JFrame {
	private static final long serialVersionUID = 1L;
	/**
	 * It's the Next button that checks that the text fields are not empty and that both password fields have the same content
	 */
	private JButton btnNext;
	/**
	 * It's the Cancel button that when it's click closes the window
	 */
	private JButton btnCancel;
	/**
	 * It's the JPanel for the customer information
	 */
	private JPanel pnCustomerData;
	/**
	 * It's the JPanel for the order information
	 */
	private JPanel pnOrder;
	/**
	 * It's the label for the Name and Surname text field
	 */
	private JLabel lblNameAndSurname;
	/**
	 * It's the text field for writing the name and surname
	 */
	private JTextField txtNameAndSurname;
	/**
	 * It's a combo box that shows a list of options from the MIN_BIRTHYEAR to the MAX_BIRTHYEAR
	 */
	private JComboBox cdBirthYear;
	/**
	 * It's the label for the birthyear combo box
	 */
	private JLabel lblBirthdate;
	/**
	 * It's the label for the password field
	 */
	private JLabel lblPassword;
	/**
	 * It's the label for the repeat password field
	 */
	private JLabel lblRepeatPassword;
	/**
	 * It's a radio button for selecting to take away your order
	 */
	private JRadioButton rdbtnTakeAway;
	/**
	 * It's a radio button for selecting to on site your order
	 */
	private JRadioButton rdbtnOnSite;
	
	/**
	 * It's the minimum birth year shown in the birth year combo box
	 */
	private static final int MIN_BIRTHYEAR=1923;
	/**
	 * It's the maximum birth year shown in the birth year combo box
	 */
	private static final int MAX_BIRTHYEAR=2023;
	/**
	 * It's a password field for the password
	 */
	private JPasswordField psswPassword;
	/**
	 * It's a password field for the repeat password
	 */
	private JPasswordField psswRepeatPassword;

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
	 * Create the frame with the Next and Cancel button, and the 2 panels. It sets the title of the window.
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
	
	/**
	 * Returns the Next Button with its color, bounds font, and action (checks that the text fields are not empty and that both
	 * password fields have the same content)
	 * @return the Next Button
	 */
	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setForeground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnNext.setBackground(new Color(50, 205, 50));
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkEverythingIsOK();
				}
			});
			btnNext.setBounds(356, 291, 89, 23);
		}
		return btnNext;
	}
	
	/**
	 * Checks that the text fields are not empty and that the password and the repeated one are equal.
	 * If not it shows a message
	 */
	private void checkEverythingIsOK() {
		checkTextFieldsAreNotEmpty();
		checkPasswordIsCorrect();
	}
	
	/**
	 * Checks that the password and the repeated one are equal, if not shows a message
	 */
	private void checkPasswordIsCorrect() {
		if (!passwordIsEqual()) {
			JOptionPane.showMessageDialog(null, "The password introduce doesn't match");
		}
	}

	/**
	 * Checks that the password and the repeated one are equal
	 * @return true if they are equal
	 */
	private boolean passwordIsEqual() {
		return String.valueOf(psswPassword.getPassword()).equals(String.valueOf(psswRepeatPassword.getPassword()));
	}

	/**
	 * Checks that the text fields are not empty, if not shows a message
	 */
	private void checkTextFieldsAreNotEmpty() {
		if (isTextFieldEmpty(txtNameAndSurname)||isTextFieldEmpty(psswPassword)|| isTextFieldEmpty(psswRepeatPassword))
			JOptionPane.showMessageDialog(null, "The fields cannot by empty");
	}
	
	/**
	 * Checks if a JTextField is empty
	 * @param textField is the field going to be checked
	 * @return true if it's empty
	 */
	private boolean isTextFieldEmpty(JTextField textField) {
		return textField.getText().isEmpty();
	}

	/**
	 * Returns the Cancel Button with its color, bounds, font, and action (closes the window)
	 * @return the Cancel Button
	 */
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
			btnCancel.setBounds(455, 291, 89, 23);
		}
		return btnCancel;
	}
	
	/**
	 * Returns the JPanel for the costumer data with its title, color, bounds, the labels for each field, and the fields for the name and 
	 * surname, birthYear, password and repeat password 
	 * @return the JPanel for the customer data
	 */
	private JPanel getPnCustomerData() {
		if (pnCustomerData == null) {
			pnCustomerData = new JPanel();
			pnCustomerData.setBorder(new TitledBorder(null, "Customer Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCustomerData.setBackground(new Color(255, 255, 255));
			pnCustomerData.setBounds(23, 41, 539, 175);
			pnCustomerData.setLayout(null);
			pnCustomerData.add(getLblNameAndSurname());
			pnCustomerData.add(getTxtNameAndSurname());
			pnCustomerData.add(getCdBirthYear());
			pnCustomerData.add(getLblBirthdate());
			pnCustomerData.add(getLblPassword());
			pnCustomerData.add(getLblRepeatPassword());
			pnCustomerData.add(getPsswPassword());
			pnCustomerData.add(getPsswRepeatPassword());
		}
		return pnCustomerData;
	}

	/**
	 * Returns the Repeat Password Field
	 * @return the Repeat Password Field
	 */
	private JPasswordField getPsswRepeatPassword() {
		psswRepeatPassword = new JPasswordField();
		psswRepeatPassword.setBounds(182, 132, 231, 19);
		return psswRepeatPassword;
	}

	/**
	 * Returns the Password Field
	 * @return the Password Field
	 */
	private JPasswordField getPsswPassword() {
		psswPassword = new JPasswordField();
		psswPassword.setBounds(182, 95, 231, 19);
		return psswPassword;	
	}
	/**
	 * Returns the JPanel for the order information  with its title, color, bounds, the labels for each button, and 
	 * the radio buttons for the On site and Take away
	 * @return the JPanel for the order information 
	 */
	private JPanel getPnOrder() {
		if (pnOrder == null) {
			pnOrder = new JPanel();
			pnOrder.setBackground(Color.WHITE);
			pnOrder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Order", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnOrder.setBounds(23, 240, 277, 79);
			pnOrder.setLayout(null);
			
			pnOrder.add(getRdbtnOnSite());
			pnOrder.add(getRdbtnTakeAway());
		}
		return pnOrder;
	}

	/**
	 * Returns the radio Button for the Take away with its color, bounds, font, and action (checks that if it's clicked the 
	 * radio button on site is deactivated)
	 
	 * @return the radio Button for the Take away
	 */
	private JRadioButton getRdbtnTakeAway() {
		rdbtnTakeAway= new JRadioButton("Take away");
		rdbtnTakeAway.setBackground(Color.WHITE);
		rdbtnTakeAway.setBounds(136, 33, 103, 21);
		rdbtnTakeAway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnOnSite.isSelected()) {
					rdbtnOnSite.setSelected(false);
				}
			}
		});
		return rdbtnTakeAway;
	}

	/**
	 * Returns the radio Button for the On site with its color, bounds, font, and action (checks that if it's clicked the 
	 * radio button Take away is deactivated)
	 
	 * @return the radio Button for the On site
	 */
	private JRadioButton getRdbtnOnSite() {
		rdbtnOnSite = new JRadioButton("On site");
		rdbtnOnSite.setBackground(Color.WHITE);
		rdbtnOnSite.setBounds(31, 33, 81, 21);
		rdbtnOnSite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnTakeAway.isSelected()) {
					rdbtnTakeAway.setSelected(false);
				}
			}
		});
		return rdbtnOnSite;
	}
	/**
	 * Returns the label for the Name and Surname field
	 * @return the label for the Name and Surname field
	 */
	private JLabel getLblNameAndSurname() {
		if (lblNameAndSurname == null) {
			lblNameAndSurname = new JLabel("Name and Surname:");
			lblNameAndSurname.setBounds(21, 26, 146, 14);
		}
		return lblNameAndSurname;
	}
	
	/**
	 * Returns the Name and Surname text field
	 * @return the Name and Surname text field
	 */
	private JTextField getTxtNameAndSurname() {
		if (txtNameAndSurname == null) {
			txtNameAndSurname = new JTextField();
			txtNameAndSurname.setBounds(182, 23, 231, 20);
			txtNameAndSurname.setColumns(10);
		}
		return txtNameAndSurname;
	}
	
	/**
	 * Returns the combo box for the birth year
	 * @return the combo box for the birth year
	 */
	private JComboBox getCdBirthYear() {
		if (cdBirthYear == null) {
			cdBirthYear = new JComboBox();
			cdBirthYear.setModel(new DefaultComboBoxModel(setBirthOptions()));
			cdBirthYear.setBounds(182, 54, 78, 22);
		}
		return cdBirthYear;
	}
	
	/**
	 * Sets the range of years shown in the combo box for the birth year. It goes from MIN_BIRTHYEAR to MAX_BIRTHYEAR
	 * @return a array of Strings containing all the years between MIN_BIRTHYEAR and MAX_BIRTHYEAR
	 */
	private String[] setBirthOptions() {
		String[] listOfYears = new String[MAX_BIRTHYEAR - MIN_BIRTHYEAR + 1];
		for (int i=MIN_BIRTHYEAR, j=0; i<=MAX_BIRTHYEAR; i++, j++) {
			listOfYears[j]=i+"";
		}
		return listOfYears;
		
	}
	
	/**
	 * Returns the label for the birth year field
	 * @return the label for the birth year field
	 */
	private JLabel getLblBirthdate() {
		if (lblBirthdate == null) {
			lblBirthdate = new JLabel("Birthdate: ");
			lblBirthdate.setBounds(21, 59, 146, 14);
		}
		return lblBirthdate;
	}
	/**
	 * Returns the label for the password field
	 * @return the label for the password field
	 */
	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds(21, 97, 146, 14);
		}
		return lblPassword;
	}
	/**
	 * Returns the label for the repeat password field
	 * @return the label for the repeat password field
	 */
	private JLabel getLblRepeatPassword() {
		if (lblRepeatPassword == null) {
			lblRepeatPassword = new JLabel("Repeat password:");
			lblRepeatPassword.setBounds(21, 134, 146, 14);
		}
		return lblRepeatPassword;
	}
}
