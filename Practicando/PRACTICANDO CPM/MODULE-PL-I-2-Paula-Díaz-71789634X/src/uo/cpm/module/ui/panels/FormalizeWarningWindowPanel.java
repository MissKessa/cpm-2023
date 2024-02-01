package uo.cpm.module.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import uo.cpm.module.model.Castle;
import uo.cpm.module.model.Reservation;
import uo.cpm.module.ui.MainWindow;

/**
 * It's the panel shown when the user ends making the reservation. It notifies
 * that when the user formalizes, he won't be able to change the reservation
 * 
 * @author paula
 *
 */
public class FormalizeWarningWindowPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;

	/**
	 * Panel that contains all the relevant information about the reservation
	 */
	private JPanel pnSummary;

	/**
	 * Panel that contains the buttons and the warning message that it won't be able
	 * to change the reservation if he formalizes
	 */
	private JPanel pnWarningMessage;

	/**
	 * Panel containing the warning message
	 */
	private JPanel pnMessage;
	private JTextArea txtWarningMessage;

	/**
	 * Panel containing the buttons
	 */
	private JPanel pnButtons;
	private JButton btnYes;
	private JButton btnBack;

	/**
	 * Panel containing the castle name
	 */
	private JPanel pnCastleName;
	private JLabel lblCastleName;
	private JTextField txtCastleName;

	/**
	 * Panel containing the starting date of the reservation
	 */
	private JPanel pnStartingDate;
	private JLabel lblDate;
	private JTextField txtDate;

	/**
	 * Panel containing the number of people, rooms, days and final price
	 */
	private JPanel pnCenterInfo;

	/**
	 * Panel containing the number of people
	 */
	private JPanel pnNumberOfPeople;
	private JLabel lblNumberOfPeople;
	private JTextField txtNumberOfPeople;

	/**
	 * Panel containing the number of rooms
	 */
	private JPanel pnNumberOfRooms;
	private JLabel lblNumberOfRooms;
	private JTextField txtNumberOfRooms;

	/**
	 * Panel containing the number of days
	 */
	private JPanel pnNumberOfDays;
	private JTextField txtNumberOfDays;
	private JLabel lblNumberOfDays;

	/**
	 * Panel containing the final price
	 */
	private JPanel pnFinalPrice;
	private JLabel lblFinalPrice;
	private JTextField txtFinalPrice;

	/**
	 * Creates the panel
	 * 
	 * @param mw is a reference to the main window
	 */
	public FormalizeWarningWindowPanel(MainWindow mw) {
		this.mw = mw;

		setLayout(new BorderLayout(0, 0));
		setBackground(MainWindow.COLOR_BACKGROUND);

		add(getPnSummary(), BorderLayout.NORTH);
		add(getPnWarningMessage(), BorderLayout.CENTER);
	}

	/**
	 * Sets the information of the castle according to the castle chosen in the
	 * service, sets the btnYes as default one and localizes the panel
	 * 
	 * @param castle
	 */
	public void setInformationInCastleWindow(Castle castle) {
		txtCastleName.setText(castle.getName());
		txtNumberOfPeople.setText("" + mw.getService().getNumberOfPeople());
		txtNumberOfRooms.setText("" + mw.getService().getNumberOfRooms());
		txtNumberOfDays.setText("" + mw.getService().getNumberOfDays());
		txtFinalPrice.setText("" + mw.localizeMoney(mw.getService().getFinalPrice()));

		SimpleDateFormat formatter = new SimpleDateFormat(Reservation.STRING_DATE_FORMAT);
		DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG,
				new Locale(mw.getTexts().getString("locale")));

		Date date = null;
		try {
			date = formatter.parse(mw.getService().getDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}

//		LocalDate date = LocalDate.parse(mw.getService().getDate());
		txtDate.setText(formatDate.format(date).toString());

		getRootPane().setDefaultButton(btnYes);

		localize();

	}

	/**
	 * Localizes the panel according to the locale chosen in the main window
	 */
	private void localize() {
		ResourceBundle texts = mw.getTexts();

		pnSummary.setBorder(new TitledBorder(null, texts.getString("pnSummary.title"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		btnYes.setText(texts.getString("btnYes.text"));
		btnYes.setToolTipText(texts.getString("btnYes.toolTip"));

//		btnBack.setText(texts.getString("btnCancelWarning.text"));
//		btnBack.setToolTipText(texts.getString("btnCancelWarning.toolTip"));
//		btnCancel.setMnemonic(texts.getString("btnCancelWarning.mnemonic").charAt(0));
		btnBack.setText(texts.getString("btnBack.text"));
		btnBack.setToolTipText(texts.getString("btnBack.toolTip"));
		btnBack.setMnemonic(texts.getString("btnBack.mnemonic").charAt(0));

		lblCastleName.setText(texts.getString("lblCastleName.text"));
		txtCastleName.setToolTipText(texts.getString("txtCastleName.toolTip"));

		lblDate.setText(texts.getString("lblDate.text"));
		txtDate.setToolTipText(texts.getString("txtDate.toolTip"));
//		lblDate.setDisplayedMnemonic(texts.getString("lblDate.mnemonic").charAt(0));

		lblNumberOfPeople.setText(texts.getString("lblNumberOfPeople.text"));
		txtNumberOfPeople.setToolTipText(texts.getString("txtNumberOfPeople.toolTip"));
//		lblNumberOfPeople.setDisplayedMnemonic(texts.getString("lblNumberOfPeople.mnemonic").charAt(0));

		lblNumberOfRooms.setText(texts.getString("lblNumberOfRooms.text"));
		txtNumberOfRooms.setToolTipText(texts.getString("txtNumberOfRooms.toolTip"));
//		lblNumberOfRooms.setDisplayedMnemonic(texts.getString("lblNumberOfRooms.mnemonic").charAt(0));

		lblNumberOfDays.setText(texts.getString("lblNumberOfDays.text"));
		txtNumberOfDays.setToolTipText(texts.getString("txtNumberOfDays.toolTip"));
//		lblNumberOfDays.setDisplayedMnemonic(texts.getString("lblNumberOfDays.mnemonic").charAt(0));

		lblFinalPrice.setText(texts.getString("lblFinalPrice.text"));
		txtFinalPrice.setToolTipText(texts.getString("txtFinalPrice.toolTip"));

		txtWarningMessage.setText(texts.getString("txtWarningMessage.text"));
	}

	private JPanel getPnSummary() {
		if (pnSummary == null) {
			pnSummary = new JPanel();
			pnSummary.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnSummary.setBackground(MainWindow.COLOR_BACKGROUND);
			pnSummary.setLayout(new BorderLayout(10, 10));

			pnSummary.add(getPnCastleName(), BorderLayout.NORTH);
			pnSummary.add(getPnStartingDate(), BorderLayout.SOUTH);
			pnSummary.add(getPnCenterInfo(), BorderLayout.CENTER);

		}
		return pnSummary;
	}

	private JPanel getPnWarningMessage() {
		if (pnWarningMessage == null) {
			pnWarningMessage = new JPanel();
			pnWarningMessage.setLayout(new BorderLayout(0, 0));
			pnWarningMessage.setBackground(MainWindow.COLOR_BACKGROUND);

			pnWarningMessage.add(getPnButtons(), BorderLayout.SOUTH);
			pnWarningMessage.add(getPnMessage(), BorderLayout.CENTER);
		}
		return pnWarningMessage;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnButtons.add(getBtnBack());

			pnButtons.add(getBtnYes());
		}
		return pnButtons;
	}

	private JButton getBtnYes() {
		if (btnYes == null) {
			btnYes = new JButton();
			btnYes.setForeground(new Color(255, 255, 255));
			btnYes.setFont(new Font("Arial", Font.BOLD, 12));
			btnYes.setBackground(MainWindow.COLOR_GREEN);

			btnYes.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mw.showPnFormalization();
				}
			});
		}
		return btnYes;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton();
			btnBack.setForeground(new Color(255, 255, 255));
			btnBack.setFont(new Font("Arial", Font.BOLD, 12));
			btnBack.setBackground(MainWindow.COLOR_BUTTONS);
			btnBack.setForeground(MainWindow.COLOR_BUTTONS_LETTER);

			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					mw.showOldPnReservation();
					mw.showPnReservation();
				}
			});
