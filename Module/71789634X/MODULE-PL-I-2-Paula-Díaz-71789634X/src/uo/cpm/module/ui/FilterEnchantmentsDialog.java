package uo.cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uo.cpm.module.model.TypeOfEnchantment;

/**
 * It's the window to filter the list of castles according of the enchantments
 * 
 * @author paula
 *
 */
public class FilterEnchantmentsDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * It's a reference of the main window
	 */
	private MainWindow mw;

	/**
	 * It's a reference to the Resource bundle use by the main window
	 */
	private ResourceBundle texts;

	/**
	 * It's the list of enchantments to filter the list of castles
	 */
	private List<TypeOfEnchantment> enchantmentFilter;

	/**
	 * Panel with all the checkbox to select the enchantments to filter
	 */
	private JPanel pnFilters;
//	private JCheckBox chckbxAp;
//	private JCheckBox chckbxOb;
//	private JCheckBox chckbxDe;
//	private JCheckBox chckbxEn;
//	private JCheckBox chckbxOl;
//	private JCheckBox chckbxRu;
	private JLabel lblFiltersInfo;

	/**
	 * Panel with all the buttons: btnSelectAll, btnUnselectAll, btnAccept and
	 * btnCancel
	 */
	private JPanel pnButtons;

	/**
	 * Panel with the buttons btnSelectAll and btnUnselectAll
	 */
	private JPanel pnButtonsFilters;
	private JButton btnSelectAll;
	private JButton btnUnselectAll;

	/**
	 * Panel with the buttons btnAccept and btnCancel
	 */
	private JPanel pnWindowButtons;
	private JButton btnAccept;
	private JButton btnCancel;

	/**
	 * Create the dialog: it sets the enchantmentFilter with the one of the service,
	 * sets btnAccept as default button and localizes the panel
	 * 
	 * @param mw is a reference to the main window
	 */
	public FilterEnchantmentsDialog(MainWindow mw) {
		this.mw = mw;
		this.texts = mw.getTexts();
		this.enchantmentFilter = mw.getService().getEnchantmentFilter();

//		addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent e) {
//				System.out.println(((JDialog) e.getSource()).getWidth());
//				System.out.println(((JDialog) e.getSource()).getHeight());
//			}
//		});
		setResizable(false);
		setMinimumSize(new Dimension(600, 200));

		setIconImage(mw.getIconImage());
		setBounds(100, 100, 600, 200);
		getContentPane().setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		setSize(new Dimension(600, 200));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		getContentPane().add(getPnFilters());
		getContentPane().add(getPnButtons(), BorderLayout.SOUTH);
		getContentPane().add(getLblFiltersInfo(), BorderLayout.NORTH);

		this.getRootPane().setDefaultButton(getBtnAccept());

		localize();
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
		hb.enableHelpKey(getRootPane(), "filter", hs);
	}

	/**
	 * Localizes the panel according to the locale chosen in the main window
	 */
	private void localize() {
		setTitle(mw.getTitle() + texts.getString("fed.title"));

		btnSelectAll.setText(texts.getString("btnSelectAll.text"));
		btnSelectAll.setToolTipText(texts.getString("btnSelectAll.toolTip"));
		btnSelectAll.setMnemonic(texts.getString("btnSelectAll.mnemonic").charAt(0));

		btnUnselectAll.setText(texts.getString("btnUnselectAll.text"));
		btnUnselectAll.setToolTipText(texts.getString("btnUnselectAll.toolTip"));
		btnUnselectAll.setMnemonic(texts.getString("btnUnselectAll.mnemonic").charAt(0));

		btnAccept.setText(texts.getString("btnAccept.text"));
		btnAccept.setToolTipText(texts.getString("btnAccept.toolTip"));

		btnCancel.setText(texts.getString("btnCancelChanges.text"));
		btnCancel.setToolTipText(texts.getString("btnCancelChanges.toolTip"));
//		btnCancel.setMnemonic(texts.getString("btnCancelChanges.mnemonic").charAt(0));

		TypeOfEnchantment[] enchantments = TypeOfEnchantment.values();
		for (int i = 0; i < enchantments.length; i++) {
			JCheckBox chckbox = ((JCheckBox) pnFilters.getComponent(i));
			chckbox.setText(enchantments[i] + " - " + texts.getString("chckbx" + enchantments[i] + ".text"));
			chckbox.setToolTipText(texts.getString("chckbx" + enchantments[i] + ".toolTip"));
			chckbox.setMnemonic(texts.getString("chckbx" + enchantments[i] + ".mnemonic").charAt(0));
		}
//		chckbxAp.setText("Ap - " + texts.getString("chckbxAp.text"));
//		chckbxAp.setToolTipText(texts.getString("chckbxAp.toolTip"));
//
//		chckbxOb.setText("Ob - " + texts.getString("chckbxOb.text"));
//		chckbxOb.setToolTipText(texts.getString("chckbxOb.toolTip"));
//
//		chckbxDe.setText("De - " + texts.getString("chckbxDe.text"));
//		chckbxDe.setToolTipText(texts.getString("chckbxDe.toolTip"));
//
//		chckbxEn.setText("En - " + texts.getString("chckbxEn.text"));
//		chckbxEn.setToolTipText(texts.getString("chckbxEn.toolTip"));
//
//		chckbxOl.setText("Ol - " + texts.getString("chckbxOl.text"));
//		chckbxOl.setToolTipText(texts.getString("chckbxOl.toolTip"));
//
//		chckbxRu.setText("Ru - " + texts.getString("chckbxRu.text"));
//		chckbxRu.setToolTipText(texts.getString("chckbxRu.toolTip"));

		lblFiltersInfo.setText(texts.getString("lblFiltersInfo.text"));
	}

	private JPanel getPnFilters() {
		if (pnFilters == null) {
			pnFilters = new JPanel();
			pnFilters.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			int divisions = TypeOfEnchantment.values().length / 2;
			if (divisions < 1)
				pnFilters.setLayout(new GridLayout(1, 0, 0, 0));
			else
				pnFilters.setLayout(new GridLayout(divisions, 0, 0, 0));

			addCheckBoxes();
//			pnFilters.add(getChckbxAp());
//			pnFilters.add(getChckbxDe());
//			pnFilters.add(getChckbxEn());
//			pnFilters.add(getChckbxOb());
//			pnFilters.add(getChckbxOl());
//			pnFilters.add(getChckbxRu());
		}
		return pnFilters;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setLayout(new BorderLayout(0, 0));
			pnButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnButtons.add(getPnButtonsFilters(), BorderLayout.CENTER);
			pnButtons.add(getPnWindowButtons(), BorderLayout.EAST);
		}
		return pnButtons;
	}

	private JPanel getPnButtonsFilters() {
		if (pnButtonsFilters == null) {
			pnButtonsFilters = new JPanel();
			pnButtonsFilters.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnButtonsFilters.add(getBtnSelectAll());
			pnButtonsFilters.add(getBtnUnselectAll());
		}
		return pnButtonsFilters;
	}

	private JButton getBtnSelectAll() {
		if (btnSelectAll == null) {
//			btnSelectAll = new JButton("Select all filters");
			btnSelectAll = new JButton();
			btnSelectAll.setFont(new Font("Arial", Font.BOLD, 12));

			btnSelectAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					markAllCheckBoxes(true);
				}
			});
