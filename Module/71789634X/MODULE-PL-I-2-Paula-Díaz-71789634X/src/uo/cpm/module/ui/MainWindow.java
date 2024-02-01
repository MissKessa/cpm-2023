package uo.cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import uo.cpm.module.model.Castle;
import uo.cpm.module.service.Castlevania;
import uo.cpm.module.ui.panels.CastlePanel;
import uo.cpm.module.ui.panels.CastleWindowPanel;
import uo.cpm.module.ui.panels.DoneWindowPanel;
import uo.cpm.module.ui.panels.FormalizationWindowPanel;
import uo.cpm.module.ui.panels.FormalizeWarningWindowPanel;
import uo.cpm.module.ui.panels.ReservationWindowPanel;

/**
 * It's the mainwindow of the app. It's a cardLayout view with: a menu bar; and
 * pnMainWindow, pnCastleInformation, pnReservation, pnFormalizeWarning,
 * pnFormalization and PnDone
 * 
 * @author paula
 *
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
//	public static final Color COLOR_YELLOW = new Color(255, 255, 240);
//	public static final Color COLOR_PURPLE = new Color(221, 181, 251);
//	public static final Color COLOR_DARK_ORANGE = new Color(227, 184, 157);

	/**
	 * Color for the foreground of the buttons
	 */
	public static final Color COLOR_BUTTONS_LETTER = new Color(0, 28, 68);
	/**
	 * Color for the background of the buttons
	 */
	public static final Color COLOR_BUTTONS = new Color(191, 127, 4);

	/**
	 * Color for the background
	 */
	public static final Color COLOR_BACKGROUND = new Color(243, 237, 200);
	/**
	 * Color for the background darker
	 */
	public static final Color COLOR_BACKGROUND_DARKER = new Color(234, 209, 150);

	/**
	 * Green color for the buttons
	 */
	public static final Color COLOR_GREEN = new Color(0, 153, 0);
	/**
	 * Red color for the buttons
	 */
//	public static final Color COLOR_RED = new Color(255, 0, 0);
	public static final Color COLOR_RED = new Color(255, 81, 0); // more orange for color blindness

//	public static final String DEFAULT_LANGUAGE = "en";

	/**
	 * Panel with the card layout
	 */
	private JPanel contents;

	/**
	 * Menu bar for the main window
	 */
	private JMenuBar menuBar;
	private JMenu mnReservation;
	private JMenu mnHelp;
	private JMenuItem mntmNew;
	private JSeparator sepExit;
	private JMenuItem mntmExit;
	private JMenuItem mntmHelpContents;
	private JMenuItem mntmAbout;
	private JSeparator sepAbout;

	/**
	 * Panel for the window #3. It has all the detailed information of the castle:
	 * name, country, image, description, room price and list of enchantments
	 */
	private CastleWindowPanel pnCastleInformation;

	/**
	 * Panel for the window #4. It has the summary of the castle and the form to
	 * fill with the id, number of rooms, people and days, and staring date
	 */
	private ReservationWindowPanel pnReservation;

	/**
	 * Panel for the window #5. It warns the user that he won't be able to change
	 * the reservation if he formalizes. It contains a summary of the window #4
	 */
	private FormalizeWarningWindowPanel pnFormalizeWarning;

	/**
	 * Panel for the window #6. It has the form to fill with the name, surnames, id,
	 * email and comments to formalize the reservation
	 */
	private FormalizationWindowPanel pnFormalization;

	/**
	 * Panel for the window #7. It's the last panel for the reservation process
	 */
	private DoneWindowPanel pnDone;

	/**
	 * Panel for the window #1. It has the game button, all the filters, the button
	 * for changing the language, and the list with all the castles
	 */
	private JPanel pnMainWindow;

	/**
	 * Panel containing the button for the game, all filters and changing the
	 * language
	 */
	private JPanel pnBar;
	private JButton btnGame;
	private JButton btnLanguage;
	/**
	 * Panel to filter by country
	 */
	private JPanel pnCountry;
	private JLabel lblCountryFilter;
	private JComboBox<String> cbCountry;

	private JButton btnEnchantmentsFilter;
	/**
	 * Panel to filter by price
	 */
	private JPanel pnPrice;
	private JLabel lblPrice;
	private JComboBox<String> cbPrice;

	private JButton btnRemoveFilters;

	/**
	 * Panel containing the list of available castles and the number of available
	 * ones
	 */
	private JPanel pnCastles;

	/**
	 * Panel containing the number of available castles
	 */
	private JPanel pnAvailableCastles;
	private JLabel lblAvailableCastles;
	private JTextField txtAvailableCastles;

	private JScrollPane scrPnCastleList;
	/**
	 * Panel containing the list of available castles
	 */
	private JPanel pnCastleList;

	/**
	 * It's the service of the company
	 */
	private Castlevania service;
	/**
	 * It's the resource bundle for localizing
	 */
	private ResourceBundle texts;

	/**
	 * Dialog for filtering the enchantments
	 */
	private FilterEnchantmentsDialog fed;

	/**
	 * Dialog for changing the language
	 */
	private ChangeLanguageDialog cld;

	/**
	 * Dialog for playing the game (window #11)
	 */
	private GameDialog gameDialog;
	private JMenuItem mntmDiscount;

	/**
	 * Creates the frame: loads the castles and localizes the pnMainWindow
	 */
	public MainWindow(Castlevania service) {
		this.service = service;
		texts = getResourceBundle(Locale.getDefault(Locale.Category.FORMAT));
		service.loadCastles(texts.getString("castlesDataFile"));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				pnCastleInformation.adjustText();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				checkExit();
			}
		});

		setBackground(COLOR_BACKGROUND_DARKER);

		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logo.png")));
		setTitle("Castlevania");
		setBounds(100, 100, 672, 473);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setMinimumSize(new Dimension(1400, 400));

		setJMenuBar(getMenuBarMw());
		getContentPane().add(getContents());

		localize();
