package uo.cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * It's the window for changing the language. A combo box with all the available
 * languages is shown and 2 buttons (accept and cancel)
 * 
 * @author paula
 *
 */
public class ChangeLanguageDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;

	/**
	 * It's a reference to the Resource bundle use by the main window
	 */
	private ResourceBundle texts;

	/**
	 * Panel that contains the buttons
	 */
	private JPanel pnButtons;
	private JButton btnAccept;
	private JButton btnCancel;

	/**
	 * Panel that contains the image of the language, and the combo box with the
	 * languages
	 */
	private JPanel pnInformation;
	private JLabel lblLanguageImage;

	/**
	 * Panel that contains the como box with the languages
	 */
	private JPanel pnLanguage;
	private JLabel lblLanguage;
	private JComboBox<String> cbLanguage;

	/**
	 * List of available languages
	 */
	private ArrayList<String> languages = new ArrayList<>();

	/**
	 * Create the dialog: it add the available languages, sets btnAccept as the
	 * default button, and localizes the panel with the locale chosen in the main
	 * window
	 * 
	 * @param mainWindow
	 */
	public ChangeLanguageDialog(MainWindow mw) {
		setResizable(false);
		this.mw = mw;
		this.texts = mw.getTexts();

//		addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent e) {
//				System.out.println(((JDialog) e.getSource()).getWidth());
//				System.out.println(((JDialog) e.getSource()).getHeight());
//			}
//		});

		addLanguages();

//		setTitle(mw.getTitle() + ": Change language");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		setIconImage(mw.getIconImage());
		setBounds(100, 100, 291, 141);
		getContentPane().setLayout(new BorderLayout(0, 10));
		this.getRootPane().setDefaultButton(getBtnAccept());

		getContentPane().add(getPnButtons(), BorderLayout.SOUTH);
		getContentPane().add(getPnInformation(), BorderLayout.CENTER);

		localize();
		loadHelp();
	}

	/**
	 * It adds the available languages with its locale between parenthesis
	 */
	private void addLanguages() {
		languages.add("British (en_GB)");
		languages.add("American (en_US)");
		languages.add("Español (es)");
		Collections.sort(languages);
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
		hb.enableHelpKey(getRootPane(), "langu", hs);
	}

	/**
	 * It localizes the panel according to the locale pass as parameter
	 * 
	 * @param locale
	 */
	private void localize(Locale locale) {
//		this.texts = ResourceBundle.getBundle("uo/cpm/module/internationalization/messages", locale);
		this.texts = MainWindow.getResourceBundle(locale);
		localize();
	}

	/**
	 * It localizes the panel according to the Resource bundle texts that is an
	 * attribute
	 */
	private void localize() {
		lblLanguageImage
				.setIcon(new ImageIcon(MainWindow.class.getResource("/img/" + texts.getString("btnLanguage.image"))));

		setTitle(mw.getTitle() + texts.getString("cld.title"));

		lblLanguageImage.setToolTipText(texts.getString("lblImageLanguage.toolTip"));

		lblLanguage.setText(texts.getString("lblLanguage.text"));
		cbLanguage.setToolTipText(texts.getString("cbLanguage.toolTip"));
		lblLanguage.setDisplayedMnemonic((texts.getString("lblLanguage.mnemonic")).charAt(0));

		btnAccept.setText(texts.getString("btnAccept.text"));
		btnAccept.setToolTipText(texts.getString("btnAccept.toolTip"));

		btnCancel.setText(texts.getString("btnCancelChanges.text"));
		btnCancel.setToolTipText(texts.getString("btnCancelChanges.toolTip"));
//		btnCancel.setMnemonic((texts.getString("btnCancelChanges.mnemonic")).charAt(0));
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnButtons.add(getBtnAccept());
			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}

	private JPanel getPnInformation() {
		if (pnInformation == null) {
			pnInformation = new JPanel();
			pnInformation.setLayout(new BorderLayout(10, 0));
			pnInformation.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnInformation.add(getLblLanguageImage(), BorderLayout.WEST);
			pnInformation.add(getPnLanguage());
		}
		return pnInformation;
	}

	private JLabel getLblLanguageImage() {
		if (lblLanguageImage == null) {
			lblLanguageImage = new JLabel("");
			lblLanguageImage.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return lblLanguageImage;
	}

	private JPanel getPnLanguage() {
		if (pnLanguage == null) {
			pnLanguage = new JPanel();
			pnLanguage.setLayout(new BorderLayout(0, 5));
			pnLanguage.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnLanguage.add(getLblLanguage(), BorderLayout.NORTH);
			pnLanguage.add(getCbLanguage());
		}
		return pnLanguage;
	}

	private JLabel getLblLanguage() {
		if (lblLanguage == null) {
//			lblLanguage = new JLabel("Select the language:");
			lblLanguage = new JLabel();
			lblLanguage.setFont(new Font("Arial", Font.PLAIN, 12));
//			lblLanguage.setDisplayedMnemonic('S');
			lblLanguage.setLabelFor(getCbLanguage());
//			lblLanguage.setToolTipText("Select the language");
		}
		return lblLanguage;
	}

	private JComboBox<String> getCbLanguage() {
		if (cbLanguage == null) {
			cbLanguage = new JComboBox<String>();
			cbLanguage.setFont(new Font("Arial", Font.PLAIN, 12));
			cbLanguage.setModel(new DefaultComboBoxModel(languages.toArray()));

			setSelected();
			cbLanguage.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					localize(getLocaleFromSelectedLanguage()); // when a language is selected, the panel is localized
				}
			});
			cbLanguage.setBackground(MainWindow.COLOR_BACKGROUND);

		}
		return cbLanguage;
	}

	/**
	 * 
	 * @return the locale from the language selected by extracting it from the
	 *         string between parenthesis in each language
	 */
	private Locale getLocaleFromSelectedLanguage() {
		String option = (String) cbLanguage.getSelectedItem();

		int firstPos = -1;
		for (int i = 0; i < option.length(); i++) {
			if (option.substring(i, i + 1).equals("(")) {
				firstPos = i + 1;
				break;
			}
		}

		String locale = option.substring(firstPos, option.length() - 1);
		return new Locale(locale);
	}

	/**
	 * Sets the selected option in the comboBox as the language chosen by the main
	 * window
	 */
	private void setSelected() {
		String locale = "(" + texts.getString("locale") + ")";

		for (int i = 0; i < languages.size(); i++) {
			String option = languages.get(i);
			if (option.contains(locale)) {
				cbLanguage.setSelectedIndex(i);
				break;
			}

		}

	}

	private JButton getBtnAccept() {
		if (btnAccept == null) {
//			btnAccept = new JButton("Accept");
			btnAccept = new JButton();
			btnAccept.setFont(new Font("Arial", Font.BOLD, 12));
			btnAccept.setForeground(new Color(255, 255, 255));
			btnAccept.setBackground(MainWindow.COLOR_GREEN);

			btnAccept.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					changeLanguage();
				}
			});
//			btnAccept.setToolTipText("Accept the changes");

		}
		return btnAccept;
	}

	/**
	 * Changes the language of the main window and closes this window
	 */
	private void changeLanguage() {
		mw.localize(new Locale(texts.getString("locale")));
		dispose();
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
//			btnCancel = new JButton("Cancel");
			btnCancel = new JButton();
			btnCancel.setFont(new Font("Arial", Font.BOLD, 12));
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.setBackground(MainWindow.COLOR_RED);

			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
//			btnCancel.setMnemonic('C');
//			btnCancel.setToolTipText("Cancel the changes");
		}
		return btnCancel;
	}
}