//			btnSelectAll.setToolTipText("Select all filters");
//			btnSelectAll.setMnemonic('S');
		}
		return btnSelectAll;
	}

	/**
	 * Marks all the checkboxes of the pnFilter according to the given value
	 * 
	 * @param value
	 */
	private void markAllCheckBoxes(boolean value) {
		for (Component checkBox : getPnFilters().getComponents()) {
			((JCheckBox) checkBox).setSelected(value);
		}
	}

	private JButton getBtnUnselectAll() {
		if (btnUnselectAll == null) {
//			btnUnselectAll = new JButton("Unselect all filters");
			btnUnselectAll = new JButton();
			btnUnselectAll.setFont(new Font("Arial", Font.BOLD, 12));

			btnUnselectAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					markAllCheckBoxes(false);
				}
			});
//			btnUnselectAll.setToolTipText("Unselect all filters");
//			btnUnselectAll.setMnemonic('U');
		}
		return btnUnselectAll;
	}

	private JPanel getPnWindowButtons() {
		if (pnWindowButtons == null) {
			pnWindowButtons = new JPanel();
			pnWindowButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnWindowButtons.add(getBtnAccept());
			pnWindowButtons.add(getBtnCancel());
		}
		return pnWindowButtons;
	}

	private JButton getBtnAccept() {
		if (btnAccept == null) {
//			btnAccept = new JButton("Accept");
			btnAccept = new JButton();
			btnAccept.setFont(new Font("Arial", Font.PLAIN, 12));
			btnAccept.setForeground(new Color(255, 255, 255));
			btnAccept.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					acceptFilter();
				}
			});
			btnAccept.setBackground(MainWindow.COLOR_GREEN);