//		previousHeight = getHeight();
		loadHelp();
	}

	/**
	 * Loads the help support
	 */
	private void loadHelp() {
		URL hsURL;
		HelpSet hs;
		try {
			File fichero = new File("help/Help.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		} catch (Exception e) {
			System.out.println("Help not found!");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation(); // Preloads the help support
		hb.enableHelpKey(getRootPane(), "intro", hs);
		hb.enableHelpKey(pnMainWindow, "intro", hs); // allow access help support by F1 in this Frame (you need to do
														// it in every dialog)
		hb.enableHelpOnButton(getMntmHelpContents(), "intro", hs); // allow access help support by the button on the
																	// menu
		hb.enableHelp(getBtnGame(), "play", hs);

		hb.enableHelp(getPnCountry(), "filter", hs);
		hb.enableHelp(getPnPrice(), "filter", hs);
		hb.enableHelp(getPnPrice(), "filter", hs);
		hb.enableHelp(getBtnEnchantmentsFilter(), "filter", hs);
		hb.enableHelp(getBtnRemoveFilters(), "filter", hs);

		hb.enableHelp(getBtnLanguage(), "langu", hs);

		hb.enableHelpKey(pnCastleInformation, "castle w", hs);
		hb.enableHelpKey(pnReservation, "reserv", hs);
		hb.enableHelpKey(pnFormalizeWarning, "warn", hs);
		hb.enableHelpKey(pnFormalization, "formal", hs);
		hb.enableHelpKey(pnDone, "formal", hs);
	}

	private JPanel getContents() { // adds all the panels to the card layout
		if (contents == null) {
			contents = new JPanel();
			contents.setLayout(new CardLayout(0, 0));
			contents.setBackground(COLOR_BACKGROUND_DARKER);

			contents.add(getPnMainWindow(), "pnMainWindow");
			contents.add(getPnCastleInformation(), "pnCastleInformation");
			contents.add(getPnReservation(), "pnReservation");
			contents.add(getPnFormalizeWarningWindow(), "pnFormalizeWarningWindow");
			contents.add(getPnFormalization(), "pnFormalization");
			contents.add(getPnDone(), "pnDone");
		}
		return contents;
	}

	private JPanel getPnMainWindow() {
		if (pnMainWindow == null) {
			pnMainWindow = new JPanel();
			pnMainWindow.setBackground(COLOR_BACKGROUND_DARKER);
			pnMainWindow.setLayout(new BorderLayout(0, 10));

			pnMainWindow.add(getPnCastles(), BorderLayout.CENTER);
			pnMainWindow.add(getPnBar(), BorderLayout.NORTH);
		}
		return pnMainWindow;
	}

	private JPanel getPnCastleInformation() {
		if (pnCastleInformation == null) {
			pnCastleInformation = new CastleWindowPanel(this);
		}
		return pnCastleInformation;
	}

	private JPanel getPnReservation() {
		if (pnReservation == null) {
			pnReservation = new ReservationWindowPanel(this);
		}
		return pnReservation;
	}

	private JPanel getPnFormalizeWarningWindow() {
		if (pnFormalizeWarning == null) {
			pnFormalizeWarning = new FormalizeWarningWindowPanel(this);
		}
		return pnFormalizeWarning;
	}

	private JPanel getPnFormalization() {
		if (pnFormalization == null) {
			pnFormalization = new FormalizationWindowPanel(this);
		}
		return pnFormalization;
	}

	private JPanel getPnDone() {
		if (pnDone == null) {
			pnDone = new DoneWindowPanel(this);
		}
		return pnDone;
	}

	/**
	 * It initializes the app: shows pnMainWindow, removes the filters, creates a
	 * new reservation and localizes the pnMainWindow with the default language
	 */
	public void initialized() {
		getService().setInitialized(true);

		showPnMainWindow();
		removeFilters();
		getService().createReservation();

		localize(Locale.getDefault(Locale.Category.FORMAT));
	}

	/**
	 * Shows pnMainWindow and loads the discounts
	 */
	public void showPnMainWindow() {
		setResizable(true);
		setMinimumSize(new Dimension(1400, 400));

		service.loadDiscounts();

		((CardLayout) getContents().getLayout()).show(contents, "pnMainWindow");
	}

	/**
	 * After playing, it initializes the main window
	 */
	public void showPnMainWindowAfterGame() {
		initialized();
	}

	/**
	 * Shows the pnCastleInformation and sets the information according to the
	 * chosen castle
	 */
	public void showPnCastleInformation() {
		setResizable(true);
		setMinimumSize(new Dimension(700, 500));

		pnCastleInformation.setInformationInCastleWindow(service.getChoosenCastle());
		pnCastleInformation.adjustText();

		((CardLayout) getContents().getLayout()).show(contents, "pnCastleInformation");
	}

	/**
	 * Shows the pnReservation and sets the information according to the chosen
	 * castle
	 */
	public void showPnReservation() {
		setResizable(true);
		setMinimumSize(new Dimension(900, 800));

		pnReservation.setInformationInReservation(service.getChoosenCastle());

		((CardLayout) getContents().getLayout()).show(contents, "pnReservation");

		getService().setInitialized(false);
		pnReservation.grabFocusLegalAge();
	}

	/**
	 * Shows the pnFormalizeWarning and sets the information according to the chosen
	 * castle
	 */
	public void showPnFormalizeWarning() {
		setResizable(true);
		setMinimumSize(new Dimension(600, 350));

		pnFormalizeWarning.setInformationInCastleWindow(service.getChoosenCastle());

		((CardLayout) getContents().getLayout()).show(contents, "pnFormalizeWarningWindow");
	}

	/**
	 * Shows the pnFormalization and sets the information
	 */
	public void showPnFormalization() {
		setResizable(true);
		setMinimumSize(new Dimension(550, 400));

		pnFormalization.setInformation();

		((CardLayout) getContents().getLayout()).show(contents, "pnFormalization");
	}

	/**
	 * Shows the pnDone and localizes
	 */
	public void showPnDone() {
		setResizable(true);
		setMinimumSize(new Dimension(400, 300));

		pnDone.localize();

		((CardLayout) getContents().getLayout()).show(contents, "pnDone");
	}

	/**
	 * Creates the gameDialog and shows
	 */
	private void showGameDialog() {
		gameDialog = new GameDialog(this);

		gameDialog.setLocationRelativeTo(this);
		gameDialog.setModal(true);
		gameDialog.setVisible(true);
	}

	/**
	 * Checks the exit by showing a window. If the user clicks Yes, it ends the app
	 */
	private void checkExit() {
		if ((JOptionPane.showConfirmDialog(this, texts.getString("exitWindow.message"),
				getTitle() + ": " + texts.getString("exitWindow.title"), JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE)) == JOptionPane.YES_OPTION) {

			System.exit(0);
		}
	}

	/**
	 * Shows the About window
	 */
	private void showAboutWindow() {
		JOptionPane.showMessageDialog(this, texts.getString("aboutWindow.message"),
				getTitle() + ": " + texts.getString("aboutWindow.title"), JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(this.getIconImage()));
	}

	/**
	 * Creates the changing language window and shows
	 */
	private void showChangeLanguageWindow() {
		cld = new ChangeLanguageDialog(this);

		cld.setLocationRelativeTo(this);
		cld.setModal(true);
		cld.setVisible(true);
	}

	/**
	 * Creates the filter enchantments dialog and shows
	 */
	private void showFilterEnchantmentsDialog() {
		fed = new FilterEnchantmentsDialog(this);

		fed.setLocationRelativeTo(this);
		fed.setModal(true);
		fed.setVisible(true);
	}

	/**
	 * 
	 * @param locale
	 * @return the resource bundle according to the locale passed as parameter
	 */
	public static ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundle.getBundle("uo/cpm/module/internationalization/messages", locale);
	}

	/**
	 * Gets the resource bundle according to the locale passed as parameter, loads
	 * the castles, localizes the pnMainWindow, add the castle panels to the list,
	 * and localizes the filters
	 * 
	 * @param locale
	 */
	public void localize(Locale locale) {
		texts = getResourceBundle(locale);

		service.loadCastles(texts.getString("castlesDataFile"));

		localize();

		addCastlePanels();

		localizeCbCountry();
		localizeCbPrice();
	}

	/**
	 * Localizes the menu bar, the filters, the list of castles and the JOptionPanes
	 */
	private void localize() {
		localizeMenuBar();
		localizeBar();
		localizeListOfCastles();

		UIManager.put("OptionPane.yesButtonText", texts.getString("OptionPane.yesButtonText"));
		UIManager.put("OptionPane.cancelButtonText", texts.getString("OptionPane.cancelButtonText"));
		UIManager.put("OptionPane.noButtonText", texts.getString("OptionPane.noButtonText"));
		UIManager.put("OptionPane.okButtonText", texts.getString("OptionPane.okButtonText"));

		UIManager.put("OptionPane.yesButtonMnemonic", texts.getString("OptionPane.yesButtonMnemonic"));
//		UIManager.put("OptionPane.cancelButtonMnemonic", texts.getString("OptionPane.cancelButtonMnemonic"));
		UIManager.put("OptionPane.noButtonMnemonic", texts.getString("OptionPane.noButtonMnemonic"));
		UIManager.put("OptionPane.okButtonMnemonic", texts.getString("OptionPane.okButtonMnemonic"));
	}

	/**
	 * Localizes the money passed as parameter with the locale of the main window
	 * 
	 * @param money
	 * @return the string with the money localize
	 */
	public String localizeMoney(float money) {
		Locale locale = new Locale(texts.getString("locale"));

		NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(locale);
		moneyFormat.setCurrency(Currency.getInstance(texts.getString("money")));

		return moneyFormat.format(money);
	}

	/**
	 * Localizes the menu bar according to the locale of the main window
	 */
	private void localizeMenuBar() {
		mnReservation.setText(texts.getString("mnReservation.text"));
		mnReservation.setToolTipText(texts.getString("mnReservation.toolTip"));
		mnReservation.setMnemonic(texts.getString("mnReservation.mnemonic").charAt(0));

		mnHelp.setText(texts.getString("mnHelp.text"));
		mnHelp.setToolTipText(texts.getString("mnHelp.toolTip"));
		mnHelp.setMnemonic(texts.getString("mnHelp.mnemonic").charAt(0));

		mntmNew.setText(texts.getString("mntmNew.text"));
		mntmNew.setToolTipText(texts.getString("mntmNew.toolTip"));
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.getExtendedKeyCodeForChar(texts.getString("mntmNew.accelerator").charAt(0)),
				InputEvent.CTRL_DOWN_MASK));

		mntmDiscount.setText(texts.getString("mntmDiscount.text"));
		mntmDiscount.setToolTipText(texts.getString("mntmDiscount.toolTip"));
		mntmDiscount.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.getExtendedKeyCodeForChar(texts.getString("mntmDiscount.accelerator").charAt(0)),
				InputEvent.CTRL_DOWN_MASK));

		mntmExit.setText(texts.getString("mntmExit.text"));
		mntmExit.setToolTipText(texts.getString("mntmExit.toolTip"));

		mntmHelpContents.setText(texts.getString("mntmHelpContents.text"));
		mntmHelpContents.setToolTipText(texts.getString("mntmHelpContents.toolTip"));

		mntmAbout.setText(texts.getString("mntmAbout.text"));
		mntmAbout.setToolTipText(texts.getString("mntmAbout.toolTip"));
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.getExtendedKeyCodeForChar(texts.getString("mntmAbout.accelerator").charAt(0)),
				InputEvent.CTRL_DOWN_MASK));
	}

	/**
	 * Localizes the buttons and texts of the filters, changing language language
	 * and game according to the locale of the main window
	 */
	private void localizeBar() {
		btnGame.setText(texts.getString("btnGame.text"));
		btnGame.setToolTipText(texts.getString("btnGame.toolTip"));
		btnGame.setMnemonic(texts.getString("btnGame.mnemonic").charAt(0));

		btnLanguage
				.setIcon(new ImageIcon(MainWindow.class.getResource("/img/" + texts.getString("btnLanguage.image"))));
		btnLanguage.setToolTipText(texts.getString("btnLanguage.toolTip"));

		lblCountryFilter.setText(texts.getString("lblCountryFilter.text"));
		cbCountry.setToolTipText(texts.getString("cbCountry.toolTip"));
		lblCountryFilter.setDisplayedMnemonic(texts.getString("lblCountryFilter.mnemonic").charAt(0));

		btnEnchantmentsFilter.setText(texts.getString("btnEnchantmentsFilter.text"));
		btnEnchantmentsFilter.setToolTipText(texts.getString("btnEnchantmentsFilter.toolTip"));
		btnEnchantmentsFilter.setMnemonic(texts.getString("btnEnchantmentsFilter.mnemonic").charAt(0));

		lblPrice.setText(texts.getString("lblPrice.text"));
		cbPrice.setToolTipText(texts.getString("cbPrice.toolTip"));
		lblPrice.setDisplayedMnemonic(texts.getString("lblPrice.mnemonic").charAt(0));

		btnRemoveFilters.setText(texts.getString("btnRemoveFilters.text"));
		btnRemoveFilters.setToolTipText(texts.getString("btnRemoveFilters.toolTip"));
		btnRemoveFilters.setMnemonic(texts.getString("btnRemoveFilters.mnemonic").charAt(0));
	}

	/**
	 * Localizes the scroll pane of the castle lists and the number of available
	 * castles according to the locale of the main window
	 */
	private void localizeListOfCastles() {
		scrPnCastleList.setToolTipText(texts.getString("scrollPane.toolTip"));

		lblAvailableCastles.setText(texts.getString("lblAvailableCastles.text"));
		txtAvailableCastles.setToolTipText(texts.getString("txtAvailableCastles.toolTip"));
	}

	/**
	 * Localizes the combo box of the countries according to the locale of the main
	 * window to change the name of the countries to the current language
	 */
	private void localizeCbCountry() {
		ArrayList<String> model = (service.getCountries());
		model.add(0, texts.getString("cbCountry.baseOption"));
		cbCountry.setModel(new DefaultComboBoxModel(model.toArray()));
		cbCountry.setSelectedIndex(0);
	}

	/**
	 * Localizes the combo box of the prices according to the locale of the main
	 * window to change the currency
	 */
	private void localizeCbPrice() {
		ArrayList<String> prices = getModelPriceFilter();

		cbPrice.setModel(new DefaultComboBoxModel(prices.toArray()));
	}

	/**
	 * 
	 * @return the list of strings with the intervals of prices for the price filter
	 *         with currency localize
	 */
	private ArrayList<String> getModelPriceFilter() {
		ArrayList<String> prices = new ArrayList<>();
		float[] priceLimits = service.getPriceFilter();

		String limit = "";
		for (int i = 0; i < 4; i++) {
			limit = localizeMoney(priceLimits[i]) + " - " + localizeMoney(priceLimits[i + 1]);
			prices.add(limit);
		}
		prices.add(0, texts.getString("cbPrice.baseOption"));

		return prices;
	}

	private JMenuBar getMenuBarMw() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setForeground(new Color(0, 0, 0));
			menuBar.setBackground(new Color(197, 109, 56));

			menuBar.add(getMnReservation());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnReservation() {
		if (mnReservation == null) {
			mnReservation = new JMenu();
//			mnReservation = new JMenu("Reservation");
			mnReservation.setBackground(new Color(154, 85, 44));
			mnReservation.setForeground(new Color(0, 0, 0));
//			mnReservation.setToolTipText("Reservation");
//			mnReservation.setMnemonic('R');

			mnReservation.add(getMntmNew());
			mnReservation.add(getMntmDiscount());
			mnReservation.add(getSepExit());
			mnReservation.add(getMntmExit());
		}
		return mnReservation;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu();
//			mnHelp = new JMenu("Help");
			mnHelp.setBackground(new Color(154, 85, 44));
			mnHelp.setForeground(new Color(0, 0, 0));
//			mnHelp.setToolTipText("Help");
//			mnHelp.setMnemonic('H');

			mnHelp.add(getMntmHelpContents());
			mnHelp.add(getSepAbout());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem();
			mntmNew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					initialized();
				}
			});
