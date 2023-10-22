package uo.cpm.p6.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

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
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JMenuBar menuBar;
	private JMenu mnGame;
	private JMenu mnHelp;
	private JMenuItem mntmNewGame;
	private JSeparator separator;
	private JMenuItem mntmExit;

	public MainWindow(SpaceInvaders game) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/invader.jpg")));
		setResizable(false);
		setTitle("Space Invasion!!");
		this.game = game;

		setBounds(100, 100, 770, 447);
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

	}

	private JButton getBtnDice() {
		if (btnDice == null) {
			btnDice = new JButton("");
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
			lblSpaceShip.setBounds(217, 44, 137, 74);
		}
		return lblSpaceShip;
	}

	private JLabel getLblScore() {
		if (lblScore == null) {
			lblScore = new JLabel("Score");
			lblScore.setForeground(Color.WHITE);
			lblScore.setHorizontalAlignment(SwingConstants.CENTER);
			lblScore.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblScore.setBounds(433, 44, 55, 41);
		}
		return lblScore;
	}

	private JTextField getTxtScore() {
		if (txtScore == null) {
			txtScore = new JTextField();
			txtScore.setFont(new Font("Tahoma", Font.BOLD, 15));
			txtScore.setHorizontalAlignment(SwingConstants.CENTER);
			txtScore.setText("0");
			txtScore.setForeground(Color.GREEN);
			txtScore.setEditable(false);
			txtScore.setBounds(419, 84, 86, 20);
			txtScore.setColumns(10);
		}
		return txtScore;
	}

	private JLabel getLblEarth() {
		if (lblEarth == null) {
			lblEarth = new JLabel("");
			lblEarth.setIcon(new ImageIcon(MainWindow.class.getResource("/img/earth.jpg")));
			lblEarth.setBounds(551, 11, 193, 181);
		}
		return lblEarth;
	}

	private JPanel getPnShots() {
		if (pnShots == null) {
			pnShots = new JPanel();
			pnShots.setBackground(Color.BLACK);
			pnShots.setBounds(107, 130, 386, 74);
		}
		return pnShots;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBackground(new Color(0, 0, 0));
			pnBoard.setBorder(new LineBorder(new Color(0, 255, 0), 5));
			pnBoard.setBounds(26, 240, 698, 106);
			pnBoard.setLayout(new GridLayout(1, 0, 0, 0));
			pnBoard.add(getBtnNewButton());
			pnBoard.add(getBtnNewButton_5());
			pnBoard.add(getBtnNewButton_6());
			pnBoard.add(getBtnNewButton_7());
			pnBoard.add(getBtnNewButton_4());
			pnBoard.add(getBtnNewButton_3());
			pnBoard.add(getBtnNewButton_2());
			pnBoard.add(getBtnNewButton_1());
		}
		return pnBoard;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(0);
				}
			});
			btnNewButton.setEnabled(false);
			btnNewButton.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton;
	}

	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(7);
				}
			});
			btnNewButton_1.setEnabled(false);
			btnNewButton_1.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_1.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_1;
	}

	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(6);
				}
			});
			btnNewButton_2.setEnabled(false);
			btnNewButton_2.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_2.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_2;
	}

	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(5);
				}
			});
			btnNewButton_3.setEnabled(false);
			btnNewButton_3.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_3.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_3;
	}

	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton("");
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(4);
				}
			});
			btnNewButton_4.setEnabled(false);
			btnNewButton_4.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_4.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_4;
	}

	private JButton getBtnNewButton_5() {
		if (btnNewButton_5 == null) {
			btnNewButton_5 = new JButton("");
			btnNewButton_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(1);
				}
			});
			btnNewButton_5.setEnabled(false);
			btnNewButton_5.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_5.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_5;
	}

	private JButton getBtnNewButton_6() {
		if (btnNewButton_6 == null) {
			btnNewButton_6 = new JButton("");
			btnNewButton_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(2);
				}
			});
			btnNewButton_6.setEnabled(false);
			btnNewButton_6.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_6.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_6;
	}

	private JButton getBtnNewButton_7() {
		if (btnNewButton_7 == null) {
			btnNewButton_7 = new JButton("");
			btnNewButton_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					shoot(3);
				}
			});
			btnNewButton_7.setEnabled(false);
			btnNewButton_7.setBorder(new LineBorder(new Color(0, 255, 0), 2));
			btnNewButton_7.setBackground(new Color(0, 0, 0));
		}
		return btnNewButton_7;
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
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnGame() {
		if (mnGame == null) {
			mnGame = new JMenu("Game");
			mnGame.add(getMntmNewGame());
			mnGame.add(getSeparator());
			mnGame.add(getMntmExit());
		}
		return mnGame;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
		}
		return mnHelp;
	}

	private JMenuItem getMntmNewGame() {
		if (mntmNewGame == null) {
			mntmNewGame = new JMenuItem("New game");
		}
		return mntmNewGame;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("New menu item");
		}
		return mntmExit;
	}
}
