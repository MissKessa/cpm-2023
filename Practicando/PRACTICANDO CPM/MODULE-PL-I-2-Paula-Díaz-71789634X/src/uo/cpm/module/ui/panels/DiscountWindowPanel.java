package uo.cpm.module.ui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uo.cpm.module.service.Game;
import uo.cpm.module.ui.GameDialog;
import uo.cpm.module.ui.MainWindow;
import uo.cpm.module.util.NotSemiColonKeyListener;

/**
 * Panel showing the window indicating if a discount has been gained after the
 * game, and making it possible to save it. It has 2 buttons: Play again and
 * Save
 * 
 * @author paula
 *
 */
public class DiscountWindowPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * It's a reference to the game window
	 */
	private GameDialog gPn;

	/**
	 * It's a reference to the game
	 */
	private Game game;
	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;

	/**
	 * Focus listener that checks the id
	 */
	private CheckIdFocusListener fL = new CheckIdFocusListener();

	/**
	 * Panel containing the buttons Play again and Save
	 */
	private JPanel pnButtons;
	private JButton btnPlayAgain;
	private JButton btnSave;

	/**
	 * Panel with all the messages and information about the discount and the
	 * mechanism to save it
	 */
	private JPanel pnMessages;
	private JLabel lblGainDiscount;
	private JLabel lblBetterDiscount;

	private JPanel pnId;
	private JLabel lblId;
	private JTextField txtId;

	private JCheckBox chckbxLegalAge;
	private JLabel lblSavedDiscount;

	/**
	 * Creates the panel and localizes it
	 */
	public DiscountWindowPanel(MainWindow mw, GameDialog gPn) {
		this.mw = mw;
		this.gPn = gPn;
		this.game = gPn.getGame();

		setLayout(new BorderLayout(0, 0));
		setBackground(MainWindow.COLOR_BACKGROUND);

		add(getPnButtons(), BorderLayout.SOUTH);
		add(getPnMessages(), BorderLayout.CENTER);

		localize();
	}

	/**
	 * Localizes all the information by the locale chosen in the main window
	 */
	private void localize() {
		ResourceBundle texts = mw.getTexts();

		btnPlayAgain.setText(texts.getString("pnDiscount.btnPlayAgain.text"));
		btnPlayAgain.setToolTipText(texts.getString("pnDiscount.btnPlayAgain.toolTip"));
		btnPlayAgain.setMnemonic(texts.getString("pnDiscount.btnPlayAgain.mnemonic").charAt(0));

		btnSave.setText(texts.getString("pnDiscount.btnSave.text"));
		btnSave.setToolTipText(texts.getString("pnDiscount.btnSave.toolTip"));
		btnSave.setMnemonic(texts.getString("pnDiscount.btnSave.mnemonic").charAt(0));

		lblId.setText(texts.getString("pnDiscount.lblId.text"));
		txtId.setToolTipText(texts.getString("pnDiscount.txtId.toolTip"));
		lblId.setDisplayedMnemonic(texts.getString("pnDiscount.lblId.mnemonic").charAt(0));

		chckbxLegalAge.setText(texts.getString("pnDiscount.chckbxLegalAge.text"));
		chckbxLegalAge.setToolTipText(texts.getString("pnDiscount.chckbxLegalAge.toolTip"));
		chckbxLegalAge.setMnemonic(texts.getString("pnDiscount.chckbxLegalAge.mnemonic").charAt(0));
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnButtons.add(getBtnPlayAgain());
			pnButtons.add(getBtnSave());
		}
		return pnButtons;
	}

	private JButton getBtnPlayAgain() {
		if (btnPlayAgain == null) {
			btnPlayAgain = new JButton();
			btnPlayAgain.setFont(new Font("Arial", Font.BOLD, 12));
			btnPlayAgain.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					playAgain();
				}
			});