//			mntmNew = new JMenuItem("New");
			mntmNew.setBackground(new Color(154, 85, 44));
			mntmNew.setForeground(new Color(0, 0, 0));
//			mntmNew.setToolTipText("New reservation");

		}
		return mntmNew;
	}

	private JMenuItem getMntmDiscount() {
		if (mntmDiscount == null) {
			mntmDiscount = new JMenuItem();
			mntmDiscount.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					checkGainingDiscount();
				}
			});
		}
		return mntmDiscount;
	}

	/**
	 * Warns the user if he goes to play a game will lose the reservation
	 */
	private void checkGainingDiscount() {
		if ((JOptionPane.showConfirmDialog(this, texts.getString("gainDiscountWindow.message"),
				getTitle() + ": " + texts.getString("gainDiscountWindow.title"), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE)) == JOptionPane.YES_OPTION) {

			showGameDialog();
		}
	}

	private JSeparator getSepExit() {
		if (sepExit == null) {
			sepExit = new JSeparator();
			sepExit.setBackground(new Color(154, 85, 44));
			sepExit.setForeground(new Color(255, 255, 255));
		}
		return sepExit;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem();
			mntmExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					checkExit();
				}
			});
//			mntmExit = new JMenuItem("Exit");
			mntmExit.setBackground(new Color(154, 85, 44));
			mntmExit.setForeground(new Color(0, 0, 0));
