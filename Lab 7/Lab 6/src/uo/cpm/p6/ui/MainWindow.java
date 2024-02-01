package uo.cpm.p6.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.rules.Game;
import uo.cpm.p6.rules.Game.Level;
import uo.cpm.p6.service.SpaceInvaders;

public class MainWindow extends JFrame {

	private SpaceInvaders game = null;
	private JButton btnDice;
	private JLabel lblSpaceShip;
	private JLabel lblScore;
	private JTextField txtScore;
	private JLabel lblEarth;
	private JPanel pnShots;
	private JPanel pnBoard;
	private MyButtonListener mBL = new MyButtonListener();
	private JButton btnNewButton;
	private JMenuBar menuBar;
	private JMenu mnGame;
	private JMenu mnHelp;
	private JMenuItem mntmNewGame;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmContents;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;
	private JMenu mnOptions;
	private JMenuItem mntmEasy;
	private JMenuItem mntmIntermediate;
	private JMenuItem mntmHard;

	public MainWindow(SpaceInvaders game) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (checkExit()) {
					System.exit(0);
				}
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/invader.jpg")));
		setResizable(false);
		setTitle("Space Invasion!!");
		this.game = game;

		setBounds(100, 100, 1045, 447);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnDice());
		getContentPane().add(getLblSpaceShip());
		getContentPane().add(getLblScore());
		getContentPane().add(getTxtScore());
		getContentPane().add(getLblEarth());
		getContentPane().add(getPnShots());
		getContentPane().add(getPnBoard());
		setJMenuBar(getMenuBar_1());

		prepareBoard(Game.Level.INTERMEDIATE);
		// enableBoard(false);

	}

	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("");
			btnDice.setMnemonic('D');
			btnDice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initGame();
				}
			});
			btnDice.setDisabledIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setBorderPainted(false);

			btnDice.setIcon(new ImageIcon(MainWindow.class.getResource("/img/dice.jpg")));
			btnDice.setBounds(26, 11, 116, 106);
		}
		return btnDice;
	}

	private JLabel getLblSpaceShip() {
		if (lblSpaceShip == null) {
			lblSpaceShip = new JLabel("");
			lblSpaceShip.setIcon(new ImageIcon(MainWindow.class.getResource("/img/spaceship.png")));
			lblSpaceShip.setBounds(358, 32, 137, 74);
		}
		return lblSpaceShip;
	}

	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score");
			lblScore.setForeground(Color.WHITE);
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblScore.setBounds(574, 32, 55, 41);
		}
		return lblScore;
	}

	private JTextField getTxtScore() {
		if (txtScore == null) {
			txtScore = new JTextField();
			txtScore.setFont(new Font("Tahoma", Font.BOLD, 15));
			txtScore.setHorizontalAlignment(SwingConstants.CENTER);
			txtScore.setText("" + game.getScore());
			txtScore.setForeground(Color.GREEN);
			txtScore.setEditable(false);
			txtScore.setBounds(560, 72, 86, 20);
			txtScore.setColumns(10);
		}
		return txtScore;
	}

	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setIcon(new ImageIcon(MainWindow.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(714, 11, 193, 181);
		}
		return lblEarth;
	}

	private JPanel getPnShots() {
		if (pnShots == null) {
			pnShots = new JPanel();
			pnShots.setBackground(Color.BLACK);
			pnShots.setBounds(216, 118, 430, 74);
		}
		return pnShots;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBackground(new Color(0, 0, 0));
			pnBoard.setBorder(new LineBorder(new Color(0, 255, 0), 5));
			pnBoard.setBounds(391, 244, 73, 106);
			// pnBoard.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnBoard;
	}

	// Here it starts the presentation logic
	private void enableBoard(boolean state) {
		for (Component button : pnBoard.getComponents()) {
			button.setEnabled(state); // we don't need a cast because the setEnabled is a method of Component
		}
	}

	private JLabel newShot() {
		JLabel newShot = new JLabel();
		newShot.setIcon(ImageFactory.loadImage("/img/shoot.png"));
		newShot.setBorder(new LineBorder(new Color(0, 255, 0), 2)); // same border as for buttons
		return newShot;
	}

	private void paintShots() {
		for (int i = 0; i < game.getShots(); i++) {
			getPnShots().add(newShot());
		}
		validate(); // to update mainWindow as we are changing the JFram behind the scenes. If not,
					// it wouldn't been seen until the user makes some changes
	}

	private void initGame() {
		game.launch();
		paintShots();
		btnDice.setEnabled(false);
		enableBoard(true);
	}

	private void removeShot() {
		getPnShots().remove(0);
		getPnShots().repaint(); // refresh/update visually the panel because we are removing elements behind the
								// scenes
	}

	private void paintCell(Integer position) {
		ImageIcon image = ImageFactory.loadImage(game.getBoard().getCells()[position].getPicture()); // asking internal
																										// board of the
																										// name of the
																										// picture
																										// according to
																										// the state of
																										// the cell
		((JButton) pnBoard.getComponent(position)).setIcon(image);
		((JButton) pnBoard.getComponent(position)).setDisabledIcon(image);
	}

	private void updateStateOfTheGame(Integer position) {
		getTxtScore().setText(String.valueOf(game.getScore()));
		removeShot();
		paintCell(position);
		if (game.isGameOver()) {
			getPnShots().removeAll();
			getPnShots().repaint();
			discoverBoard();
			JOptionPane.showMessageDialog(this, getFinalMessage(), "Space Invasion!", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void discoverBoard() {
		for (int i = 0; i < pnBoard.getComponentCount(); i++) {
			paintCell(i);
			getPnBoard().getComponent(i).setEnabled(false);
		}
		getPnBoard().repaint();
		getPnBoard().setEnabled(false);

	}

	private String getFinalMessage() {
		return game.getFinalMessage();
	}

	private void shoot(Integer position) {
		game.shoot(position);
		updateStateOfTheGame(position);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnGame());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnGame() {
		if (mnGame == null) {
			mnGame = new JMenu("Game");
			mnGame.setMnemonic('G');
			mnGame.add(getMntmNewGame());
			mnGame.add(getSeparator());
			mnGame.add(getMntmExit());
		}
		return mnGame;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmNewGame() {
		if (mntmNewGame == null) {
			mntmNewGame = new JMenuItem("New game");
			mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			mntmNewGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkNewGame())
						initialize(game.getLevel());
				}

			});
		}
		return mntmNewGame;
	}

	private void prepareBoard(Level level) {
		int width = 0;
		int dim = 0;
		switch (level) {
		case EASY: {
			width = 1010;
			dim = Board.DIM_EASY;
			break;
		}
		case INTERMEDIATE: {
			width = 815;
			dim = Board.DIM_INTERMEDIATE;
			break;
		}
		case HARD: {
			width = 610;
			dim = Board.DIM_HARD;
			break;
		}
		}

		getPnBoard().setBounds(26, 240, width, 106); // we resize the board with the new dimension
		getPnBoard().setLayout(new GridLayout(1, dim, 4, 0));
		// We delete the components in the board
		getPnBoard().removeAll();
		for (int i = 0; i < dim; i++) {
			getPnBoard().add(newButton(i));
			pnBoard.getComponent(i).setEnabled(false);
			((JButton) pnBoard.getComponent(i)).setIcon(null);
			((JButton) pnBoard.getComponent(i)).setDisabledIcon(null);
		}
		enableBoard(false);
		validate();
	}

	private void initialize(Level level) {
		game = new SpaceInvaders(level);

		while (pnShots.getComponentCount() != 0) {
			removeShot();
		}

		prepareBoard(level);
		btnDice.setEnabled(true);
		txtScore.setText("" + game.getScore());
		enableBoard(false);
		validate();

	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkExit()) {
						System.exit(0);
					}
				}
			});
		}
		return mntmExit;
	}

	private boolean checkExit() {
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to leave and end the game?") == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;

	}

	private boolean checkNewGame() {
		if (JOptionPane.showConfirmDialog(this,
				"Are you sure you want to end this game and start a new one?") == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}

	private JButton newButton(Integer position) {
		JButton newButton = new JButton();
		newButton.setEnabled(false);
		newButton.setBorder(new LineBorder(new Color(0, 255, 0), 2));
		newButton.setBackground(new Color(0, 0, 0));

		newButton.setActionCommand(position.toString()); // This string attribute is supposed to support a pattern View
															// Control model

//		newButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				shoot(Integer.parseInt(((JButton)e.getSource()).getActionCommand()));
//			}
//		});
		newButton.addActionListener(mBL);
		return newButton;
	}

	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
		}
		return mntmContents;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
		}
		return mntmAbout;
	}

	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('O');
			mnOptions.add(getMntmEasy());
			mnOptions.add(getMntmIntermediate());
			mnOptions.add(getMntmHard());
		}
		return mnOptions;
	}

	private JMenuItem getMntmEasy() {
		if (mntmEasy == null) {
			mntmEasy = new JMenuItem("Easy");
			mntmEasy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
			mntmEasy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize(Level.EASY);
				}
			});
		}
		return mntmEasy;
	}

	private JMenuItem getMntmIntermediate() {
		if (mntmIntermediate == null) {
			mntmIntermediate = new JMenuItem("Intermediate");
			mntmIntermediate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
			mntmIntermediate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize(Level.INTERMEDIATE);
				}
			});
		}
		return mntmIntermediate;
	}

	private JMenuItem getMntmHard() {
		if (mntmHard == null) {
			mntmHard = new JMenuItem("Hard");
			mntmHard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
			mntmHard.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					initialize(Level.HARD);
				}
			});
		}
		return mntmHard;

	}

	class MyButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			shoot(Integer.parseInt(((JButton) e.getSource()).getActionCommand()));
		}

	}
}