//			btnPlayAgain.setToolTipText("Play again");
			btnPlayAgain.setBackground(MainWindow.COLOR_BUTTONS);
			btnPlayAgain.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
		}
		return btnPlayAgain;
	}

	/**
	 * It makes sure that you don't want to save the discount if you didn't save it.
	 * If you save it or didn't gain a discount, the game is initialize again
	 */
	private void playAgain() {
		if (pnId.isVisible() && lblSavedDiscount.getText().isBlank())
			showNotSavedDiscountWindow(); // if you click play again and didn't save
		else
			gPn.initialize(); // if you click play again and you don't have a discount or you save it
	}

	/**
	 * Shows the window when the discount wasn't save and confirm that the user
	 * doesn't want to save it. If it doesn't want, it goes to the game panel with a
	 * new game. If it does, it keep in this panel (discount window panel)
	 */
	public void showNotSavedDiscountWindow() {
		ResourceBundle texts = mw.getTexts();

		if (JOptionPane.showConfirmDialog(this, texts.getString("pnDiscount.notSaved.message"),
				mw.getTitle() + ": " + texts.getString("pnDiscount.notSaved.title"), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
			gPn.initialize();
		}
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setFont(new Font("Arial", Font.BOLD, 12));
			btnSave.addFocusListener(fL);
			btnSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // saves the discount and sets the button disabled
					btnSave.setEnabled(false);
					txtId.setEditable(false);
					chckbxLegalAge.setEnabled(false);
					saveDiscount();
				}
			});
			btnSave.setEnabled(false);
//			btnSave.setMnemonic('S');
//			btnSave.setToolTipText("Save discount");

			btnSave.setBackground(MainWindow.COLOR_BUTTONS);
			btnSave.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
		}
		return btnSave;
	}

	/**
	 * Saves the discount if it doesn't have an already saved discount, and reloads
	 * the discounts in the service. It also notifies the user that it was saved
	 */
	private void saveDiscount() {
		ResourceBundle texts = mw.getTexts();
		if (!mw.getService().hasDiscount(txtId.getText())) {
//			game.saveDiscount(mw.getService(), txtId.getText());

			mw.getService().saveDiscount(game.getDiscount(txtId.getText()));
//			mw.getService().loadDiscounts();
			lblSavedDiscount.setText(texts.getString("pnDiscount.lblSavedDiscount.text"));

		} else {
			gPn.showAlreadySavedDiscountWindow();
		}

	}

	private JPanel getPnMessages() {
		if (pnMessages == null) {
			pnMessages = new JPanel();
			pnMessages.setLayout(new GridLayout(0, 1, 0, 10));
			pnMessages.setBackground(MainWindow.COLOR_BACKGROUND);

			pnMessages.add(getLblGainDiscount());
			pnMessages.add(getLblBetterDiscount());
			pnMessages.add(getPnId());
			pnMessages.add(getChckbxLegalAge());
			pnMessages.add(getLblSavedDiscount());
		}
		return pnMessages;
	}

	private JLabel getLblGainDiscount() {
		if (lblGainDiscount == null) {
			lblGainDiscount = new JLabel();
			lblGainDiscount.setFont(new Font("Arial", Font.ITALIC, 12));
//			lblGainDiscount.setText("You gain....");
			lblGainDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblGainDiscount.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblGainDiscount;
	}

	private JLabel getLblBetterDiscount() {
		if (lblBetterDiscount == null) {
			lblBetterDiscount = new JLabel();
			lblBetterDiscount.setFont(new Font("Arial", Font.ITALIC, 12));
			lblBetterDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBetterDiscount;
	}

	private JPanel getPnId() {
		if (pnId == null) {
			pnId = new JPanel();
			pnId.setLayout(new BorderLayout(0, 0));
			pnId.setBackground(MainWindow.COLOR_BACKGROUND);

			pnId.add(getLblId(), BorderLayout.WEST);
			pnId.add(getTxtId(), BorderLayout.CENTER);
		}
		return pnId;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel();
			lblId.setFont(new Font("Arial", Font.BOLD, 12));
//			lblId.setDisplayedMnemonic('I');
			lblId.setLabelFor(getTxtId());
		}
		return lblId;
	}

	private JTextField getTxtId() { // Checks that the id is correct
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setFont(new Font("Arial", Font.PLAIN, 12));
			txtId.addFocusListener(fL);
			txtId.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent e) {
					checkId();
				}
			});
			txtId.addKeyListener(new NotSemiColonKeyListener());