//			mntmExit.setToolTipText("Exit");
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		}
		return mntmExit;
	}

	private JMenuItem getMntmHelpContents() {
		if (mntmHelpContents == null) {
			mntmHelpContents = new JMenuItem();
//			mntmHelpContents = new JMenuItem("Help Contents");
			mntmHelpContents.setBackground(new Color(154, 85, 44));
			mntmHelpContents.setForeground(new Color(0, 0, 0));
//			mntmHelpContents.setToolTipText("Help contents");
			mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmHelpContents;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem();
			mntmAbout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showAboutWindow();
				}
			});
//			mntmAbout = new JMenuItem("About");
			mntmAbout.setBackground(new Color(154, 85, 44));
			mntmAbout.setForeground(new Color(0, 0, 0));
//			mntmAbout.setToolTipText("About");
			mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
		}
		return mntmAbout;
	}

	private JSeparator getSepAbout() {
		if (sepAbout == null) {
			sepAbout = new JSeparator();
			sepAbout.setBackground(new Color(154, 85, 44));
			sepAbout.setForeground(new Color(255, 255, 255));
		}
		return sepAbout;
	}

//	public void showOldPnReservation() {
//		pnReservation.showInformationInReservation(service.getChoosenCastle());
////		setMinimumSize(new Dimension(550, 470));
////		setResizable(false);
//		maxWidth = 700;
//		maxHeight = 500;
//
//		setMinimumSize(new Dimension(600, 500));
//		((CardLayout) getContents().getLayout()).show(contents, "pnReservation");
//		setSize(new Dimension(maxWidth, maxHeight));
//		pnReservation.grabFocusLegalAge();
//	}

	private JPanel getPnCastles() {
		if (pnCastles == null) {
			pnCastles = new JPanel();
			pnCastles.setBackground(COLOR_BACKGROUND_DARKER);
			pnCastles.setLayout(new BorderLayout(0, 0));

			pnCastles.add(getScrPnCastleList(), BorderLayout.CENTER);
			pnCastles.add(getPnAvailableCastles(), BorderLayout.NORTH);
		}
		return pnCastles;
	}

	private JPanel getPnBar() {
		if (pnBar == null) {
			pnBar = new JPanel();
			pnBar.setBackground(COLOR_BACKGROUND_DARKER);
			pnBar.setLayout(new GridLayout(1, 1, 10, 0));

			pnBar.add(getBtnGame());
			pnBar.add(getPnCountry());
			pnBar.add(getBtnEnchantmentsFilter());
			pnBar.add(getPnPrice());
			pnBar.add(getBtnRemoveFilters());
			pnBar.add(getBtnLanguage());
		}
		return pnBar;
	}

	private JButton getBtnGame() {
		if (btnGame == null) {
			btnGame = new JButton();
			btnGame.setForeground(COLOR_BUTTONS_LETTER);

			btnGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showGameDialog();
				}
			});
			btnGame.setFont(new Font("Arial", Font.BOLD, 12));
