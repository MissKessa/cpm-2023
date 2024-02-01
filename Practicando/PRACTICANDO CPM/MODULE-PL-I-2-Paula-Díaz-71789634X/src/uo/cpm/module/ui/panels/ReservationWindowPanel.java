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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JCalendar;

import uo.cpm.module.model.Castle;
import uo.cpm.module.ui.MainWindow;
import uo.cpm.module.util.NotSemiColonKeyListener;

/**
 * It's the panel shown for making the reservation. It shows the summary of the
 * castle, the id, number of people, rooms, days and a calendar to show the
 * starting date
 * 
 * @author paula
 *
 */
public class ReservationWindowPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;
	/**
	 * It's the chosen castle
	 */
	private Castle castle;

	/**
	 * It's the id of the discount applied
	 */
	private String idDiscountApplied;

	/**
	 * Panel with the btnFormalize and btnBack
	 */
	private JPanel pnButtons;
	private JButton btnFormalize;
	private JButton btnBack;

	/**
	 * Panel with the summary of the castle and all the form to be filled to make
	 * the reservation
	 */
	private JPanel pnForm;

	/**
	 * Panel containing the summary of the castle
	 */
	private JPanel pnCastleInfo;

	/**
	 * Panel containing the name of the castle
	 */
	private JPanel pnCastleName;
	private JLabel lblCastleName;
	private JTextField txtCastleName;

	/**
	 * Panel containing the country of the castle
	 */
	private JPanel pnCastleCountry;
	private JLabel lblCastleCountry;
	private JTextField txtCastleCountry;

	/**
	 * Panel containing the enchantments of the castle
	 */
	private JPanel pnCastleEnchantments;
	private JLabel lblCastleEnchantments;
	private JTextField txtCastleEnchantments;

	/**
	 * Panel containing the room price of the castle
	 */
	private JPanel pnCastlePrice;
	private JLabel lblCastlePrice;
	private JTextField txtCastlePrice;

	/**
	 * Panel containing the form to be filled to make the reservation
	 */
	private JPanel pnReservationInfo;

	/**
	 * Panel that contains the starting date and the final price
	 */
	private JPanel pnSouth;

	/**
	 * Panel that contains the starting date
	 */
	private JPanel pnStartingDate;
	private JLabel lblStartingDate;
	/**
	 * It's the calendar to chose the starting date
	 */
	private JCalendar calendar;

	/**
	 * Panel taht contains the final price
	 */
	private JPanel pnFinalPrice;
	private JLabel lblFinalPrice;
	private JTextField txtFinalPrice;

	/**
	 * Panel that contains the chckbxLegalAge, id, number of rooms, days and people
	 */
	private JPanel pnCenter;

	/**
	 * Panel containing the number of people
	 */
	private JPanel pnNumberOfPeople;
	private JLabel lblNumberOfPeople;
	private JSpinner spNumberOfPeople;
	/**
	 * Panel containing the number of rooms
	 */
	private JPanel pnNumberOfRooms;
	private JLabel lblNumberOfRooms;
	private JSpinner spNumberOfRooms;
	/**
	 * Panel containing the number of days
	 */
	private JPanel pnNumberOfDays;
	private JLabel lblNumberOfDays;
	private JSpinner spNumberOfDays;

	/**
	 * Check box to indicate that the user has the legal age or it's older
	 */
	private JCheckBox chckbxLegalAge;

	/**
	 * Panel that contains the number of rooms, days and people
	 */
	private JPanel pnNumbers;

	/**
	 * Panel that contains the id and the button for claiming the discount
	 */
	private JPanel pnIdentify;
	private JPanel pnId;
	private JLabel lblId;
	private JTextField txtId;

	/**
	 * Panel containing the button for claiming the discount and the label that
	 * notifies if the user have a discount, if it has been applied...
	 */
	private JPanel pnDiscount;
	private JButton btnClaimDiscount;
	private JLabel lblMessageDiscount;

	/**
	 * Mouse listener that checks the formalization and the discount button
	 */
	private CheckFormAndDiscountMouseListener mL = new CheckFormAndDiscountMouseListener();
	/**
	 * Focus listener that checks the formalization and the discount button
	 */
	private checkFormAndDiscountFocusListener fL = new checkFormAndDiscountFocusListener();

	/**
	 * Change listener that updates the final price
	 */
	private updateFinalPriceChangeListener cL = new updateFinalPriceChangeListener();

	/**
	 * Create the panel.
	 * 
	 * @param mw is the reference to the main windo
	 */
	public ReservationWindowPanel(MainWindow mw) {
		this.mw = mw;

		setLayout(new BorderLayout(0, 0));
		setBackground(MainWindow.COLOR_BACKGROUND);

		add(getPnForm(), BorderLayout.CENTER);
		add(getPnButtons(), BorderLayout.SOUTH);

	}

	/**
	 * Sets the information of the summary castle and the form. If it's the first
	 * time for the user to see this panel, the form will be empty. If not, it will
	 * be with the information that he filled before. It also sets the btnFormalize
	 * as default and it localizes the panel
	 * 
	 * @param castle is the chosen castle
	 */
	public void setInformationInReservation(Castle castle) {
		this.castle = castle;

		txtCastleName.setText(castle.getName());
		txtCastleCountry.setText(castle.getCountry());
		txtCastleEnchantments.setText(castle.getEnchantments());
		txtCastlePrice.setText(mw.localizeMoney(castle.getPrice()));

		if (mw.getService().isInitialized()) {
			initialize();
		}

		getRootPane().setDefaultButton(btnFormalize);
		mw.getService().setRoomPrice(castle.getPrice());

		updateTxtFinalPrice();
		localize();

	}

	/**
	 * It initializes the panel with all the form empty and with the default values
	 */
	private void initialize() {
		idDiscountApplied = null;
		txtId.setText("");
		btnClaimDiscount.setEnabled(false);
		lblMessageDiscount.setEnabled(false);
		lblMessageDiscount.setText("");

		spNumberOfPeople.setValue(Integer.valueOf(1));
		spNumberOfRooms.setValue(Integer.valueOf(1));
		spNumberOfDays.setValue(Integer.valueOf(1));

		String date_string = "" + LocalDate.now().getDayOfMonth() + "-" + LocalDate.now().getMonthValue() + "-"
				+ LocalDate.now().getYear();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = null;
		try {
			date = formatter.parse(date_string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setDate(date);

		chckbxLegalAge.setSelected(false);

		btnFormalize.setEnabled(false);
		btnFormalize.setBackground(Color.GRAY);

		mw.getService().setDiscountApplied(null);
	}

	/**
	 * It localizes the panel according to the locale chosen in the main window
	 */
	private void localize() {
		ResourceBundle texts = mw.getTexts();

		lblCastleName.setText(texts.getString("lblCastleName.text"));
		txtCastleName.setToolTipText(texts.getString("txtCastleName.toolTip"));

		lblCastleCountry.setText(texts.getString("lblCastleCountry.text"));
		txtCastleCountry.setToolTipText(texts.getString("txtCastleCountry.toolTip"));

		lblCastleEnchantments.setText(texts.getString("lblCastleEnchantments.text"));
		txtCastleEnchantments.setToolTipText(texts.getString("txtCastleEnchantments.toolTip"));

		lblCastlePrice.setText(texts.getString("lblCastlePrice.text"));
		txtCastlePrice.setToolTipText(texts.getString("txtCastlePrice.toolTip"));

//		btnBack.setText(texts.getString("btnCancelFormalization.text"));
//		btnBack.setToolTipText(texts.getString("btnCancelFormalization.toolTip"));
//		btnCancel.setMnemonic(texts.getString("btnCancelFormalization.mnemonic").charAt(0));

		btnBack.setText(texts.getString("btnBack.text"));
		btnBack.setToolTipText(texts.getString("btnBack.toolTip"));
		btnBack.setMnemonic(texts.getString("btnBack.mnemonic").charAt(0));

		btnFormalize.setText(texts.getString("btnFormalize.text"));
		btnFormalize.setToolTipText(texts.getString("btnFormalize.toolTip"));

		pnCastleInfo.setBorder(new TitledBorder(null, texts.getString("pnCastleInfo.title"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		pnReservationInfo.setBorder(new TitledBorder(null, texts.getString("pnReservationInfo.title"),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		chckbxLegalAge.setText(texts.getString("chckbxLegalAge.text"));
		chckbxLegalAge.setMnemonic(texts.getString("chckbxLegalAge.mnemonic").charAt(0));

		lblStartingDate.setText(texts.getString("lblDate.text"));
		calendar.setToolTipText(texts.getString("txtDate.toolTip"));
		lblStartingDate.setDisplayedMnemonic(texts.getString("lblDate.mnemonic").charAt(0));
		calendar.setLocale(new Locale(texts.getString("locale")));
		calendar.setTodayButtonText(texts.getString("calendar.TodayButtonText"));

		lblId.setText(texts.getString("lblId.text"));
		txtId.setToolTipText(texts.getString("txtId.toolTip"));
		lblId.setDisplayedMnemonic(texts.getString("lblId.mnemonic").charAt(0));

		lblNumberOfPeople.setText(texts.getString("lblNumberOfPeople.text"));
		spNumberOfPeople.setToolTipText(texts.getString("txtNumberOfPeople.toolTip"));
		lblNumberOfPeople.setDisplayedMnemonic(texts.getString("lblNumberOfPeople.mnemonic").charAt(0));

		lblNumberOfRooms.setText(texts.getString("lblNumberOfRooms.text"));
		spNumberOfRooms.setToolTipText(texts.getString("txtNumberOfRooms.toolTip"));
		lblNumberOfRooms.setDisplayedMnemonic(texts.getString("lblNumberOfRooms.mnemonic").charAt(0));

		lblNumberOfDays.setText(texts.getString("lblNumberOfDays.text"));
		spNumberOfDays.setToolTipText(texts.getString("txtNumberOfDays.toolTip"));
		lblNumberOfDays.setDisplayedMnemonic(texts.getString("lblNumberOfDays.mnemonic").charAt(0));

		lblFinalPrice.setText(texts.getString("lblFinalPrice.text"));
		txtFinalPrice.setToolTipText(texts.getString("txtFinalPrice.toolTip"));

		btnClaimDiscount.setText(texts.getString("btnClaimDiscount.text"));
		btnClaimDiscount.setToolTipText(texts.getString("btnClaimDiscount.toolTip"));
		btnClaimDiscount.setMnemonic(texts.getString("btnClaimDiscount.mnemonic").charAt(0));
	}

	/**
	 * Makes the reservation and shows the next panel
	 */
	private void showNextPanel() {
		makeReservation();
		mw.showPnFormalizeWarning();
	}

	/**
	 * Makes the reservation by passing to the reservation the id, castle code,
	 * starting date, number of days, rooms and people
	 */
	private void makeReservation() {
		mw.getService().makeReservation(txtId.getText(), castle.getCode(), calendar.getDate(),
				(Integer) spNumberOfDays.getValue(), (Integer) spNumberOfRooms.getValue(),
				(Integer) spNumberOfPeople.getValue());
	}

	/**
	 * Grabs the focus for the check box for the legal age
	 */
	public void grabFocusLegalAge() {
		chckbxLegalAge.grabFocus();
	}

	private JCalendar getCalendar() {
		if (calendar == null) {
			calendar = new JCalendar();

			calendar.getYearChooser().setStartYear(LocalDate.now().getYear()); // the current year is the minimum year
																				// of the calendar

			calendar.setMinSelectableDate(new Date()); // the minimum date that can be selected is today
			calendar.setTodayButtonVisible(true);
		}
		return calendar;
	}

	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setLayout(new BorderLayout(0, 10));
			pnForm.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnForm.add(getPnCastleInfo(), BorderLayout.NORTH);
			pnForm.add(getPnReservationInfo(), BorderLayout.CENTER);
		}
		return pnForm;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnButtons.add(getBtnBack());

			pnButtons.add(getBtnFormalize());
		}
		return pnButtons;
	}

	private JButton getBtnFormalize() {
		if (btnFormalize == null) {
			btnFormalize = new JButton();
			btnFormalize.setForeground(new Color(255, 255, 255));
			btnFormalize.setFont(new Font("Arial", Font.BOLD, 12));

			btnFormalize.addMouseListener(mL);
			btnFormalize.addFocusListener(fL);
			btnFormalize.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showNextPanel();
				}
			});

			btnFormalize.setEnabled(false);
			btnFormalize.setBackground(Color.GRAY);
//			btnFormalize.setToolTipText("Formalize reservation");
		}
		return btnFormalize;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton();
			btnBack.setForeground(new Color(255, 255, 255));
			btnBack.setFont(new Font("Arial", Font.BOLD, 12));
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mw.showPnCastleInformation();
				}
			});
