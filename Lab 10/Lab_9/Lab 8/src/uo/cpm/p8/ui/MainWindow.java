package uo.cpm.p8.ui;

import java.awt.BorderLayout;
import javax.help.*;
import java.net.*;
import java.io.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import uo.cpm.p8.player.MusicPlayer;

public class MainWindow extends JFrame {

	private MusicPlayer mP = null;
	private JPanel pnNorth;
	private JLabel lblLogo;
	private JSlider slVolume;
	private JPanel pnVol;
	private JLabel lblVol;
	private JTextField txtVol;
	private JPanel pnCenter;
	private JPanel pnLibrary;
	private JPanel pnPlaylist;
	private JLabel lblLibrary;
	private JPanel pnLibraryButtons;
	private JButton btnAdd;
	private JButton btnDelete;
	private JScrollPane spLibrary;
	private JList list1;
	private JLabel lblPlaylist;
	private JPanel pnPlaylistButtons;
	private JButton btnRew;
	private JButton btnPlay;
	private JButton btnFor;
	private JButton btnStop;
	private JButton btnDel;
	private JScrollPane spPlaylist;
	private JList list2;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnPlay;
	private JMenu mnOptions;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmContents;
	private JSeparator separator_1;
	private JMenuItem mntmAbout;

	private DefaultListModel modelList1 = null;
	private DefaultListModel modelList2 = null;

	private JFileChooser selector = null;
	private JMenuItem mntmRemoveAll;
	private JButton btnRandom;

	/**
	 * Create the frame.
	 * 
	 * @param mP
	 */
	public MainWindow(MusicPlayer mP) {
//		addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent e) {
//				System.out.println(((JFrame)e.getSource()).getBounds());
//			}
//		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/logoTitulo.png")));
		setTitle("EII Mp3 Player");
		this.mP = mP;
		setBounds(100, 100, 789, 579);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(getPnNorth(), BorderLayout.NORTH);
		getContentPane().add(getPnCenter(), BorderLayout.CENTER);
		setJMenuBar(getMenuBar_1());
		setMinimumSize(new Dimension(475, 250));
		loadHelp();
	}

	//Add this method to the main window and call it from the constructor
	private void loadHelp(){
	   URL hsURL;
	   HelpSet hs;

	    try {
		    	File fichero = new File("help/Help.hs");
		    	hsURL = fichero.toURI().toURL();
		        hs = new HelpSet(null, hsURL);
		      }

	    catch (Exception e){
	      System.out.println("Help not found!");
	      return;
	   }

	   HelpBroker hb = hs.createHelpBroker();
	   hb.initPresentation(); // Preloads the help support
	   hb.enableHelpKey(getRootPane(),"intro", hs); //allow access help support by F1 in this Frame (you need to do it in every dialog)
	   hb.enableHelpOnButton(getMntmContents(), "intro", hs); //allow access help support by the button on the menu
	   hb.enableHelp(getList1(), "add", hs); //allow access help support by an specific component
	 }
	