//			btnGame = new JButton("Game");
//			btnGame.setMnemonic('G');
//			btnGame.setToolTipText("Play the game");
			btnGame.setBackground(COLOR_BUTTONS);
			btnGame.setHorizontalTextPosition(SwingConstants.CENTER);
			btnGame.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnGame.setIcon(new ImageIcon(MainWindow.class.getResource("/img/game icon.png")));
		}
		return btnGame;
	}

	private JButton getBtnLanguage() {
		if (btnLanguage == null) {
			btnLanguage = new JButton("");
			btnLanguage.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showChangeLanguageWindow();
				}
			});
			// btnLanguage.setToolTipText("Change the language");
			btnLanguage.setBackground(COLOR_BACKGROUND);
			// btnLanguage.setIcon(new
			// ImageIcon(MainWindow.class.getResource("/img/uk.png")));
		}
		return btnLanguage;
	}

	private JPanel getPnCountry() {
		if (pnCountry == null) {
			pnCountry = new JPanel();
			pnCountry.setBackground(COLOR_BACKGROUND_DARKER);
			pnCountry.setLayout(new GridLayout(0, 1, 0, 0));

			pnCountry.add(getLblCountryFilter());
			pnCountry.add(getCbCountry());
		}
		return pnCountry;
	}

	private JLabel getLblCountryFilter() {
		if (lblCountryFilter == null) {
			lblCountryFilter = new JLabel();
			lblCountryFilter.setFont(new Font("Arial", Font.BOLD, 12));
			// lblCountryFilter = new JLabel("Country:");
			// lblCountryFilter.setDisplayedMnemonic('C');
			lblCountryFilter.setLabelFor(getCbCountry());
		}
		return lblCountryFilter;
	}

	private JComboBox<String> getCbCountry() {
		if (cbCountry == null) {
			cbCountry = new JComboBox<String>();
			cbCountry.setFont(new Font("Arial", Font.PLAIN, 12));
			cbCountry.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateCastleList();
				}
			});

			localizeCbCountry();

			// cbCountry.setToolTipText("Filter by country");
			cbCountry.setBackground(COLOR_BACKGROUND);
		}
		return cbCountry;
	}

	private JButton getBtnEnchantmentsFilter() {
		if (btnEnchantmentsFilter == null) {
			btnEnchantmentsFilter = new JButton();
			btnEnchantmentsFilter.setForeground(new Color(0, 28, 68));
			btnEnchantmentsFilter.setFont(new Font("Arial", Font.BOLD, 12));
			btnEnchantmentsFilter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showFilterEnchantmentsDialog();
				}
			});
