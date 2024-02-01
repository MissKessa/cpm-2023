package uo.cpm.examen.ui;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uo.cpm.examen.logic.Juego;
import uo.cpm.examen.logic.Prize;
import uo.cpm.examen.logic.TypePrize;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDado;
	private JPanel pnGifts;
	private JComboBox<Prize> comboBox;
	private JLabel lblPoints;
	private JTextField txtPoints;
	private JButton btnEnd;
	private JButton btnAdd;
	private JLabel lblAvailablePrizes;
	private JScrollPane scrollPane;
	private JTextArea txtAreaPrizesChosen;
	private JLabel lblNewLabel;
	private Juego juego;
	private JLabel lblImagePrize;
	private JPanel pnFilter;
	private JButton btnFilterElctronica;
	private JButton btnFilterInfantil;
	private JButton btnFilterOcio;
	private JButton btnFinish;
	private JMenuBar menuBar;
	private JMenu mnRoulette;
	private JMenu mnHelp;
	private JMenuItem mntmNewGame;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JMenuItem mntmHelpContents;
	private JSeparator separator_1;

	public VentanaPrincipal() {
		juego = new Juego();
		setTitle("Roulette");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/ruleta.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 658);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPnGifts());
		contentPane.add(getBtnEnd());
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel());
		contentPane.add(getBtnDado());
		contentPane.add(getLblPoints());
		contentPane.add(getTxtPoints());
		this.getRootPane().setDefaultButton(btnEnd);
		contentPane.add(getLblImagePrize());
		contentPane.add(getPnFilter());
		contentPane.add(getBtnFinish());

	}

	private JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("");
			btnDado.setToolTipText("Roulette");
			btnDado.setBounds(13, 113, 189, 226);
			btnDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/ruleta.png")));
			btnDado.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					juego.setPoints();
					getTxtPoints().setText("" + juego.getPoints());
					btnDado.setEnabled(false);
					btnAdd.setEnabled(true);
					getComboBox().setModel(new DefaultComboBoxModel(juego.getAvailablePrizesString()));
					getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class
							.getResource("/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));
				}
			});
		}
		return btnDado;
	}

	private JPanel getPnGifts() {
		if (pnGifts == null) {
			pnGifts = new JPanel();
			pnGifts.setToolTipText("");
			pnGifts.setBounds(212, 248, 477, 96);
			pnGifts.setLayout(null);
			pnGifts.add(getComboBox());
			pnGifts.add(getBtnAdd());
			pnGifts.add(getLblAvailablePrizes());
		}
		return pnGifts;
	}

	private JComboBox<Prize> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<Prize>();
			comboBox.setToolTipText("Available prizes");
			comboBox.setModel(new DefaultComboBoxModel(juego.getPrizesString()));
			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class
							.getResource("/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));
				}
			});

			comboBox.setBounds(20, 40, 288, 33);

		}
		return comboBox;
	}

	private JLabel getLblPoints() {
		if (lblPoints == null) {
			lblPoints = new JLabel("Points:");
			lblPoints.setBounds(212, 153, 45, 13);
		}
		return lblPoints;
	}

	private JTextField getTxtPoints() {
		if (txtPoints == null) {
			txtPoints = new JTextField();
			txtPoints.setToolTipText("Points");
			txtPoints.setBounds(276, 148, 96, 19);
			txtPoints.setEditable(false);
			txtPoints.setColumns(10);
			txtPoints.setText("" + juego.getPoints());
		}
		return txtPoints;
	}

	private JButton getBtnEnd() {
		if (btnEnd == null) {
			btnEnd = new JButton("End");
			btnEnd.setToolTipText("End");
			btnEnd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(rootPane, "Congratulations for your gifts!", "Roulette:Congrats",
							JOptionPane.INFORMATION_MESSAGE);
					getComboBox().setEnabled(false);
					getBtnEnd().setEnabled(false);
					getBtnAdd().setEnabled(false);
					getBtnFinish().setEnabled(true);
				}
			});
			btnEnd.setEnabled(false);
			btnEnd.setBounds(508, 541, 85, 21);
		}
		return btnEnd;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add");
			btnAdd.setToolTipText("Add prize");
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					juego.add((String) getComboBox().getSelectedItem());
					String[] available = juego.getAvailablePrizesString();
					getComboBox().setModel(new DefaultComboBoxModel(available));

					if (available.length == 0) {
						btnAdd.setEnabled(false);
						getLblImagePrize().setIcon(null);
					} else {
						getComboBox().setSelectedIndex(0);
						getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class.getResource(
								"/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));
					}
					getTxtPoints().setText("" + juego.getRemainingPoints());
					getBtnEnd().setEnabled(true);
					txtAreaPrizesChosen.setText(juego.getSelectedString());

				}
			});
			btnAdd.setMnemonic('A');
			btnAdd.setEnabled(false);
			btnAdd.setBounds(318, 46, 85, 21);
		}
		return btnAdd;
	}

	private JLabel getLblAvailablePrizes() {
		if (lblAvailablePrizes == null) {
			lblAvailablePrizes = new JLabel("Available prizes:");
			lblAvailablePrizes.setDisplayedMnemonic('v');
			lblAvailablePrizes.setLabelFor(getComboBox());
			lblAvailablePrizes.setBounds(20, 10, 106, 20);
		}
		return lblAvailablePrizes;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(212, 377, 477, 144);
			scrollPane.setViewportView(getTxtAreaPrizesChosen());
		}
		return scrollPane;
	}

	private JTextArea getTxtAreaPrizesChosen() {
		if (txtAreaPrizesChosen == null) {
			txtAreaPrizesChosen = new JTextArea();
			txtAreaPrizesChosen.setToolTipText("Prizes chosen");
			txtAreaPrizesChosen.setEditable(false);
			txtAreaPrizesChosen.setText(juego.getSelectedString());

		}
		return txtAreaPrizesChosen;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Prizes chosen:");
			lblNewLabel.setBounds(210, 354, 95, 13);
		}
		return lblNewLabel;
	}

	private JLabel getLblImagePrize() {
		if (lblImagePrize == null) {
			lblImagePrize = new JLabel("");
			lblImagePrize.setBounds(416, 103, 251, 133);

		}
		return lblImagePrize;
	}

	private JPanel getPnFilter() {
		if (pnFilter == null) {
			pnFilter = new JPanel();
			pnFilter.setBounds(13, 10, 676, 93);
			pnFilter.setLayout(new GridLayout(1, 0, 0, 0));
			pnFilter.add(getBtnFilterElctronica());
			pnFilter.add(getBtnFilterInfantil());
			pnFilter.add(getBtnFilterOcio());
		}
		return pnFilter;
	}

	private JButton getBtnFilterElctronica() {
		if (btnFilterElctronica == null) {
			btnFilterElctronica = new JButton("Electronica");
			btnFilterElctronica.addActionListener(new ActionListener() {
				@SuppressWarnings("unchecked")
				@Override
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(
							new DefaultComboBoxModel(juego.getAvailablePrizesByTypeString(TypePrize.Electronica)));

					getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class
							.getResource("/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));
				}

			});
			btnFilterElctronica.setMnemonic('E');
			btnFilterElctronica.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFilterElctronica.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnFilterElctronica.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoElectronica.png")));
			btnFilterElctronica.setToolTipText("Electronica filter");
		}
		return btnFilterElctronica;
	}

	private JButton getBtnFilterInfantil() {
		if (btnFilterInfantil == null) {
			btnFilterInfantil = new JButton("Infantil");
			btnFilterInfantil.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getComboBox().setModel(
							new DefaultComboBoxModel(juego.getAvailablePrizesByTypeString(TypePrize.Infantil)));

					getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class
							.getResource("/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));
				}
			});
			btnFilterInfantil.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFilterInfantil.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnFilterInfantil.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoInfantil.png")));
			btnFilterInfantil.setMnemonic('I');
			btnFilterInfantil.setToolTipText("Infantil filter");
		}
		return btnFilterInfantil;
	}

	private JButton getBtnFilterOcio() {
		if (btnFilterOcio == null) {
			btnFilterOcio = new JButton("Ocio");
			btnFilterOcio.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getComboBox()
							.setModel(new DefaultComboBoxModel(juego.getAvailablePrizesByTypeString(TypePrize.Ocio)));

					getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class
							.getResource("/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));
				}
			});
			btnFilterOcio.setHorizontalTextPosition(SwingConstants.CENTER);
			btnFilterOcio.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnFilterOcio.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/iconoOcio.png")));
			btnFilterOcio.setMnemonic('O');
			btnFilterOcio.setToolTipText("Ocio filter");
		}
		return btnFilterOcio;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.addActionListener(new ProcessFinish());
			btnFinish.setMnemonic('F');
			btnFinish.setEnabled(false);
			btnFinish.setBounds(604, 541, 85, 21);
		}
		return btnFinish;
	}

	private class ProcessFinish implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnRoulette());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnRoulette() {
		if (mnRoulette == null) {
			mnRoulette = new JMenu("Roulette");
			mnRoulette.setMnemonic('R');
			mnRoulette.add(getMntmNewGame());
			mnRoulette.add(getSeparator());
			mnRoulette.add(getMntmExit());
		}
		return mnRoulette;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmHelpContents());
			mnHelp.add(getSeparator_1());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmNewGame() {
		if (mntmNewGame == null) {
			mntmNewGame = new JMenuItem("New Game");
			mntmNewGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					initializeGame();
				}
			});
			mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmNewGame;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private void initializeGame() {
		juego = new Juego();
		getBtnDado().setEnabled(true);
		getBtnAdd().setEnabled(false);
		getBtnEnd().setEnabled(false);
		getBtnFinish().setEnabled(false);
		getComboBox().setEnabled(true);
		getTxtAreaPrizesChosen().setText(null);
		getTxtPoints().setText("0");
		comboBox.setModel(new DefaultComboBoxModel(juego.getPrizesString()));

		getLblImagePrize().setIcon(new ImageIcon(VentanaPrincipal.class
				.getResource("/img/" + (juego.getImagePrize((String) getComboBox().getSelectedItem())))));

	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(rootPane, "Do you really want to exit?", "Roullete:Exit",
							JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						System.exit(0);
				}

			});
			mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmExit;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(rootPane, "Author: Paula Díaz Álvarez \nDNI: 71789634X",
							"Roulette:About", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmAbout;
	}

	private JMenuItem getMntmHelpContents() {
		if (mntmHelpContents == null) {
			mntmHelpContents = new JMenuItem("Help Contents");
			mntmHelpContents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		}
		return mntmHelpContents;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
}