//			txtId.setToolTipText("The id to save the gained discount");
			txtId.setColumns(10);
		}
		return txtId;
	}

	private JCheckBox getChckbxLegalAge() {
		if (chckbxLegalAge == null) {
			chckbxLegalAge = new JCheckBox();
			chckbxLegalAge.setFont(new Font("Arial", Font.PLAIN, 12));
			chckbxLegalAge.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // checks the id is correct
					checkId();
				}
			});
//			chckbxLegalAge.setMnemonic('A');
		}
		return chckbxLegalAge;
	}

	/**
	 * Checks that the id is not blank, it has been indicated that the user has the
	 * legal age at least, and the discount wasn't saved before; to set the button
	 * Save enabled
	 */
	private void checkId() {
		if (txtId.getText().isBlank() || txtId.getText().isEmpty())
			btnSave.setEnabled(false);

		else if (!chckbxLegalAge.isSelected())
			btnSave.setEnabled(false);

		else if (lblSavedDiscount.getText().isBlank())
			btnSave.setEnabled(true);

		else
			btnSave.setEnabled(false);
	}

	private JLabel getLblSavedDiscount() {
		if (lblSavedDiscount == null) {
			lblSavedDiscount = new JLabel();
			lblSavedDiscount.setFont(new Font("Arial", Font.ITALIC, 12));
			lblSavedDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblSavedDiscount;
	}

	/**
	 * Sets the information of the panel depending if it has won or not, and the
	 * discount gained. It also localizes the information according to the locale
	 * chosen in the main window
	 */
	public void setInformation() {
		ResourceBundle texts = mw.getTexts();
		if (game.hasWonExtra25()) {
			lblGainDiscount.setText(texts.getString("pnDiscount.lblGainDiscount.text.EXTRA25"));
			lblBetterDiscount.setText("");

			setInformationWonDiscount();

			btnPlayAgain.setEnabled(false);

		} else if (game.hasWonExtra10()) {
			lblGainDiscount.setText(texts.getString("pnDiscount.lblGainDiscount.text.EXTRA10"));
			lblBetterDiscount.setText(texts.getString("pnDiscount.lblBetterDiscount.text.EXTRA10"));

			setInformationWonDiscount();

			btnPlayAgain.setEnabled(true);

		} else if (game.hasWonExtra5()) {
			lblGainDiscount.setText(texts.getString("pnDiscount.lblGainDiscount.text.EXTRA5"));
			lblBetterDiscount.setText(texts.getString("pnDiscount.lblBetterDiscount.text.EXTRA5"));

			setInformationWonDiscount();

			btnPlayAgain.setEnabled(true);
		} else {
			lblGainDiscount.setText(texts.getString("pnDiscount.lblGainDiscount.text.noDiscount"));
			lblBetterDiscount.setText(texts.getString("pnDiscount.lblBetterDiscount.text.noDiscount"));

			pnId.setVisible(false);

			chckbxLegalAge.setVisible(false);
			lblSavedDiscount.setVisible(false);

			btnPlayAgain.setEnabled(true);

		}
		chckbxLegalAge.setSelected(false);
		btnSave.setEnabled(false);

		localize();
	}

	/**
	 * Sets the information of the panel when it has won a discount
	 */
	private void setInformationWonDiscount() {
		pnId.setVisible(true);
		txtId.setText("");

		chckbxLegalAge.setVisible(true);
		chckbxLegalAge.setEnabled(true);
		txtId.setEnabled(true);
		txtId.setEditable(true);

		lblSavedDiscount.setVisible(true);
		lblSavedDiscount.setText("");

		btnPlayAgain.setEnabled(false);

	}

	/**
	 * 
	 * @return if the discount was saved (the panel to fill the information must be
	 *         visible and the user wasn't informed that it was save)
	 */
	public boolean hasBeenSaved() {
		if (pnId.isVisible() && !lblSavedDiscount.getText().isBlank())
			return true;
		else if (!pnId.isVisible())
			return true;
		return false;
	}

	/**
	 * Focus listener that checks the id when the component gains the focus
	 * 
	 * @author paula
	 *
	 */
	private class CheckIdFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent e) { // check the id
			checkId();
		}
	}
}