//			btnCancel.setToolTipText("Cancel");
//			btnCancel.setMnemonic('C');
			btnBack.setBackground(MainWindow.COLOR_BUTTONS);
			btnBack.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
		}
		return btnBack;
	}

	private JPanel getPnCastleInfo() {
		if (pnCastleInfo == null) {
			pnCastleInfo = new JPanel();
			pnCastleInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCastleInfo.setLayout(new GridLayout(2, 0, 10, 10));
			pnCastleInfo.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCastleInfo.add(getPnCastleName());
			pnCastleInfo.add(getPnCastleCountry());
			pnCastleInfo.add(getPnCastleEnchantments());
			pnCastleInfo.add(getPnCastlePrice());
		}
		return pnCastleInfo;
	}

	private JPanel getPnReservationInfo() {
		if (pnReservationInfo == null) {
			pnReservationInfo = new JPanel();
			pnReservationInfo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnReservationInfo.setLayout(new GridLayout(0, 1, 10, 10));

			pnReservationInfo.setBackground(MainWindow.COLOR_BACKGROUND);
			pnReservationInfo.add(getPnCenter());
//			pnReservationInfo.add(getChckbxLegalAge(), BorderLayout.NORTH);
			pnReservationInfo.add(getPnSouth());
		}
		return pnReservationInfo;
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

	private JPanel getPnCastleCountry() {
		if (pnCastleCountry == null) {
			pnCastleCountry = new JPanel();
			pnCastleCountry.setLayout(new BorderLayout(5, 0));
			pnCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCastleCountry.add(getLblCastleCountry(), BorderLayout.WEST);
			pnCastleCountry.add(getTxtCastleCountry(), BorderLayout.CENTER);
		}
		return pnCastleCountry;
	}

	private JPanel getPnCastleEnchantments() {
		if (pnCastleEnchantments == null) {
			pnCastleEnchantments = new JPanel();
			pnCastleEnchantments.setLayout(new BorderLayout(5, 0));
			pnCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCastleEnchantments.add(getLblCastleEnchantments(), BorderLayout.WEST);
			pnCastleEnchantments.add(getTxtCastleEnchantments(), BorderLayout.CENTER);
		}
		return pnCastleEnchantments;
	}

	private JPanel getPnCastlePrice() {
		if (pnCastlePrice == null) {
			pnCastlePrice = new JPanel();
			pnCastlePrice.setLayout(new BorderLayout(5, 0));
			pnCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCastlePrice.add(getLblCastlePrice(), BorderLayout.WEST);
			pnCastlePrice.add(getTxtCastlePrice(), BorderLayout.CENTER);
		}
		return pnCastlePrice;
	}

	private JCheckBox getChckbxLegalAge() {
		if (chckbxLegalAge == null) {
			chckbxLegalAge = new JCheckBox();
			chckbxLegalAge.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					checkFormalization();
					checkDiscountButton();
				}
			});
			chckbxLegalAge.setFont(new Font("Arial", Font.PLAIN, 12));

			chckbxLegalAge.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					checkIsLegalAge();
				}
			});
		}
		return chckbxLegalAge;
	}

	/**
	 * Checks the legal age of the user. If the user didn't select he checkbox a
	 * window is displayed telling him that he must mark the checkbox. If he doesn't
	 * want to mark it, it goes to the panel before
	 */
	private void checkIsLegalAge() {
		if (!chckbxLegalAge.isSelected()) {
			ResourceBundle texts = mw.getTexts();

			if (JOptionPane.showConfirmDialog(this, texts.getString("JOptionPane.reservation.message"),
					mw.getTitle() + texts.getString("JOptionPane.reservation.title"), JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.WARNING_MESSAGE) == JOptionPane.CANCEL_OPTION) {

				mw.showPnCastleInformation();

			} else {

				chckbxLegalAge.grabFocus();
			}
		}
	}

	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setLayout(new BorderLayout(10, 0));
			pnSouth.setBackground(MainWindow.COLOR_BACKGROUND);

			pnSouth.add(getPnStartingDate(), BorderLayout.CENTER);
			pnSouth.add(getPnFinalPrice(), BorderLayout.EAST);
		}
		return pnSouth;
	}

	private JPanel getPnStartingDate() {
		if (pnStartingDate == null) {
			pnStartingDate = new JPanel();
			pnStartingDate.setLayout(new BorderLayout(5, 0));
			pnStartingDate.setBackground(MainWindow.COLOR_BACKGROUND);

			pnStartingDate.add(getLblStartingDate(), BorderLayout.WEST);
//			pnStartingDate.add(getTxtStartingDate());
			pnStartingDate.add(getCalendar());
		}
		return pnStartingDate;
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

	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setLayout(new GridLayout(0, 1, 10, 10));
			pnCenter.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCenter.add(getChckbxLegalAge());
			pnCenter.add(getPnIdentify());
			pnCenter.add(getPnNumbers());
		}
		return pnCenter;
	}

	private JPanel getPnNumbers() {
		if (pnNumbers == null) {
			pnNumbers = new JPanel();
			pnNumbers.setLayout(new GridLayout(2, 0, 10, 10));
			pnNumbers.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumbers.add(getPnNumberOfPeople());
			pnNumbers.add(getPnNumberOfRooms());
			pnNumbers.add(getPnNumberOfDays());
		}
		return pnNumbers;
	}

	private JPanel getPnIdentify() {
		if (pnIdentify == null) {
			pnIdentify = new JPanel();
			pnIdentify.setLayout(new BorderLayout(10, 0));
			pnIdentify.setBackground(MainWindow.COLOR_BACKGROUND);

			pnIdentify.add(getPnId(), BorderLayout.CENTER);
			pnIdentify.add(getPnDiscount(), BorderLayout.EAST);
//			pnIdentify.add(getChckbxLegalAge(), BorderLayout.NORTH);
		}
		return pnIdentify;
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

	private JLabel getLblCastleCountry() {
		if (lblCastleCountry == null) {
			lblCastleCountry = new JLabel();
			lblCastleCountry.setLabelFor(getTxtCastleCountry());
			lblCastleCountry.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastleCountry;
	}

	private JLabel getLblCastleEnchantments() {
		if (lblCastleEnchantments == null) {
			lblCastleEnchantments = new JLabel();
			lblCastleEnchantments.setLabelFor(getTxtCastleEnchantments());
			lblCastleEnchantments.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastleEnchantments;
	}

	private JLabel getLblCastlePrice() {
		if (lblCastlePrice == null) {
			lblCastlePrice = new JLabel();
			lblCastlePrice.setLabelFor(getTxtCastlePrice());
			lblCastlePrice.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastlePrice;
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

	private JTextField getTxtCastleCountry() {
		if (txtCastleCountry == null) {
			txtCastleCountry = new JTextField();
			txtCastleCountry.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleCountry.setBorder(null);
			txtCastleCountry.setEditable(false);
			txtCastleCountry.setColumns(10);
			txtCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtCastleCountry;
	}

	private JTextField getTxtCastleEnchantments() {
		if (txtCastleEnchantments == null) {
			txtCastleEnchantments = new JTextField();
			txtCastleEnchantments.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleEnchantments.setBorder(null);
			txtCastleEnchantments.setEditable(false);
			txtCastleEnchantments.setColumns(10);
			txtCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtCastleEnchantments;
	}

	private JTextField getTxtCastlePrice() {
		if (txtCastlePrice == null) {
			txtCastlePrice = new JTextField();
			txtCastlePrice.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastlePrice.setBorder(null);
			txtCastlePrice.setEditable(false);
			txtCastlePrice.setColumns(10);
			txtCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return txtCastlePrice;
	}

	private JLabel getLblStartingDate() {
		if (lblStartingDate == null) {
			lblStartingDate = new JLabel();
			lblStartingDate.setLabelFor(getCalendar());
			lblStartingDate.setFont(new Font("Arial", Font.BOLD, 12));
			lblStartingDate.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblStartingDate;
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
			txtFinalPrice.setBorder(null);
			txtFinalPrice.setFont(new Font("Arial", Font.PLAIN, 12));
			txtFinalPrice.setEditable(false);
			txtFinalPrice.setColumns(10);
			txtFinalPrice.setBackground(MainWindow.COLOR_BACKGROUND);

			updateTxtFinalPrice();
		}
		return txtFinalPrice;
	}

	/**
	 * It updates the final price of the reservation according to the number of
	 * rooms selected. It localizes the money according to the locale chosen in the
	 * main window
	 */
	public void updateTxtFinalPrice() {
		txtFinalPrice
				.setText(mw.localizeMoney(mw.getService().calculateFinalPrice((Integer) getSpNumberOfRooms().getValue(),
						(Integer) getSpNumberOfDays().getValue())));
	}

	private JPanel getPnId() {
		if (pnId == null) {
			pnId = new JPanel();
			pnId.setLayout(new BorderLayout(5, 0));
			pnId.setBackground(MainWindow.COLOR_BACKGROUND);

			pnId.add(getLblId(), BorderLayout.WEST);
			pnId.add(getTxtId(), BorderLayout.CENTER);
		}
		return pnId;
	}

	private JPanel getPnDiscount() {
		if (pnDiscount == null) {
			pnDiscount = new JPanel();
			pnDiscount.setLayout(new BorderLayout(0, 5));
			pnDiscount.setBackground(MainWindow.COLOR_BACKGROUND);

			pnDiscount.add(getBtnClaimDiscount(), BorderLayout.CENTER);
			pnDiscount.add(getLblMessageDiscount(), BorderLayout.SOUTH);
		}
		return pnDiscount;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel();
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
			txtId.setBackground(MainWindow.COLOR_BACKGROUND);
			txtId.setColumns(10);

			txtId.addMouseListener(mL);
			txtId.addFocusListener(fL);
			txtId.addKeyListener(new NotSemiColonKeyListener());
		}
		return txtId;
	}

	/**
	 * Checks that the form is correct: the id is not blank
	 */
	private void checkFormalization() {
		if (!(txtId.getText().isEmpty() || txtId.getText().isBlank()) && chckbxLegalAge.isSelected()) {
			btnFormalize.setEnabled(true);
			btnFormalize.setBackground(MainWindow.COLOR_GREEN);

		} else {
			btnFormalize.setEnabled(false);
			btnFormalize.setBackground(Color.GRAY);
		}
	}

	/**
	 * Checks if the id is valid or not, and according to that it sets the
	 * pnDiscount with the corresponding texts and if the button is disabled or not
	 */
	private void checkDiscountButton() {
		if (!(txtId.getText().isEmpty() || txtId.getText().isBlank())) {
			checkDiscountForValidId();

		} else {
			checkDiscountForNoValidId();
		}
	}

	/**
	 * It sets the pnDiscount with the corresponding texts and if the button is
	 * disabled or not, depending if the id has a discount or not, and if it's a new
	 * id or not
	 */
	private void checkDiscountForValidId() {
		ResourceBundle texts = mw.getTexts();
		if (!(mw.getService().hasDiscount(txtId.getText()))) {
			noDiscount(texts);

		} else if (!txtId.getText().equals(idDiscountApplied)) {
			newIdWithDiscount();

		} else if (mw.getService().hasDiscount(txtId.getText()) && (txtId.getText().equals(idDiscountApplied))) { // has
			sameIdWithDiscount(texts);

		}
	}

	/**
	 * The id is valid but it doesn't have a discount so there is no discount
	 * applied, it updates the final price and the btnClaimDiscount is disabled
	 */
	private void noDiscount(ResourceBundle texts) {
		mw.getService().setDiscountApplied(null);
		idDiscountApplied = null;
		updateTxtFinalPrice();
		btnClaimDiscount.setEnabled(false);
		lblMessageDiscount.setText(texts.getString("lblMessageDiscount.text.noDiscount"));
		lblMessageDiscount.setEnabled(true);
	}

	/**
	 * The id is valid but it's a new id so there is no discount applied, it updates
	 * the final price and the btnClaimDiscount is enabled
	 */
	private void newIdWithDiscount() {
		mw.getService().setDiscountApplied(null);
		idDiscountApplied = null;

		updateTxtFinalPrice();
		btnClaimDiscount.setEnabled(true);
		lblMessageDiscount.setText("");
		lblMessageDiscount.setEnabled(false);
	}

	/**
	 * The id is valid and the same as the one of the discount applied so it
	 * notifies the user that it's already applied the discount
	 */
	private void sameIdWithDiscount(ResourceBundle texts) {
		lblMessageDiscount.setText(texts.getString("lblMessageDiscount.text.discountApplied"));
	}

	/**
	 * The id is not valid so there is no discount applied, and the btnClaimDiscount
	 * is disabled
	 */
	private void checkDiscountForNoValidId() {
		mw.getService().setDiscountApplied(null);
		btnClaimDiscount.setEnabled(false);
		lblMessageDiscount.setText("");
		lblMessageDiscount.setEnabled(false);
	}

	private JButton getBtnClaimDiscount() {
		if (btnClaimDiscount == null) {
			btnClaimDiscount = new JButton();
			btnClaimDiscount.setFont(new Font("Arial", Font.BOLD, 12));

			btnClaimDiscount.addMouseListener(mL);
			btnClaimDiscount.addFocusListener(fL);
			btnClaimDiscount.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					claimDiscount();
				}
			});
			btnClaimDiscount.setEnabled(false);
			btnClaimDiscount.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return btnClaimDiscount;
	}

	/**
	 * It claims the discount: it sets the discount applied according to the id,
	 * updates the final price, notifies the user that it has been applied and
	 * disables the button for claiming the discount
	 */
	private void claimDiscount() {
		ResourceBundle texts = mw.getTexts();

		mw.getService().setDiscountApplied(mw.getService().searchDiscount(txtId.getText()));
		idDiscountApplied = txtId.getText();

		updateTxtFinalPrice();
		btnClaimDiscount.setEnabled(false);

		lblMessageDiscount.setText(texts.getString("lblMessageDiscount.text.discountApplied"));
	}

	private JLabel getLblMessageDiscount() {
		if (lblMessageDiscount == null) {
			lblMessageDiscount = new JLabel("");
			lblMessageDiscount.setEnabled(false);
			lblMessageDiscount.setFont(new Font("Arial", Font.PLAIN, 12));
			lblMessageDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblMessageDiscount.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblMessageDiscount;
	}

	private JPanel getPnNumberOfPeople() {
		if (pnNumberOfPeople == null) {
			pnNumberOfPeople = new JPanel();
			pnNumberOfPeople.setLayout(new GridLayout(0, 2, 0, 0));
			pnNumberOfPeople.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumberOfPeople.add(getLblNumberOfPeople());
			pnNumberOfPeople.add(getSpNumberOfPeople());
		}
		return pnNumberOfPeople;
	}

	private JPanel getPnNumberOfRooms() {
		if (pnNumberOfRooms == null) {
			pnNumberOfRooms = new JPanel();
			pnNumberOfRooms.setLayout(new GridLayout(0, 2, 0, 0));
			pnNumberOfRooms.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumberOfRooms.add(getLblNumberOfRooms());
			pnNumberOfRooms.add(getSpNumberOfRooms());
		}
		return pnNumberOfRooms;
	}

	private JPanel getPnNumberOfDays() {
		if (pnNumberOfDays == null) {
			pnNumberOfDays = new JPanel();
			pnNumberOfDays.setLayout(new GridLayout(0, 2, 0, 0));
			pnNumberOfDays.setBackground(MainWindow.COLOR_BACKGROUND);

			pnNumberOfDays.add(getLblNumberOfDays());
			pnNumberOfDays.add(getSpNumberOfDays());
		}
		return pnNumberOfDays;
	}

	private JLabel getLblNumberOfPeople() {
		if (lblNumberOfPeople == null) {
			lblNumberOfPeople = new JLabel();
			lblNumberOfPeople.setLabelFor(getSpNumberOfPeople());
			lblNumberOfPeople.setFont(new Font("Arial", Font.BOLD, 12));
			lblNumberOfPeople.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblNumberOfPeople;
	}

	private JLabel getLblNumberOfRooms() {
		if (lblNumberOfRooms == null) {
			lblNumberOfRooms = new JLabel();
			lblNumberOfRooms.setLabelFor(getSpNumberOfRooms());
			lblNumberOfRooms.setBackground(MainWindow.COLOR_BACKGROUND);
			lblNumberOfRooms.setFont(new Font("Arial", Font.BOLD, 12));
		}
		return lblNumberOfRooms;
	}

	private JLabel getLblNumberOfDays() {
		if (lblNumberOfDays == null) {
			lblNumberOfDays = new JLabel();
			lblNumberOfDays.setLabelFor(getSpNumberOfDays());
			lblNumberOfDays.setFont(new Font("Arial", Font.BOLD, 12));
			lblNumberOfDays.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblNumberOfDays;
	}

	private JSpinner getSpNumberOfPeople() {
		if (spNumberOfPeople == null) {
			spNumberOfPeople = new JSpinner();
			spNumberOfPeople.setFont(new Font("Arial", Font.PLAIN, 12));
			spNumberOfPeople.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					calculateNewLimitsForSpNumberOfRooms((Integer) ((JSpinner) e.getSource()).getValue());
				}
			});
			spNumberOfPeople
					.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spNumberOfPeople.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return spNumberOfPeople;
	}

	/**
	 * Calculates the minimum and maximum value for the spinner number of rooms
	 * according to the number of people. The minimum value must be able to have at
	 * most 2 people per room. The maximum value is the number of people (one room
	 * for each person). After setting the minimum it updates the final price
	 * 
	 * @param numberOfPeople the value of the spinner number of people
	 */
	private void calculateNewLimitsForSpNumberOfRooms(Integer numberOfPeople) {
		Integer min;

		if (numberOfPeople % 2 == 0)
			min = numberOfPeople / 2;
		else
			min = (numberOfPeople + 1) / 2;

		Integer max = numberOfPeople;
		spNumberOfRooms.setModel(new SpinnerNumberModel(min, min, max, Integer.valueOf(1)));

		updateTxtFinalPrice();
	}

	private JSpinner getSpNumberOfRooms() {
		if (spNumberOfRooms == null) {
			spNumberOfRooms = new JSpinner();
			spNumberOfRooms.setFont(new Font("Arial", Font.PLAIN, 12));
			spNumberOfRooms.addChangeListener(cL);
			spNumberOfRooms.setModel(new SpinnerNumberModel(1, 1, 1, 1));
			spNumberOfRooms.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return spNumberOfRooms;
	}

	private JSpinner getSpNumberOfDays() {
		if (spNumberOfDays == null) {
			spNumberOfDays = new JSpinner();
			spNumberOfDays.setFont(new Font("Arial", Font.PLAIN, 12));
			spNumberOfDays
					.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spNumberOfDays.setBackground(MainWindow.COLOR_BACKGROUND);

			spNumberOfDays.addChangeListener(cL);
		}
		return spNumberOfDays;
	}

	/**
	 * Mouse listener that checks that the form is correct and checks the discount
	 * button
	 * 
	 * @author paula
	 *
	 */
	private class CheckFormAndDiscountMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			checkFormalization();
			checkDiscountButton();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			checkFormalization();
			checkDiscountButton();
		}
	}

	/**
	 * Mouse listener that checks that the form is correct and checks the discount
	 * button
	 * 
	 * @author paula
	 *
	 */
	private class checkFormAndDiscountFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) {
			checkFormalization();
			checkDiscountButton();
		}

		@Override
		public void focusLost(FocusEvent e) {
			checkFormalization();
			checkDiscountButton();
		}
	}

	/**
	 * Updates the final price when the state of the component has changed
	 * 
	 * @author paula
	 *
	 */
	private class updateFinalPriceChangeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			updateTxtFinalPrice();
		}
	}

}