//			btnEnchantmentsFilter = new JButton("Enchantments");
//			btnEnchantmentsFilter.setToolTipText("Filter by enchantments");
//			btnEnchantmentsFilter.setMnemonic('E');
			btnEnchantmentsFilter.setBackground(COLOR_BUTTONS);
		}
		return btnEnchantmentsFilter;
	}

	/**
	 * Updates the list of available castles: adds the panels for each castles,
	 * repaints them and adjust the images of the castles
	 */
	public void updateCastleList() {
		addCastlePanels();
		repaint();
		validate();
		adjustImagesCastleList();
	}

	/**
	 * Adjusts the images of the castle list when there are shown less than 3
	 * castles, and adjusts the image to the height to the height of the panel
	 */
	private void adjustImagesCastleList() {
		if (pnCastleList.getComponentCount() < 3) {
			int heightInfo = 0;
			int imageHeight = 0;

			for (Component panel : pnCastleList.getComponents()) {
				heightInfo = ((CastlePanel) panel).getPnHeight();
				imageHeight = ((CastlePanel) panel).getImageHeight();

				if (heightInfo != 0) {
					if (heightInfo != imageHeight)
						((CastlePanel) panel).scaleImage(heightInfo);
				}
			}
		}
	}

	private JPanel getPnPrice() {
		if (pnPrice == null) {
			pnPrice = new JPanel();
			pnPrice.setBackground(COLOR_BACKGROUND_DARKER);
			pnPrice.setLayout(new GridLayout(0, 1, 0, 0));

			pnPrice.add(getLblPrice());
			pnPrice.add(getCbPrice());
		}
		return pnPrice;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel();
			lblPrice.setFont(new Font("Arial", Font.BOLD, 12));
//			lblPrice = new JLabel("Price:");
//			lblPrice.setDisplayedMnemonic('P');
			lblPrice.setLabelFor(getCbPrice());
		}
		return lblPrice;
	}

	private JComboBox<String> getCbPrice() {
		if (cbPrice == null) {
			cbPrice = new JComboBox<String>();
			cbPrice.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					updateCastleList();
				}
			});
