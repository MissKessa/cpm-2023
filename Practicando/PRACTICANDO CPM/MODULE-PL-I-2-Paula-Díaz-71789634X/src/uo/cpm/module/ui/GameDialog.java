package uo.cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import uo.cpm.module.model.Dice;
import uo.cpm.module.model.TypeEntity;
import uo.cpm.module.player.MusicPlayer;
import uo.cpm.module.service.Game;
import uo.cpm.module.ui.panels.DiscountWindowPanel;

/**
 * Window for the game. It has several views: pnGame (for playing) and
 * pnDiscount (when you end the game, and saving the discount gained).
 * 
 * @author paula
 *
 */
public class GameDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * It's a reference to the game
	 */
	private Game game;

	/**
	 * Panel representing the board of the game
	 */
	private JPanel pnBoard;

	/**
	 * Panel containing the dice, its value and the number of remaining throws; and
	 * the button for Help
	 */
	private JPanel pnHeader;

	/**
	 * Panel containing the dice and its value
	 */
	private JPanel pnDice;
	private JButton btnDice;
	private JTextField txtValueDice;

	/**
	 * Panel containing the remaining throws
	 */
	private JPanel pnThrows;
	private JLabel lblThrows;
	private JTextField txtThrows;

	private JButton btnHelp;

	/**
	 * Panel for the cardLayout showing the game. It has a dice and it shows its
	 * value and the number of remaining throws, a button for Help and the board of
	 * the game
	 */
	private JPanel pnGame;

	/**
	 * Panel for the cardLayout for saving the discount gained after the game ends.
	 */
	private DiscountWindowPanel pnDiscount;

	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;

	/**
	 * Action performed listener for moving the ghostbuster
	 */
	private MoveGhostbuster moveListener = new MoveGhostbuster();

	/**
	 * It's the player for playing music
	 */
	private MusicPlayer player = new MusicPlayer();
	/**
	 * It's the thread that plays the music continuosly
	 */
	private PlayerThread thread = new PlayerThread();

	/**
	 * Create the dialog: it creates a new game, and localizes the pnGame
	 * 
	 * @param mw
	 */
	public GameDialog(MainWindow mw) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindowDependingOnSituation();
			}
		});
		player.setVolume(50, 100);
		thread.start();
		this.mw = mw;
		game = new Game();

//		System.out.println(new File("player/Music game.mp3").exists());