//			btnAccept.setToolTipText("Accept");
		}
		return btnAccept;
	}

	/**
	 * Accepts the filter, closes the window and updates the castle list in the main
	 * window
	 */
	private void acceptFilter() {
		setEnchantmentFilter();
		dispose();
		mw.updateCastleList();
	}

	/**
	 * Sets the enchantment filter accoridng to the checkbox in pnFilter that are
	 * selected
	 */
	private void setEnchantmentFilter() {
		mw.getService().removeAllEnchanmentToFilter();

		for (Component checkBox : getPnFilters().getComponents()) {
			if (((JCheckBox) checkBox).isSelected()) {
				mw.getService().addEnchanmentToFilter(
						TypeOfEnchantment.valueOf(((JCheckBox) checkBox).getText().substring(0, 2)));
			}
		}
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
//			btnCancel = new JButton("Cancel");
			btnCancel = new JButton();
			btnCancel.setFont(new Font("Arial", Font.PLAIN, 12));
			btnCancel.setForeground(new Color(255, 255, 255));
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
//			btnCancel.setToolTipText("Cancel");
			btnCancel.setBackground(MainWindow.COLOR_RED);
		}
		return btnCancel;
	}

	private JLabel getLblFiltersInfo() {
		if (lblFiltersInfo == null) {
			lblFiltersInfo = new JLabel();
			lblFiltersInfo.setFont(new Font("Arial", Font.PLAIN, 12));
			// lblFiltersInfo = new JLabel("Select the filters that are going to be
			// applied:");
			lblFiltersInfo.setLabelFor(getPnFilters());
			lblFiltersInfo.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return lblFiltersInfo;
	}

	/**
	 * Adds the check boxes of the different types of enchantments in the pnFilter
	 * with the corresponding value according to the enchantmentFilter
	 */
	private void addCheckBoxes() {
		pnFilters.removeAll();
//		enchantmentFilter = mw.getService().getEnchantmentFilter();

		TypeOfEnchantment[] enchantments = TypeOfEnchantment.values();

		for (TypeOfEnchantment enchantment : enchantments) {
			JCheckBox checkbox = new JCheckBox();
			checkbox.setFont(new Font("Arial", Font.PLAIN, 12));

			if (enchantmentFilter.contains(enchantment))
				checkbox.setSelected(true);

			pnFilters.add(checkbox);
		}
	}

//	private JCheckBox getChckbxAp() {
//		if (chckbxAp == null) {
//			chckbxAp = new JCheckBox();
//			chckbxAp.setFont(new Font("Arial", Font.PLAIN, 12));
////			chckbxAp = new JCheckBox("Ap - Apparitions of Ghosts");
////			chckbxAp.setToolTipText("Apparitions of Ghosts");
//			chckbxAp.setMnemonic('A');
//			if (enchantmentFilter.contains(TypeOfEnchantment.Ap)) {
//				chckbxAp.setSelected(true);
//			}
//		}
//		return chckbxAp;
//	}
//
//	private JCheckBox getChckbxOb() {
//		if (chckbxOb == null) {
//			chckbxOb = new JCheckBox();
//			chckbxOb.setFont(new Font("Arial", Font.PLAIN, 12));
////			chckbxOb = new JCheckBox("Ob - Moving objects");
////			chckbxOb.setToolTipText("Moving objects");
//			chckbxOb.setMnemonic('O');
//			if (enchantmentFilter.contains(TypeOfEnchantment.Ob)) {
//				chckbxOb.setSelected(true);
//			}
//		}
//		return chckbxOb;
//	}
//
//	private JCheckBox getChckbxDe() {
//		if (chckbxDe == null) {
//			chckbxDe = new JCheckBox();
//			chckbxDe.setFont(new Font("Arial", Font.PLAIN, 12));
////			chckbxDe = new JCheckBox("De - Drop in temperature");
////			chckbxDe.setToolTipText("Drop in temperature");
//			chckbxDe.setMnemonic('D');
//			if (enchantmentFilter.contains(TypeOfEnchantment.De)) {
//				chckbxDe.setSelected(true);
//			}
//		}
//		return chckbxDe;
//	}
//
//	private JCheckBox getChckbxEn() {
//		if (chckbxEn == null) {
//			chckbxEn = new JCheckBox();
//			chckbxEn.setFont(new Font("Arial", Font.PLAIN, 12));
////			chckbxEn = new JCheckBox("En - Lights blinking");
////			chckbxEn.setToolTipText("Lights blinking");
//			chckbxEn.setMnemonic('E');
//			if (enchantmentFilter.contains(TypeOfEnchantment.En)) {
//				chckbxEn.setSelected(true);
//			}
//		}
//		return chckbxEn;
//	}
//
//	private JCheckBox getChckbxOl() {
//		if (chckbxOl == null) {
//			chckbxOl = new JCheckBox();
//			chckbxOl.setFont(new Font("Arial", Font.PLAIN, 12));
////			chckbxOl = new JCheckBox("Ol - Nauseous smells");
////			chckbxOl.setToolTipText("Nauseous smells");
//			chckbxOl.setMnemonic('O');
//			if (enchantmentFilter.contains(TypeOfEnchantment.Ol)) {
//				chckbxOl.setSelected(true);
//			}
//		}
//		return chckbxOl;
//	}
//
//	private JCheckBox getChckbxRu() {
//		if (chckbxRu == null) {
//			chckbxRu = new JCheckBox();
//			chckbxRu.setFont(new Font("Arial", Font.PLAIN, 12));
////			chckbxRu = new JCheckBox("Ru -  Strange noises");
////			chckbxRu.setToolTipText("Strange noises");
//			chckbxRu.setMnemonic('R');
//			if (enchantmentFilter.contains(TypeOfEnchantment.Ru)) {
//				chckbxRu.setSelected(true);
//			}
//		}
//		return chckbxRu;
//	}

}