//			cbPrice.setToolTipText("Filter by price");
			// ArrayList<String> prices = service.getPrices();
			localizeCbPrice();
			cbPrice.setBackground(COLOR_BACKGROUND);
		}
		return cbPrice;
	}

	private JButton getBtnRemoveFilters() {
		if (btnRemoveFilters == null) {
			btnRemoveFilters = new JButton();
			btnRemoveFilters.setForeground(COLOR_BUTTONS_LETTER);
			btnRemoveFilters.setFont(new Font("Arial", Font.BOLD, 12));
//			btnRemoveFilters = new JButton("Remove filters");
			btnRemoveFilters.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					removeFilters();
				}
			});
//			btnRemoveFilters.setMnemonic('R');
//			btnRemoveFilters.setToolTipText("Remove filters");
			btnRemoveFilters.setBackground(COLOR_BUTTONS);
		}
		return btnRemoveFilters;
	}

	/**
	 * Removes the filters: adds all the enchantments to the filter, selects the
	 * default option in the combo box for the country and the price. Then it
	 * updates the castle list
	 */
	private void removeFilters() {
		service.addAllEnchanmentToFilter();
		cbCountry.setSelectedIndex(0);
		cbPrice.setSelectedIndex(0);

		updateCastleList();
	}

	private JScrollPane getScrPnCastleList() {
		if (scrPnCastleList == null) {
			scrPnCastleList = new JScrollPane();
			scrPnCastleList.setBackground(COLOR_BACKGROUND_DARKER);
			// scrollPane.setToolTipText("List of castles");
			scrPnCastleList.setViewportView(getPnCastleList());
		}
		return scrPnCastleList;
	}

	private JPanel getPnCastleList() {
		if (pnCastleList == null) {
			pnCastleList = new JPanel();
			pnCastleList.setBackground(COLOR_BACKGROUND_DARKER);
			pnCastleList.setLayout(new GridLayout(0, 1, 0, 0));

			addCastlePanels();
		}
		return pnCastleList;
	}

	/**
	 * It removes all the panels in the pnCastleList, filters the castles according
	 * to the selected options, and it adds all the filteres castles to the panel.
	 * It updates the number of available castles
	 */
	private void addCastlePanels() {
		pnCastleList.removeAll();
		CastlePanel castle;
		List<Castle> castles = service.getCastleList();

		castles = service.filterByCountry(castles, (String) getCbCountry().getSelectedItem(),
				getCbCountry().getSelectedIndex());

		castles = service.filterByPrice(castles, getCbPrice().getSelectedIndex());

		for (int i = 0; i < castles.size(); i++) {
			castle = new CastlePanel(this, castles.get(i));
			pnCastleList.add(castle);
		}

		getTxtAvailableCastles().setText("" + castles.size());
	}

	private JPanel getPnAvailableCastles() {
		if (pnAvailableCastles == null) {
			pnAvailableCastles = new JPanel();
			pnAvailableCastles.setBackground(COLOR_BACKGROUND_DARKER);
			pnAvailableCastles.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

			pnAvailableCastles.add(getLblAvailableCastles());
			pnAvailableCastles.add(getTxtAvailableCastles());
		}
		return pnAvailableCastles;
	}

	private JLabel getLblAvailableCastles() {
		if (lblAvailableCastles == null) {
			lblAvailableCastles = new JLabel();
			lblAvailableCastles.setFont(new Font("Arial", Font.BOLD, 12));
			// lblAvailableCastles = new JLabel("Available castles:");
			lblAvailableCastles.setLabelFor(getTxtAvailableCastles());
		}
		return lblAvailableCastles;
	}

	private JTextField getTxtAvailableCastles() {
		if (txtAvailableCastles == null) {
			txtAvailableCastles = new JTextField();
			txtAvailableCastles.setFont(new Font("Arial", Font.PLAIN, 12));
			txtAvailableCastles.setBorder(null);
			txtAvailableCastles.setText("" + getPnCastleList().getComponentCount());
			txtAvailableCastles.setBackground(COLOR_BACKGROUND_DARKER);
			// txtAvailableCastles.setToolTipText("Number of available castles");
			txtAvailableCastles.setEditable(false);
			txtAvailableCastles.setColumns(10);
		}
		return txtAvailableCastles;
	}

	/**
	 * 
	 * @return the business app
	 */
	public Castlevania getService() {
		return service;
	}

	/**
	 * 
	 * @return the resource bundle used by the main window
	 */
	public ResourceBundle getTexts() {
		return texts;
	}

}