//		thread.(player.play());
		;

		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logo.png")));
		setResizable(true);
		setBounds(100, 100, 450, 300);
		setMinimumSize(new Dimension(700, 700));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		setBackground(MainWindow.COLOR_BACKGROUND);

		getContentPane().add(getPnGame(), "pnGame");
		getContentPane().add(getPnDiscount(), "pnDiscount");

		localize();
		loadHelp();
	}

	/**
	 * It's the thread that keeps the music playing depending on the variable
	 * keepPlaying
	 * 
	 * @author paula
	 *
	 */
	private class PlayerThread extends Thread {
		/**
		 * It's the variable that stops the thread to play the music again
		 */
		private boolean keepPlaying = true;

		@Override
		public void run() {
			while (keepPlaying) {
				player.play();
				try {
					sleep(7200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		public void setKeepPlaying(boolean keep) {
			keepPlaying = keep;
		}
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
		hb.enableHelpKey(getRootPane(), "play", hs);
		hb.enableHelpKey(pnGame, "play", hs); // allow access help support by F1 in this Frame (you need to do
												// it in every dialog)
		hb.enableHelpOnButton(getBtnHelp(), "play", hs); // allow access help support by the button on the
															// menu
		hb.enableHelp(getPnDiscount(), "save", hs);
	}

	/**
	 * When this window is closing, it shows a exit window depending on the
	 * situation. If the discount gained hasn't been saved, the not save discount
	 * window is shown. Else it will show a normal exit window
	 */
	private void closeWindowDependingOnSituation() {
		if (pnDiscount.isVisible() && !pnDiscount.hasBeenSaved())
			pnDiscount.showNotSavedDiscountWindow();
		else
			checkExit();
	}

	/**
	 * It shows a normal exit window to go back to the main window when all the game
	 * process has finished
	 */
	private void checkExit() {
		ResourceBundle texts = mw.getTexts();

		if ((JOptionPane.showConfirmDialog(this, texts.getString("gPn.exit.message"),
				mw.getTitle() + ": " + texts.getString("gPn.exit.title"), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE)) == JOptionPane.YES_OPTION) {

			dispose();
			mw.showPnMainWindowAfterGame();
			thread.setKeepPlaying(false);
//			keepPlaying = false;
			player.stop();
		}
	}

	/**
	 * It localizes the pnGame panel according to the locale chosen in the main
	 * window
	 */
	private void localize() {
		ResourceBundle texts = mw.getTexts();
		setTitle(mw.getTitle() + ": " + texts.getString("gPn.title"));

		pnBoard.setToolTipText(texts.getString("gPn.pnBoard.toolTip"));

		btnHelp.setText(texts.getString("gPn.btnHelp.text"));
		btnHelp.setToolTipText(texts.getString("gPn.btnHelp.toolTip"));
//		btnHelp.setMnemonic(texts.getString("gPn.btnHelp.mnemonic").charAt(0));

		btnDice.setText(texts.getString("gPn.btnDice.text"));
		btnDice.setToolTipText(texts.getString("gPn.btnDice.toolTip"));
		btnDice.setMnemonic(texts.getString("gPn.btnDice.mnemonic").charAt(0));

		txtValueDice.setToolTipText(texts.getString("gPn.txtValueDice.toolTip"));

		lblThrows.setText(texts.getString("gPn.lblThrows.text"));
		txtThrows.setToolTipText(texts.getString("gPn.txtThrows.toolTip"));

		for (int i = 0; i < Game.NUMBER_OF_ROWS; i++) {
			for (int j = 0; j < Game.NUMBER_OF_COLUMNS; j++) {
				TypeEntity type = game.getType(i, j);
				if (type != null) {
					((JButton) (pnBoard.getComponent(7 * i + j))).setToolTipText(texts.getString("gPn." + type));
				}
			}
		}

	}

	/**
	 * It localizes the pnGame and shows it
	 */
	public void showPnGame() {

		localize();
		((CardLayout) getContentPane().getLayout()).show(getContentPane(), "pnGame");
	}

	/**
	 * It sets the information of the pnDiscount and shows it
	 */
	public void showPnDiscount() {
		thread.setKeepPlaying(false);
//		keepPlaying = false;
		player.stop();
		pnDiscount.setInformation();
		((CardLayout) getContentPane().getLayout()).show(getContentPane(), "pnDiscount");
	}

	private JPanel getPnGame() {
		if (pnGame == null) {
			pnGame = new JPanel();
			pnGame.setLayout(new BorderLayout(10, 10));
			pnGame.setBackground(MainWindow.COLOR_BACKGROUND);

			pnGame.add(getPnHeader(), BorderLayout.NORTH);
			pnGame.add(getPnBoard(), BorderLayout.CENTER);
		}
		return pnGame;
	}

	private JPanel getPnDiscount() {
		if (pnDiscount == null) {
			pnDiscount = new DiscountWindowPanel(mw, this);
		}
		return pnDiscount;
	}

	private JPanel getPnHeader() {
		if (pnHeader == null) {
			pnHeader = new JPanel();
			pnHeader.setLayout(new BorderLayout(10, 0));
			pnHeader.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnHeader.add(getBtnHelp(), BorderLayout.EAST);
			pnHeader.add(getPnThrows(), BorderLayout.CENTER);
			pnHeader.add(getPnDice(), BorderLayout.WEST);
		}
		return pnHeader;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
//			pnBoard.setToolTipText("board");
			pnBoard.setLayout(new GridLayout(5, 0, 5, 5));
			pnBoard.setBackground(MainWindow.COLOR_BACKGROUND);

			addBoardButtons();
		}
		return pnBoard;
	}

	private JButton getBtnHelp() {
		if (btnHelp == null) {
			btnHelp = new JButton();
			btnHelp.setFont(new Font("Arial", Font.BOLD, 12));
//			btnHelp.setToolTipText("Help game");
//			btnHelp.setMnemonic('?');
			btnHelp.setBackground(MainWindow.COLOR_BUTTONS);
			btnHelp.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
		}
		return btnHelp;
	}

	private JPanel getPnThrows() {
		if (pnThrows == null) {
			pnThrows = new JPanel();
			pnThrows.setLayout(new BorderLayout(5, 0));
			pnThrows.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnThrows.add(getLblThrows(), BorderLayout.WEST);
			pnThrows.add(getTxtThrows(), BorderLayout.CENTER);
		}
		return pnThrows;
	}

	private JPanel getPnDice() {
		if (pnDice == null) {
			pnDice = new JPanel();
			pnDice.setLayout(new BorderLayout(0, 5));
			pnDice.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnDice.add(getBtnDice());
			pnDice.add(getTxtValueDice(), BorderLayout.SOUTH);
		}
		return pnDice;
	}

	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton();
			btnDice.setFont(new Font("Arial", Font.BOLD, 12));
			btnDice.setBackground(MainWindow.COLOR_BUTTONS);
			btnDice.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
			btnDice.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					throwDice();
				}
			});