	private JFileChooser getSelector() {
		if (selector == null) {
			selector = new JFileChooser();
			selector.setMultiSelectionEnabled(true);

			selector.setFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3"));

			String desktopPath = System.getProperty("user.home") + "/Desktop"; // this doesn't make it portable to UNIX
																				// or Mac
			selector.setCurrentDirectory(new File(desktopPath));
		}
		return selector;
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setLayout(new GridLayout(1, 0, 0, 0));
			pnNorth.add(getLblLogo());
			pnNorth.add(getSlVolume());
			pnNorth.add(getPnVol());
		}
		return pnNorth;
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblLogo.setIcon(new ImageIcon(MainWindow.class.getResource("/img/logo.png")));
		}
		return lblLogo;
	}

	private JSlider getSlVolume() {
		if (slVolume == null) {
			slVolume = new JSlider();
			slVolume.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					getTxtVol().setText(String.valueOf(slVolume.getValue()));
					mP.setVolume(slVolume.getValue(), slVolume.getMaximum());
				}
			});
			slVolume.setBorder(null);
			slVolume.setForeground(new Color(255, 255, 204));
			slVolume.setPaintTicks(true);
			slVolume.setPaintLabels(true);
			slVolume.setMinorTickSpacing(10);
			slVolume.setMajorTickSpacing(20);
		}
		return slVolume;
	}

	private JPanel getPnVol() {
		if (pnVol == null) {
			pnVol = new JPanel();
			pnVol.add(getLblVol());
			pnVol.add(getTxtVol());
		}
		return pnVol;
	}

	private JLabel getLblVol() {
		if (lblVol == null) {
			lblVol = new JLabel("Vol:");
			lblVol.setForeground(new Color(255, 140, 0));
			lblVol.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblVol.setHorizontalAlignment(SwingConstants.CENTER);
			lblVol.setLabelFor(getTxtVol());
		}
		return lblVol;
	}

	private JTextField getTxtVol() {
		if (txtVol == null) {
			txtVol = new JTextField();
			txtVol.setBorder(null);
			txtVol.setFont(new Font("Tahoma", Font.BOLD, 18));
			txtVol.setForeground(new Color(255, 255, 255));
			txtVol.setText("50");
			txtVol.setHorizontalAlignment(SwingConstants.CENTER);
			txtVol.setEditable(false);
			txtVol.setColumns(3);
		}
		return txtVol;
	}

	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setLayout(new GridLayout(1, 0, 0, 0));
			pnCenter.add(getPnLibrary());
			pnCenter.add(getPnPlaylist());
		}
		return pnCenter;
	}

	private JPanel getPnLibrary() {
		if (pnLibrary == null) {
			pnLibrary = new JPanel();
			pnLibrary.setLayout(new BorderLayout(0, 0));
			pnLibrary.add(getLblLibrary(), BorderLayout.NORTH);
			pnLibrary.add(getPnLibraryButtons(), BorderLayout.SOUTH);
			pnLibrary.add(getSpLibrary(), BorderLayout.CENTER);
		}
		return pnLibrary;
	}

	private JPanel getPnPlaylist() {
		if (pnPlaylist == null) {
			pnPlaylist = new JPanel();
			pnPlaylist.setLayout(new BorderLayout(0, 0));
			pnPlaylist.add(getLblPlaylist(), BorderLayout.NORTH);
			pnPlaylist.add(getPnPlaylistButtons(), BorderLayout.SOUTH);
			pnPlaylist.add(getSpPlaylist(), BorderLayout.CENTER);
		}
		return pnPlaylist;
	}

	private JLabel getLblLibrary() {
		if (lblLibrary == null) {
			lblLibrary = new JLabel("\u266ALibrary:");
			lblLibrary.setForeground(new Color(255, 153, 0));
			lblLibrary.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblLibrary.setLabelFor(getList1());
		}
		return lblLibrary;
	}

	private JPanel getPnLibraryButtons() {
		if (pnLibraryButtons == null) {
			pnLibraryButtons = new JPanel();
			pnLibraryButtons.setLayout(new GridLayout(1, 0, 0, 0));
			pnLibraryButtons.add(getBtnAdd());
			pnLibraryButtons.add(getBtnDelete());
		}
		return pnLibraryButtons;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Add to Playlist");
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (Object o : list1.getSelectedValuesList()) {
						if (!modelList2.contains(o))
							modelList2.addElement(o);
					}
				}
			});
			btnAdd.setMnemonic('A');
		}
		return btnAdd;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (Object o : list1.getSelectedValuesList()) {
						modelList1.removeElement(o);
					}
				}
			});
			btnDelete.setMnemonic('D');
		}
		return btnDelete;
	}

	private JScrollPane getSpLibrary() {
		if (spLibrary == null) {
			spLibrary = new JScrollPane();
			spLibrary.setViewportView(getList1());
		}
		return spLibrary;
	}

	private JList getList1() {
		if (list1 == null) {
			list1 = new JList();
			modelList1 = new DefaultListModel();
			list1.setModel(modelList1);
			list1.setBorder(new LineBorder(new Color(255, 153, 0), 2));
		}
		return list1;
	}

	private JLabel getLblPlaylist() {
		if (lblPlaylist == null) {
			lblPlaylist = new JLabel("\u266APlaylist:");
			lblPlaylist.setForeground(new Color(255, 153, 0));
			lblPlaylist.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPlaylist;
	}

	private JPanel getPnPlaylistButtons() {
		if (pnPlaylistButtons == null) {
			pnPlaylistButtons = new JPanel();
			pnPlaylistButtons.setLayout(new GridLayout(1, 0, 0, 0));
			pnPlaylistButtons.add(getBtnRew());
			pnPlaylistButtons.add(getBtnPlay());
			pnPlaylistButtons.add(getBtnFor());
			pnPlaylistButtons.add(getBtnStop());
			pnPlaylistButtons.add(getBtnDel());
			pnPlaylistButtons.add(getBtnRandom());
		}
		return pnPlaylistButtons;
	}

	private JButton getBtnRew() {
		if (btnRew == null) {
			btnRew = new JButton("\u25C4\u25C4");
			btnRew.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Integer selectedSong = list2.getSelectedIndex();
					if (selectedSong > 0) {
						list2.setSelectedIndex(--selectedSong);
						mP.play(((MyFile) list2.getSelectedValue()).getFile());
					}
				}
			});
		}
		return btnRew;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("\u25BA");
			btnPlay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mP.play(((MyFile) list2.getSelectedValue()).getFile());
				}
			});
		}
		return btnPlay;
	}

	private JButton getBtnFor() {
		if (btnFor == null) {
			btnFor = new JButton("\u25BA\u25BA");
			btnFor.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Integer selectedSong = list2.getSelectedIndex();
					if (selectedSong < modelList2.getSize() - 1) {
						list2.setSelectedIndex(selectedSong + 1);
						mP.play(((MyFile) list2.getSelectedValue()).getFile());
					}
				}
			});
		}
		return btnFor;
	}

	private JButton getBtnStop() {
		if (btnStop == null) {
			btnStop = new JButton("\u25A0");
			btnStop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mP.stop();
				}
			});
		}
		return btnStop;
	}

	private JButton getBtnDel() {
		if (btnDel == null) {
			btnDel = new JButton("Del");
			btnDel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (Object o : list2.getSelectedValuesList()) {
						modelList2.removeElement(o);
					}
				}
			});
		}
		return btnDel;
	}

	private JScrollPane getSpPlaylist() {
		if (spPlaylist == null) {
			spPlaylist = new JScrollPane();
			spPlaylist.setViewportView(getList2());
		}
		return spPlaylist;
	}

	private JList getList2() {
		if (list2 == null) {
			list2 = new JList();
			modelList2 = new DefaultListModel();
			list2.setModel(modelList2);
			list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list2.setBorder(new LineBorder(new Color(255, 153, 0), 2));
		}
		return list2;
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnPlay());
			menuBar.add(getMnOptions());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.setMnemonic('F');
			mnFile.add(getMntmOpen());
			mnFile.add(getMntmRemoveAll());
			mnFile.add(getSeparator());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnPlay() {
		if (mnPlay == null) {
			mnPlay = new JMenu("Play");
			mnPlay.setMnemonic('P');
		}
		return mnPlay;
	}

	private JMenu getMnOptions() {
		if (mnOptions == null) {
			mnOptions = new JMenu("Options");
			mnOptions.setMnemonic('t');
		}
		return mnOptions;
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

	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Integer response = getSelector().showOpenDialog(rootPane);
					if (response == JFileChooser.APPROVE_OPTION) {
						for (File f : getSelector().getSelectedFiles()) {
							modelList1.addElement(new MyFile(f));
						}
					}
				}
			});
			mntmOpen.setMnemonic('O');
		}
		return mntmOpen;
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
			mntmExit.setMnemonic('E');
		}
		return mntmExit;
	}

	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setMnemonic('C');
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
			mntmAbout.setMnemonic('b');
		}
		return mntmAbout;
	}

	private JMenuItem getMntmRemoveAll() {
		if (mntmRemoveAll == null) {
			mntmRemoveAll = new JMenuItem("Remove all");
			mntmRemoveAll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					modelList1.removeAllElements();
					modelList2.removeAllElements();
				}
			});
		}
		return mntmRemoveAll;
	}

	private JButton getBtnRandom() {
		if (btnRandom == null) {
			btnRandom = new JButton("Rand");
			btnRandom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int index = (int) (Math.random() * (modelList2.getSize()));
					mP.play(((MyFile) modelList2.getElementAt(index)).getFile());
				}
			});
		}
		return btnRandom;
	}
}
