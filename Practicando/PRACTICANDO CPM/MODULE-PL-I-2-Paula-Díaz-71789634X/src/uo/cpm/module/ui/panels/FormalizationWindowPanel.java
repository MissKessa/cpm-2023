package uo.cpm.module.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uo.cpm.module.ui.MainWindow;
import uo.cpm.module.util.NotSemiColonKeyListener;

/**
 * It's the panel that the user fills to finally formalize the reservation
 * (name, full name, email and comments)
 * 
 * @author paula
 *
 */
public class FormalizationWindowPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;

	/**
	 * Panel that contains the buttons formalize and cancel
	 */
	private JPanel pnButtons;
	private JButton btnFormalize;
	private JButton btnCancel;

	/**
	 * Panel that contains all the information necessary to formalize: name,
	 * surname, id, email and comments
	 */
	private JPanel pnInfo;

	/**
	 * Panel that contains the name and surname
	 */
	private JPanel pnFullName;
	private JPanel pnName;
	private JLabel lblName;
	private JTextField txtName;
	private JPanel pnSurname;
	private JLabel lblSurname;
	private JTextField txtSurname;

	/**
	 * Panel that contains the id, email and comments
	 */
	private JPanel pnCenter;

	private JPanel pnId;
	private JLabel lblId;
	private JTextField txtId;

	/**
	 * Panel that contains the email and comments
	 */
	private JPanel pnExtra;

	private JPanel pnEmail;
	private JLabel lblEmail;
	private JTextField txtEmail;

	private JPanel pnComments;
	private JLabel lblComments;
	private JScrollPane scrPnComments;
	private JTextArea txtComments;

	/**
	 * Mouse listener that checks the form its filled
	 */
	private CheckItsFilledMouseListener mL = new CheckItsFilledMouseListener();
	/**
	 * Focus listener that checks the form its filled
	 */
	private CheckItsFilledFocusListener fL = new CheckItsFilledFocusListener();
	/**
	 * Key listener that only allows letters and white spaces
	 */
	private OnlyLettersAndSpacesKeyListener kL = new OnlyLettersAndSpacesKeyListener();

	/**
	 * It creates the panel
	 * 
	 * @param mw is the reference to the main window
	 */
	public FormalizationWindowPanel(MainWindow mw) {
		this.mw = mw;

		setLayout(new BorderLayout(0, 5));
		setBackground(MainWindow.COLOR_BACKGROUND);

		add(getPnButtons(), BorderLayout.SOUTH);
		add(getPnInfo(), BorderLayout.CENTER);
	}

	/**
	 * It cleans all the text field, sets the btnFormalize disabled and as default
	 * button. It also localizes the panel
	 */
	public void setInformation() {
		txtId.setText(mw.getService().getID());
		txtName.setText("");
		txtSurname.setText("");
		txtEmail.setText("");
		txtComments.setText("");
		btnFormalize.setEnabled(false);
		btnFormalize.setBackground(Color.GRAY);

		getRootPane().setDefaultButton(btnFormalize);

		localize();

	}

	/**
	 * It localizes the panel according to the locale chosen in the main window
	 */
	private void localize() {
		ResourceBundle texts = mw.getTexts();

		btnFormalize.setText(texts.getString("btnFormalize.text"));
		btnFormalize.setToolTipText(texts.getString("btnFormalize.toolTip"));

		btnCancel.setText(texts.getString("btnCancelFormalization.text"));
		btnCancel.setToolTipText(texts.getString("btnCancelFormalization.toolTip"));
//		btnCancel.setMnemonic(texts.getString("btnCancelFormalization.mnemonic").charAt(0));

		lblName.setText(texts.getString("lblName.text"));
		txtName.setToolTipText(texts.getString("txtName.toolTip"));
		lblName.setDisplayedMnemonic(texts.getString("lblName.mnemonic").charAt(0));

		lblSurname.setText(texts.getString("lblSurname.text"));
		txtSurname.setToolTipText(texts.getString("txtSurname.toolTip"));
		lblSurname.setDisplayedMnemonic(texts.getString("lblSurname.mnemonic").charAt(0));

		lblId.setText(texts.getString("lblId.text"));
		txtId.setToolTipText(texts.getString("txtId.toolTip"));

		lblEmail.setText(texts.getString("lblEmail.text"));
		txtEmail.setToolTipText(texts.getString("txtEmail.toolTip"));
		lblEmail.setDisplayedMnemonic(texts.getString("lblEmail.mnemonic").charAt(0));

		lblComments.setText(texts.getString("lblComments.text"));
		txtComments.setToolTipText(texts.getString("txtComments.toolTip"));
		lblComments.setDisplayedMnemonic(texts.getString("lblComments.mnemonic").charAt(0));
	}

	/**
	 * It updates the reservation, saves it, removes the discount and shows the next
	 * panel (pnDone)
	 */
	private void shownNextPanel() {
		updateReservation();
		mw.getService().saveReservation();
		mw.getService().removeDiscount();
		mw.showPnDone();
	}

	/**
	 * It updates the reservation by setting the full name, the email and the
	 * comments
	 */
	private void updateReservation() {
		mw.getService().setFullName((txtName.getText()), (txtSurname.getText()));
		mw.getService().setEmail((txtEmail.getText()).strip());
		mw.getService().setComments(txtComments.getText().strip());
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnButtons.add(getBtnCancel());

			pnButtons.add(getBtnFormalize());
		}
		return pnButtons;
	}

	private JButton getBtnFormalize() {
		if (btnFormalize == null) {
			btnFormalize = new JButton();
			btnFormalize.setForeground(new Color(255, 255, 255));
			btnFormalize.setEnabled(false);

			btnFormalize.addMouseListener(mL);
			btnFormalize.addFocusListener(fL);
			btnFormalize.addActionListener(new ActionListener() { // shows the next panel
				@Override
				public void actionPerformed(ActionEvent e) {
					shownNextPanel();
				}
			});
			btnFormalize.setFont(new Font("Arial", Font.BOLD, 12));
			btnFormalize.setBackground(Color.GRAY);

		}
		return btnFormalize;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton();
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // shows the panel before
					mw.showPnFormalizeWarning();
				}
			});
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.setFont(new Font("Arial", Font.BOLD, 12));
//			btnCancel.setMnemonic('C');
//			btnCancel.setToolTipText("Cancel");
			btnCancel.setBackground(MainWindow.COLOR_RED);
		}
		return btnCancel;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setLayout(new BorderLayout(10, 10));
			pnInfo.setBackground(MainWindow.COLOR_BACKGROUND);

			pnInfo.add(getPnFullName(), BorderLayout.NORTH);
			pnInfo.add(getPnCenter(), BorderLayout.CENTER);
		}
		return pnInfo;
	}

	private JPanel getPnFullName() {
		if (pnFullName == null) {
			pnFullName = new JPanel();
			pnFullName.setLayout(new GridLayout(1, 1, 10, 0));
			pnFullName.setBackground(MainWindow.COLOR_BACKGROUND);

			pnFullName.add(getPnName());
			pnFullName.add(getPnSurname());
		}
		return pnFullName;
	}

	private JPanel getPnName() {
		if (pnName == null) {
			pnName = new JPanel();
			pnName.setLayout(new BorderLayout(5, 0));
			pnName.setBackground(MainWindow.COLOR_BACKGROUND);

			pnName.add(getLblName(), BorderLayout.WEST);
			pnName.add(getTxtName(), BorderLayout.CENTER);
		}
		return pnName;
	}

	private JPanel getPnSurname() {
		if (pnSurname == null) {
			pnSurname = new JPanel();
			pnSurname.setLayout(new BorderLayout(5, 0));
			pnSurname.setBackground(MainWindow.COLOR_BACKGROUND);

			pnSurname.add(getLblSurname(), BorderLayout.WEST);
			pnSurname.add(getTxtSurname(), BorderLayout.CENTER);
		}
		return pnSurname;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel();
			lblName.setLabelFor(getTxtName());
			lblName.setFont(new Font("Arial", Font.BOLD, 12));
			lblName.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblName;
	}

	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel();
			lblSurname.setLabelFor(getTxtSurname());
			lblSurname.setFont(new Font("Arial", Font.BOLD, 12));
			lblSurname.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblSurname;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Arial", Font.PLAIN, 12));
			txtName.setColumns(10);
			txtName.setBackground(MainWindow.COLOR_BACKGROUND);

			txtName.addKeyListener(kL);
			txtName.addMouseListener(mL);
			txtName.addFocusListener(fL);
		}
		return txtName;
	}

	private JTextField getTxtSurname() {
		if (txtSurname == null) {
			txtSurname = new JTextField();
			txtSurname.setFont(new Font("Arial", Font.PLAIN, 12));
			txtSurname.setColumns(10);
			txtSurname.setBackground(MainWindow.COLOR_BACKGROUND);

			txtSurname.addKeyListener(kL);
			txtSurname.addMouseListener(mL);
			txtSurname.addFocusListener(fL);
		}
		return txtSurname;

	}

	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setLayout(new BorderLayout(10, 10));
			pnCenter.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCenter.add(getPnId(), BorderLayout.NORTH);
			pnCenter.add(getPnExtra(), BorderLayout.CENTER);
		}
		return pnCenter;
	}

	private JPanel getPnId() {
		if (pnId == null) {
			pnId = new JPanel();
			pnId.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
			pnId.setBackground(MainWindow.COLOR_BACKGROUND);

			pnId.add(getLblId());
			pnId.add(getTxtId());
		}
		return pnId;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel();
			lblId.setHorizontalAlignment(SwingConstants.LEFT);
			lblId.setLabelFor(getTxtId());
			lblId.setFont(new Font("Arial", Font.BOLD, 12));
			lblId.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblId;
	}

	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setFont(new Font("Arial", Font.PLAIN, 12));
			txtId.setEditable(false);
			txtId.setColumns(10);
			txtId.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtId;
	}

	private JPanel getPnExtra() {
		if (pnExtra == null) {
			pnExtra = new JPanel();
			pnExtra.setLayout(new BorderLayout(0, 0));
			pnExtra.setBackground(MainWindow.COLOR_BACKGROUND);

			pnExtra.add(getPnEmail(), BorderLayout.NORTH);
			pnExtra.add(getPnComments());
		}
		return pnExtra;
	}

	private JPanel getPnEmail() {
		if (pnEmail == null) {
			pnEmail = new JPanel();
			pnEmail.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
			pnEmail.setBackground(MainWindow.COLOR_BACKGROUND);

			pnEmail.add(getLblEmail());
			pnEmail.add(getTxtEmail());
		}
		return pnEmail;
	}

	private JPanel getPnComments() {
		if (pnComments == null) {
			pnComments = new JPanel();
			pnComments.setLayout(new BorderLayout(5, 0));
			pnComments.setBackground(MainWindow.COLOR_BACKGROUND);

			pnComments.add(getLblComments(), BorderLayout.NORTH);
			pnComments.add(getScrPnComments(), BorderLayout.CENTER);
		}
		return pnComments;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel();
			lblEmail.setLabelFor(getTxtEmail());
			lblEmail.setFont(new Font("Arial", Font.BOLD, 12));
			lblEmail.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblEmail;
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Arial", Font.PLAIN, 12));
			txtEmail.setColumns(10);
			txtEmail.setBackground(MainWindow.COLOR_BACKGROUND);

			txtEmail.addKeyListener(new NotSemiColonKeyListener());
			txtEmail.addMouseListener(mL);
			txtEmail.addFocusListener(fL);
		}
		return txtEmail;
	}

	private JLabel getLblComments() {
		if (lblComments == null) {
			lblComments = new JLabel();
			lblComments.setLabelFor(getTxtComments());
			lblComments.setFont(new Font("Arial", Font.BOLD, 12));
			lblComments.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblComments;
	}

	private JScrollPane getScrPnComments() {
		if (scrPnComments == null) {
			scrPnComments = new JScrollPane();
			scrPnComments.setViewportView(getTxtComments());
			scrPnComments.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return scrPnComments;
	}

	private JTextArea getTxtComments() {
		if (txtComments == null) {
			txtComments = new JTextArea();
			txtComments.setFont(new Font("Arial", Font.PLAIN, 12));
			txtComments.setBackground(MainWindow.COLOR_BACKGROUND);

			txtComments.addKeyListener(new NotSemiColonKeyListener());
		}
		return txtComments;
	}

	/**
	 * Checks that the form is filled to enabled the btnFormalize: the name and
	 * surname are not blank, and that the email is correct
	 */
	private void checkItsFilled() {
		if (!(txtName.getText().isBlank() || txtName.getText().isEmpty())
				&& !(txtSurname.getText().isBlank() || txtSurname.getText().isEmpty()) && checkEmail()) {
			btnFormalize.setEnabled(true);
			btnFormalize.setBackground(MainWindow.COLOR_GREEN);

		} else {
			btnFormalize.setEnabled(false);
			btnFormalize.setBackground(Color.GRAY);
		}
	}

	/**
	 * It checks if the email is correct: it's not blank and has the form
	 * something@something.something
	 * 
	 * @return true if the email is correct
	 */
	private boolean checkEmail() {
		String email = txtEmail.getText().strip();
		if (email.isBlank() || email.isEmpty())
			return false;

		String[] substring = email.split("@");
		if (substring.length == 1)
			return false;

//		String[] domain = substring[1].split(".");
//		if (domain.length <= 1)
//			return false;
//		if (domain[0].length() == 0 || domain[1].length() == 0)
//			return false;
//		if (!substring[1].contains("."))
//			return false;

		if (!substring[1].contains("."))
			return false;
		if (substring[1].length() < 3)
			return false;
		return true;
	}

	/**
	 * Mouse listener that checks if the form is filled
	 * 
	 * @author paula
	 *
	 */
	private class CheckItsFilledMouseListener extends MouseAdapter {
		@Override
		public void mouseExited(MouseEvent e) {
			checkItsFilled();
		}
	}

	/**
	 * Focus listener that checks if the form is filled
	 * 
	 * @author paula
	 *
	 */
	private class CheckItsFilledFocusListener extends FocusAdapter {
		@Override
		public void focusLost(FocusEvent e) {
			checkItsFilled();
		}
	}

	/**
	 * Key listener that only allows to write letters and white spacs
	 * 
	 * @author paula
	 *
	 */
	private class OnlyLettersAndSpacesKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(KeyEvent e) {
			char key = e.getKeyChar();
			if (Character.isAlphabetic(key) || key == KeyEvent.VK_SPACE) {

			} else {
				e.consume();
			}
		}
	}
}
