package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnOpen;
	private JButton btnPrint;
	private JPanel stateBar;
	private JPanel pnTexts;
	private JScrollPane scOriginal;
	private JScrollPane scTranslated;
	private JTextArea taOriginal;
	private JTextArea taTranslated;
	private JButton btnSpanish;
	private JButton btnFrench;
	private JButton btnEnglish;
	private JLabel etDocOriginal;
	private JLabel etIdOriginal;
	private JLabel etDocTraducido;
	private JLabel etIdTraducido;
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu translateMenu;
	private JMenu toolsMenu;
	private JMenu helpMenu;
	private JMenuItem saveAsItem;
	private JMenuItem newItem;
	private JMenuItem exitItem;
	
	private boolean activatedKeyNumber;
	private KeyNotNumberListener kL= new KeyNotNumberListener();

	
	private JMenuBar getMyMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getFileMenu());
			menuBar.add(getEditMenu());
			menuBar.add(getTranslateMenu());
			menuBar.add(getToolsMenu());
			menuBar.add(getHelpMenu());
		}
		return menuBar;
	}

	private JToolBar getToolBar() {
		if (toolBar == null) {
			toolBar = new JToolBar();
			toolBar.setBackground(new java.awt.Color(208,204,204));
			toolBar.add(getBtnNew());
			toolBar.add(getBtnOpen());
			toolBar.add(getBtnSave());
			toolBar.add(getBtnPrint());
			toolBar.add(getBtnEnglish());
			toolBar.add(getBtnSpanish());
			toolBar.add(getBtnFrench());
			for (int i=0; i<toolBar.getComponentCount();i++) {
				toolBar.getComponent(i).addMouseListener(new MouseBorderListener());
			}
		}
		return toolBar;
	}
	
	private class MouseBorderListener extends MouseAdapter{
		
		@Override
		public void mouseEntered(MouseEvent e) {
			((JButton)e.getSource()).setBorderPainted(true);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			((JButton)e.getSource()).setBorderPainted(false);
		}
	}

	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton();
			btnNew.setIcon(new ImageIcon(MainWindow.class.getResource("/img/New.png")));
			btnNew.setBorderPainted(false);
			btnNew.setContentAreaFilled(false);
			btnNew.setFocusPainted(false);
			btnNew.setPreferredSize(new java.awt.Dimension(24,24));
			btnNew.setMaximumSize(new java.awt.Dimension(24,24));
			btnNew.setMinimumSize(new java.awt.Dimension(24,24));
			btnNew.setMargin(new java.awt.Insets(0,0,0,0));
		}
		return btnNew;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton();
			btnSave.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Save.png")));
			btnSave.setBorderPainted(false);
			btnSave.setContentAreaFilled(false);
			btnSave.setPreferredSize(new java.awt.Dimension(24,24));
			btnSave.setMaximumSize(new java.awt.Dimension(24,24));
			btnSave.setMinimumSize(new java.awt.Dimension(24,24));
			btnSave.setMargin(new java.awt.Insets(0,0,0,0));
		}
		return btnSave;
	}

	private JButton getBtnOpen() {
		if (btnOpen == null) {
			btnOpen = new JButton();
			btnOpen.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Open.png")));
			btnOpen.setBorderPainted(false);
			btnOpen.setContentAreaFilled(false);
			btnOpen.setMaximumSize(new java.awt.Dimension(24,24));
			btnOpen.setMinimumSize(new java.awt.Dimension(24,24));
			btnOpen.setPreferredSize(new java.awt.Dimension(24,24));
			btnOpen.setMargin(new java.awt.Insets(0,0,0,0));
		}
		return btnOpen;
	}

	private JButton getBtnPrint() {
		if (btnPrint == null) {
			btnPrint = new JButton();
			btnPrint.setIcon(new ImageIcon(MainWindow.class.getResource("/img/Print.png")));
			btnPrint.setBorderPainted(false);
			btnPrint.setContentAreaFilled(false);
			btnPrint.setMaximumSize(new java.awt.Dimension(24,24));
			btnPrint.setMinimumSize(new java.awt.Dimension(24,24));
			btnPrint.setMargin(new java.awt.Insets(0,0,0,0));
		}
		return btnPrint;
	}

	private JPanel getStateBar() {
		if (stateBar == null) {
			stateBar = new JPanel();
			stateBar.setLayout(new GridLayout(1, 4, 0, 0));
			stateBar.add(getEtDocOriginal());
			stateBar.add(getEtIdOriginal());
			stateBar.add(getEtDocTraducido());
			stateBar.add(getEtIdTraducido());
		}
		return stateBar;
	}

	private JLabel getEtDocOriginal() {
		if (etDocOriginal == null){
			etDocOriginal = new JLabel();
			etDocOriginal.setText("Original Document");
			etDocOriginal.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
			etDocOriginal.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		}
		return etDocOriginal;
	}

	private JLabel getEtIdOriginal() {
		if (etIdOriginal == null){
			etIdOriginal = new JLabel();
			etIdOriginal.setText("Spanish");
			etIdOriginal.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
			etIdOriginal.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		}
		return etIdOriginal;
	}

	private JLabel getEtDocTraducido() {
		if (etDocTraducido == null){
			etDocTraducido = new JLabel();
			etDocTraducido.setText("Translated Document");
			etDocTraducido.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
			etDocTraducido.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
		}
		return etDocTraducido;
	}

	private JLabel getEtIdTraducido() {
		if (etIdTraducido == null){
			etIdTraducido = new JLabel();
			etIdTraducido.setText("English");
			etIdTraducido.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
			etIdTraducido.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
		}
		return etIdTraducido;
	}

	private JPanel getPnTexts() {
		if (pnTexts == null) {
			GridLayout gl_pnTexts = new GridLayout();
			gl_pnTexts.setRows(2);
			gl_pnTexts.setColumns(1);
			pnTexts = new JPanel();
			pnTexts.setLayout(gl_pnTexts);
			pnTexts.add(getScOriginal(), null);
			pnTexts.add(getScTranslated(), null);
		}
		return pnTexts;
	}

	private JScrollPane getScOriginal() {
		if (scOriginal == null) {
			scOriginal = new JScrollPane();
			scOriginal.setBorder(new TitledBorder(null, "Original document", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scOriginal.setViewportView(getTaOriginal());
		}
		return scOriginal;
	}

	private JScrollPane getScTranslated() {
		if (scTranslated == null) {
			scTranslated = new JScrollPane();
			scTranslated.setBorder(new TitledBorder(null, "Translated Document", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			scTranslated.setViewportView(getTaTranslated());
		}
		return scTranslated;
	}

	private JTextArea getTaOriginal() {
		if (taOriginal == null) {
			taOriginal = new JTextArea();
			taOriginal.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
				}
			});
			taOriginal.addFocusListener(new FocusBackgroundListener());
			taOriginal.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
			taOriginal.setWrapStyleWord(true);
			taOriginal.setBackground(java.awt.Color.lightGray);
			taOriginal.setLineWrap(true);
		}
		return taOriginal;
	}
	
	private class KeyNotNumberListener extends KeyAdapter{
		@Override
		public void keyTyped(KeyEvent e) {
			checkKey(e);
		}
		
		void checkKey(KeyEvent e) {
			char key=e.getKeyChar();
			if (!Character.isDigit(key)) {
				e.consume();
			}
		}
	}

	private JTextArea getTaTranslated() {
		if (taTranslated == null) {
			taTranslated = new JTextArea();
			taTranslated.addFocusListener(new FocusBackgroundListener());
			taTranslated.setWrapStyleWord(true);
			taTranslated.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
			taTranslated.setBackground(java.awt.Color.lightGray);
			taTranslated.setLineWrap(true);
		}
		return taTranslated;
	}

	private JButton getBtnSpanish() {
		if (btnSpanish == null) {
			btnSpanish = new JButton();
			btnSpanish.setText("ES");
			btnSpanish.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
			btnSpanish.setBorderPainted(false);
			btnSpanish.setPreferredSize(new java.awt.Dimension(24,24));
			btnSpanish.setMargin(new java.awt.Insets(0,0,0,0));
			btnSpanish.setMaximumSize(new java.awt.Dimension(24,24));
			btnSpanish.setMinimumSize(new java.awt.Dimension(24,24));
			btnSpanish.setContentAreaFilled(false);
			
		}
		return btnSpanish;
	}

	private JButton getBtnFrench() {
		if (btnFrench == null) {
			btnFrench = new JButton();
			btnFrench.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!activatedKeyNumber) {
					getTaOriginal().addKeyListener(kL);
					getTaTranslated().addKeyListener(kL);
					activatedKeyNumber=true;
					}
					else {
						activatedKeyNumber=false;
						getTaOriginal().removeKeyListener(kL);
						getTaTranslated().removeKeyListener(kL);
					}
				}
			});
			btnFrench.setBorderPainted(false);
			btnFrench.setText("FR");
			btnFrench.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
			btnFrench.setPreferredSize(new java.awt.Dimension(25,24));
			btnFrench.setMargin(new java.awt.Insets(0,0,0,0));
			btnFrench.setMaximumSize(new java.awt.Dimension(25,24));
			btnFrench.setMinimumSize(new java.awt.Dimension(25,24));
			btnFrench.setContentAreaFilled(false);
		}
		return btnFrench;
	}

	private JButton getBtnEnglish() {
		if (btnEnglish == null) {
			btnEnglish = new JButton();
			btnEnglish.setBorderPainted(false);
			btnEnglish.setText("EN");
			btnEnglish.setFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 14));
			btnEnglish.setPreferredSize(new java.awt.Dimension(24,24));
			btnEnglish.setMargin(new java.awt.Insets(0,0,0,0));
			btnEnglish.setMaximumSize(new java.awt.Dimension(24,24));
			btnEnglish.setMinimumSize(new java.awt.Dimension(24,24));
			btnEnglish.setContentAreaFilled(false);
		}
		return btnEnglish;
	}

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setMnemonic('f');
			fileMenu.setText("File");
			fileMenu.add(getNewItem());
			fileMenu.addSeparator();
			fileMenu.add(getSaveAsItem());
			fileMenu.addSeparator();
			fileMenu.add(getExitItem());
		}
		return fileMenu;
	}

	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setMnemonic('e');
			editMenu.setText("Edit");
		}
		return editMenu;
	}

	private JMenu getTranslateMenu() {
		if (translateMenu == null) {
			translateMenu = new JMenu();
			translateMenu.setMnemonic('t');
			translateMenu.setText("Translate");
		}
		return translateMenu;
	}

	private JMenu getToolsMenu() {
		if (toolsMenu == null) {
			toolsMenu = new JMenu();
			toolsMenu.setMnemonic('l');
			toolsMenu.setText("Tools");
		}
		return toolsMenu;
	}

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setMnemonic('H');
			helpMenu.setText("Help");
		}
		return helpMenu;
	}

	private JMenuItem getSaveAsItem() {
		if (saveAsItem == null) {
			saveAsItem = new JMenuItem();
			saveAsItem.setMnemonic('S');
			saveAsItem.setText("Save as...");
		}
		return saveAsItem;
	}

	private JMenuItem getNewItem() {
		if (newItem == null) {
			newItem = new JMenuItem();
			newItem.setMnemonic('N');
			newItem.setText("New");
		}
		return newItem;
	}

	private JMenuItem getExitItem() {
		if (exitItem == null) {
			exitItem = new JMenuItem();
			exitItem.setMnemonic('x');
			exitItem.setText("Exit");
		}
		return exitItem;
	}


	public MainWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				getTaOriginal().grabFocus();
			}
		});
		setSize(981, 586);
		setJMenuBar(getMyMenuBar());
		setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		setTitle("Text translations editor");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(getToolBar(), java.awt.BorderLayout.NORTH);
		contentPane.add(getStateBar(), java.awt.BorderLayout.SOUTH);
		contentPane.add(getPnTexts(), java.awt.BorderLayout.CENTER);
		
		setContentPane(contentPane);
		
		
	}

} 