//			btnDice.setMnemonic('D');
//			btnDice.setToolTipText("Dice");
		}
		return btnDice;
	}

	/**
	 * Throws the dice: sets the dice disabled, updates the remaining throws, throws
	 * the dice, and enables all ghostbusters
	 */
	private void throwDice() {
		btnDice.setEnabled(false);
		game.increaseNumberOfThrowsMade();
		txtThrows.setText("" + game.getRemainingThrows());
		int dice = Dice.launch();
		txtValueDice.setText("" + dice);

//		disabledAllSquares();
		enableGhostBusters();

	}

	/**
	 * Disables all the squares of the board
	 */
	private void disabledAllSquares() {
		for (Component component : pnBoard.getComponents()) {
			((JButton) component).setEnabled(false);
		}
	}

	/**
	 * Enables all the ghostbusters by going through the board and checking the type
	 * of entity in the square. If it's not a ghostbuster, it disables the button
	 */
	private void enableGhostBusters() {
		for (int k = 0; k < pnBoard.getComponentCount(); k++) {
			JButton button = ((JButton) pnBoard.getComponent(k));
			int i = k / Game.NUMBER_OF_COLUMNS;
			int j = k % Game.NUMBER_OF_COLUMNS;

			if (TypeEntity.ghostbuster.equals(game.getType(i, j))) {
				button.setEnabled(true);
				button.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			} else {
				button.setEnabled(false);
				button.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			}
		}
	}

	private JTextField getTxtValueDice() {
		if (txtValueDice == null) {
			txtValueDice = new JTextField();
			txtValueDice.setFont(new Font("Arial", Font.PLAIN, 12));
			txtValueDice.setHorizontalAlignment(SwingConstants.CENTER);
//			txtValueDice.setToolTipText("Value of dice");
			txtValueDice.setEditable(false);
			txtValueDice.setColumns(10);
			txtValueDice.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return txtValueDice;
	}

	private JLabel getLblThrows() {
		if (lblThrows == null) {
			lblThrows = new JLabel();
			lblThrows.setFont(new Font("Arial", Font.BOLD, 12));
			lblThrows.setLabelFor(getTxtThrows());
		}
		return lblThrows;
	}

	private JTextField getTxtThrows() {
		if (txtThrows == null) {
			txtThrows = new JTextField();
			txtThrows.setFont(new Font("Arial", Font.PLAIN, 12));
			txtThrows.setEditable(false);
			txtThrows.setColumns(10);
			txtThrows.setText("" + game.getRemainingThrows());
			txtThrows.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return txtThrows;
	}

	/**
	 * Adds all the buttons according to the board of the game: they are set
	 * disabled; the action command is set according to their position; and the
	 * image according to the entity in the square
	 */
	private void addBoardButtons() {
		pnBoard.removeAll();
		for (int k = 0; k < Game.NUMBER_OF_ROWS * Game.NUMBER_OF_COLUMNS; k++) {
			JButton button = new JButton();
			button.setBackground(MainWindow.COLOR_BACKGROUND);

			int i = k / Game.NUMBER_OF_COLUMNS;
			int j = k % Game.NUMBER_OF_COLUMNS;
			button.setActionCommand("" + k);
			button.setBackground(Color.WHITE);

			try {
				Image image = (new ImageIcon((Game.class.getResource("/img/" + game.getImage(i, j)))).getImage()
						.getScaledInstance(80, 80, Image.SCALE_FAST));
				button.setIcon(new ImageIcon(image));
				button.setDisabledIcon(new ImageIcon(image));
			} catch (NullPointerException e) {
				button.setEnabled(false);
			}

			button.addActionListener(moveListener);

			button.setEnabled(false);

			pnBoard.add(button);
		}

	}

	/**
	 * Moves the ghosbuster in the button with that actionCommand: it swaps their
	 * positions (image and tooltip). Then it disables all the squares as the move
	 * has been done, and then the board is repainted
	 * 
	 * @param actionCommand
	 */
	private void moveGhostbuster(String actionCommand) {
		int k = Integer.valueOf(actionCommand);

		int iGb = k / Game.NUMBER_OF_COLUMNS; // ghostbuster
		int j = k % Game.NUMBER_OF_COLUMNS;

		int iG = iGb - Integer.valueOf(txtValueDice.getText()); // ghost

		if (game.swap(iGb, iG, j)) {
			JButton bGb = (JButton) pnBoard.getComponent(k);
			JButton bG = (JButton) pnBoard.getComponent(Game.NUMBER_OF_COLUMNS * iG + j);

			bG.setIcon(bGb.getIcon());
			bG.setDisabledIcon(bGb.getIcon());
			bG.setToolTipText(bGb.getToolTipText());

			bGb.setToolTipText(null);
			bGb.setDisabledIcon(null);
			bGb.setIcon(null);
		}

		disabledAllSquares();

		repaint();
	}

	/**
	 * Action listener for moving the ghostbuster when they are clicked, then the
	 * dice is enabled and if the game has finish it shows the pnDiscount
	 * 
	 * @author paula
	 *
	 */
	private class MoveGhostbuster implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			moveGhostbuster(((JButton) (e.getSource())).getActionCommand());
			btnDice.setEnabled(true);
			txtValueDice.setText("");

			if (game.hasFinish())
				showPnDiscount();
		}

	}

	/**
	 * 
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * Window shown when the user has already a discount associated with the id. It
	 * closes the window and it shows the main window
	 */
	public void showAlreadySavedDiscountWindow() {
		ResourceBundle texts = mw.getTexts();
		JOptionPane.showMessageDialog(this, texts.getString("gPn.alreadySaved.text"),
				mw.getTitle() + ": " + texts.getString("gPn.alreadySaved.title"), JOptionPane.WARNING_MESSAGE);
		dispose();
		mw.showPnMainWindowAfterGame();

	}

	/**
	 * It initializes the pnGame: initializes the game, adds the board buttons, sets
	 * the dice enabled and shows the pnGame
	 */
	public void initialize() {
		game.initialize();
//		keepPlaying = true;
//		thread.setKeepPlaying(true);
		thread = new PlayerThread();
		thread.start();
		addBoardButtons();
		btnDice.setEnabled(true);
		txtValueDice.setText("");
		txtThrows.setText("" + game.getRemainingThrows());
		showPnGame();

	}
}