//			btnCancel.setToolTipText("Cancel formalization");
//			btnCancel.setMnemonic('C');
		}
		return btnBack;
	}

	private JPanel getPnCastleName() {
		if (pnCastleName == null) {
			pnCastleName = new JPanel();
			pnCastleName.setLayout(new BorderLayout(5, 0));
			pnCastleName.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCastleName.add(getLblCastleName(), BorderLayout.WEST);
			pnCastleName.add(getTxtCastleName(), BorderLayout.CENTER);
		}
		return pnCastleName;
	}

	private JPanel getPnStartingDate() {
		if (pnStartingDate == null) {
			pnStartingDate = new JPanel();
			pnStartingDate.setLayout(new BorderLayout(5, 0));
			pnStartingDate.setBackground(MainWindow.COLOR_BACKGROUND);

			pnStartingDate.add(getLblDate(), BorderLayout.WEST);
			pnStartingDate.add(getTxtDate(), BorderLayout.CENTER);
		}
		return pnStartingDate;
	}

	private JPanel getPnCenterInfo() {
		if (pnCenterInfo == null) {
			pnCenterInfo = new JPanel();
			pnCenterInfo.setLayout(new GridLayout(2, 0, 5, 5));
			pnCenterInfo.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCenterInfo.add(getPnNumberOfPeople());
			pnCenterInfo.add(getPnNumberOfRooms());
			pnCenterInfo.add(getPnNumberOfDays());
			pnCenterInfo.add(getPnFinalPrice());
		}
		return pnCenterInfo;
	}

	private JLabel getLblCastleName() {
		if (lblCastleName == null) {
			lblCastleName = new JLabel();
			lblCastleName.setLabelFor(getTxtCastleName());
			lblCastleName.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastleName.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastleName;
	}

	private JTextField getTxtCastleName() {
		if (txtCastleName == null) {
			txtCastleName = new JTextField();
			txtCastleName.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleName.setBorder(null);
			txtCastleName.setEditable(false);
			txtCastleName.setColumns(10);
			txtCastleName.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtCastleName;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel();
			lblDate.setLabelFor(getTxtDate());
			lblDate.setFont(new Font("Arial", Font.BOLD, 12));
			lblDate.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblDate;
	}

	private JTextField getTxtDate() {
		if (txtDate == null) {
			txtDate = new JTextField();
			txtDate.setFont(new Font("Arial", Font.PLAIN, 12));
			txtDate.setBorder(null);
			txtDate.setEditable(false);
			txtDate.setColumns(10);
			txtDate.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtDate;
	}

	private JPanel getPnNumberOfPeople() {
		if (pnNumberOfPeople == null) {
			pnNumberOfPeople = new JPanel();
			pnNumberOfPeople.setLayout(new BorderLayout(5, 0));
			pnNumberOfPeople.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumberOfPeople.add(getLblNumberOfPeople(), BorderLayout.WEST);
			pnNumberOfPeople.add(getTxtNumberOfPeople(), BorderLayout.CENTER);
		}
		return pnNumberOfPeople;
	}

	private JPanel getPnNumberOfRooms() {
		if (pnNumberOfRooms == null) {
			pnNumberOfRooms = new JPanel();
			pnNumberOfRooms.setLayout(new BorderLayout(5, 0));
			pnNumberOfRooms.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumberOfRooms.add(getLblNumberOfRooms(), BorderLayout.WEST);
			pnNumberOfRooms.add(getTxtNumberOfRooms(), BorderLayout.CENTER);
		}
		return pnNumberOfRooms;
	}

	private JPanel getPnNumberOfDays() {
		if (pnNumberOfDays == null) {
			pnNumberOfDays = new JPanel();
			pnNumberOfDays.setLayout(new BorderLayout(5, 0));
			pnNumberOfDays.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumberOfDays.add(getTxtNumberOfDays(), BorderLayout.CENTER);
			pnNumberOfDays.add(getLblNumberOfDays(), BorderLayout.WEST);
		}
		return pnNumberOfDays;
	}

	private JPanel getPnFinalPrice() {
		if (pnFinalPrice == null) {
			pnFinalPrice = new JPanel();
			pnFinalPrice.setLayout(new BorderLayout(5, 0));
			pnFinalPrice.setBackground(MainWindow.COLOR_BACKGROUND);

			pnFinalPrice.add(getLblFinalPrice(), BorderLayout.WEST);
			pnFinalPrice.add(getTxtFinalPrice(), BorderLayout.CENTER);
		}
		return pnFinalPrice;
	}

	private JLabel getLblNumberOfPeople() {
		if (lblNumberOfPeople == null) {
			lblNumberOfPeople = new JLabel();
			lblNumberOfPeople.setLabelFor(getTxtNumberOfPeople());
			lblNumberOfPeople.setFont(new Font("Arial", Font.BOLD, 12));
			lblNumberOfPeople.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblNumberOfPeople;
	}

	private JTextField getTxtNumberOfPeople() {
		if (txtNumberOfPeople == null) {
			txtNumberOfPeople = new JTextField();
			txtNumberOfPeople.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNumberOfPeople.setBorder(null);
			txtNumberOfPeople.setEditable(false);
			txtNumberOfPeople.setColumns(10);
			txtNumberOfPeople.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtNumberOfPeople;
	}

	private JLabel getLblNumberOfRooms() {
		if (lblNumberOfRooms == null) {
			lblNumberOfRooms = new JLabel();
			lblNumberOfRooms.setLabelFor(getTxtNumberOfRooms());
			lblNumberOfRooms.setFont(new Font("Arial", Font.BOLD, 12));
			lblNumberOfRooms.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblNumberOfRooms;
	}

	private JTextField getTxtNumberOfRooms() {
		if (txtNumberOfRooms == null) {
			txtNumberOfRooms = new JTextField();
			txtNumberOfRooms.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNumberOfRooms.setBorder(null);
			txtNumberOfRooms.setEditable(false);
			txtNumberOfRooms.setColumns(10);
			txtNumberOfRooms.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtNumberOfRooms;
	}

	private JTextField getTxtNumberOfDays() {
		if (txtNumberOfDays == null) {
			txtNumberOfDays = new JTextField();
			txtNumberOfDays.setFont(new Font("Arial", Font.PLAIN, 12));
			txtNumberOfDays.setBorder(null);
			txtNumberOfDays.setEditable(false);
			txtNumberOfDays.setColumns(10);
			txtNumberOfDays.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtNumberOfDays;
	}

	private JLabel getLblNumberOfDays() {
		if (lblNumberOfDays == null) {
			lblNumberOfDays = new JLabel();
			lblNumberOfDays.setLabelFor(lblNumberOfDays);
			lblNumberOfDays.setFont(new Font("Arial", Font.BOLD, 12));
			lblNumberOfDays.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblNumberOfDays;
	}

	private JLabel getLblFinalPrice() {
		if (lblFinalPrice == null) {
			lblFinalPrice = new JLabel();
			lblFinalPrice.setLabelFor(getTxtFinalPrice());
			lblFinalPrice.setFont(new Font("Arial", Font.BOLD, 12));
			lblFinalPrice.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblFinalPrice;
	}

	private JTextField getTxtFinalPrice() {
		if (txtFinalPrice == null) {
			txtFinalPrice = new JTextField();
			txtFinalPrice.setFont(new Font("Arial", Font.PLAIN, 12));
			txtFinalPrice.setBorder(null);
			txtFinalPrice.setEditable(false);
			txtFinalPrice.setColumns(10);
			txtFinalPrice.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtFinalPrice;
	}

	private JPanel getPnMessage() {
		if (pnMessage == null) {
			pnMessage = new JPanel();
			pnMessage.setLayout(new FlowLayout());
			pnMessage.setBackground(MainWindow.COLOR_BACKGROUND);

			pnMessage.add(getTxtWarningMessage());
		}
		return pnMessage;
	}

	private JTextArea getTxtWarningMessage() {
		if (txtWarningMessage == null) {
			txtWarningMessage = new JTextArea();
			txtWarningMessage.setBorder(null);
			txtWarningMessage.setFont(new Font("Arial", Font.ITALIC, 14));
			txtWarningMessage.setEditable(false);
			txtWarningMessage.setBackground(MainWindow.COLOR_BACKGROUND);
			txtWarningMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
			txtWarningMessage.setAlignmentY(Component.CENTER_ALIGNMENT);
		}
		return txtWarningMessage;
	}
}
