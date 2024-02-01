package uo.cpm.module.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import uo.cpm.module.model.Castle;
import uo.cpm.module.ui.MainWindow;

/**
 * Panel shpwing the detailed information of the castle: name, country, image,
 * description, enchantments and price. It also has a reservation and back
 * button
 * 
 * @author paula
 *
 */
public class CastleWindowPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MainWindow mw;
	/**
	 * Panel containing the image and description of the castle
	 */
	private JPanel pnCenterInfoCastle;
	/**
	 * Panel containing the name and country
	 */
	private JPanel pnNorthInfoCastle;
	/**
	 * Panel containing the buttons, enchanments and price
	 */
	private JPanel pnSouthInfoCastle;

	private JLabel lblImageCastle;
	private ImageIcon image = null;

	private JScrollPane scrPnDescription;
	private JTextArea txtCastleDescription;
	private JLabel lblCastleDescription;

	private JPanel pnCastleName;
	private JLabel lblCastleName;
	private JTextField txtCastleName;

	private JPanel pnCastleCountry;
	private JLabel lblCastleCountry;
	private JTextField txtCastleCountry;

	/**
	 * Panel containing the enchanments and price
	 */
	private JPanel pnMainInfo;
	private JPanel pnCastleEnchantments;
	private JLabel lblCastleEnchantments;
	private JTextField txtCastleEnchantments;

	private JPanel pnCastlePrice;
	private JLabel lblCastlePrice;
	private JTextField txtCastlePrice;

	/**
	 * Panel containg the buttons
	 */
	private JPanel pnCastleInfoButtons;
	private JButton btnMakeReservation;
	private JButton btnBack;

//	private Castle castle;

	/**
	 * Create the panel and localize it
	 */
	public CastleWindowPanel(MainWindow mw) {
		this.mw = mw;

		setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		setLayout(new BorderLayout(0, 10));
		add(getPnCenterInfoCastle(), BorderLayout.CENTER);
		add(getPnNorthInfoCastle(), BorderLayout.NORTH);
		add(getPnSouthInfoCastle(), BorderLayout.SOUTH);

		localize();

	}

	/**
	 * Localizes the whole panel with the locale obtain from the main window
	 */
	private void localize() {
		ResourceBundle texts = mw.getTexts();

		lblImageCastle.setToolTipText(texts.getString("lblImageCastle.toolTip"));

		lblCastleName.setText(texts.getString("lblCastleName.text"));
		txtCastleName.setToolTipText(texts.getString("txtCastleName.toolTip"));

		lblCastleCountry.setText(texts.getString("lblCastleCountry.text"));
		txtCastleCountry.setToolTipText(texts.getString("txtCastleCountry.toolTip"));

		lblCastleEnchantments.setText(texts.getString("lblCastleEnchantments.text"));
		txtCastleEnchantments.setToolTipText(texts.getString("txtCastleEnchantments.toolTip"));

		lblCastlePrice.setText(texts.getString("lblCastlePrice.text"));
		txtCastlePrice.setToolTipText(texts.getString("txtCastlePrice.toolTip"));

		lblCastleDescription.setText(texts.getString("lblCastleDescription.text"));
		txtCastleDescription.setToolTipText(texts.getString("txtCastleDescription.toolTip"));

		btnMakeReservation.setText(texts.getString("btnMakeReservation.text"));
		btnMakeReservation.setToolTipText(texts.getString("btnMakeReservation.toolTip"));

		btnBack.setText(texts.getString("btnBack.text"));
		btnBack.setToolTipText(texts.getString("btnBack.toolTip"));
		btnBack.setMnemonic(texts.getString("btnBack.mnemonic").charAt(0));

	}

	/**
	 * Sets the information about the castle to be shown in the panel (the text
	 * fields), and image and scales it. It also sets the default button
	 * (btnMakeReservation), and localizes the information
	 * 
	 * @param castle is the castle to take the information from
	 */
	public void setInformationInCastleWindow(Castle castle) {
//		this.castle = castle;
		txtCastleName.setText(castle.getName());
		txtCastleCountry.setText(castle.getCountry());
		txtCastleDescription.setText(castle.getDescription());
		txtCastleEnchantments.setText(castle.getEnchantments());
		txtCastlePrice.setText(mw.localizeMoney(castle.getPrice()));

		// lblCastleImage.setIcon(image);
		image = new ImageIcon((CastlePanel.class.getResource("/img/" + castle.getCode() + ".png")));
		scaleImage(scrPnDescription.getWidth(), scrPnDescription.getHeight());
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				scaleImage(scrPnDescription.getWidth(), scrPnDescription.getHeight());
			}

		});

		getRootPane().setDefaultButton(btnMakeReservation);
		localize();
	}

	/**
	 * Adjusts the text of the description of th4e castle to the width of the panel.
	 * So the maximum information is shown without scrolling
	 */
	public void adjustText() {
		int width = scrPnDescription.getWidth();
		String[] words = txtCastleDescription.getText().split(" ");

		String text = "";
		int numberCharacters = 0;
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replace("\n", "");
			if (numberCharacters > width / 6) { // 6 is the number of width that a character occupies
				text += "\n";
				numberCharacters = 0;
			}
			numberCharacters += words[i].length() + 1; // plus space
			text += words[i] + " ";
		}

		txtCastleDescription.setText(text);
	}

	/**
	 * Scales the image of the castle to the width and height passed as parameter
	 * 
	 * @param width  is the new width
	 * @param height is the new height
	 */
	private void scaleImage(int width, int height) {
		Image imageResized = image.getImage();
		int min = Math.min(width, height);
		imageResized = imageResized.getScaledInstance(min, min, Image.SCALE_FAST);
		lblImageCastle.setIcon(new ImageIcon(imageResized));
	}

	private JPanel getPnCenterInfoCastle() {
		if (pnCenterInfoCastle == null) {
			pnCenterInfoCastle = new JPanel();
			pnCenterInfoCastle.setLayout(new GridLayout(1, 0, 10, 10));
			pnCenterInfoCastle.setBackground(MainWindow.COLOR_BACKGROUND);

			pnCenterInfoCastle.add(getLblImageCastle());
			pnCenterInfoCastle.add(getScrPnDescription());
		}
		return pnCenterInfoCastle;
	}

	private JPanel getPnNorthInfoCastle() {
		if (pnNorthInfoCastle == null) {
			pnNorthInfoCastle = new JPanel();
			pnNorthInfoCastle.setLayout(new GridLayout(1, 0, 10, 10));
			pnNorthInfoCastle.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnNorthInfoCastle.add(getPnCastleName());
			pnNorthInfoCastle.add(getPnCastleCountry());
		}
		return pnNorthInfoCastle;
	}

	private JPanel getPnSouthInfoCastle() {
		if (pnSouthInfoCastle == null) {
			pnSouthInfoCastle = new JPanel();
			pnSouthInfoCastle.setLayout(new BorderLayout(10, 10));
			pnSouthInfoCastle.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnSouthInfoCastle.add(getPnMainInfo(), BorderLayout.CENTER);
			pnSouthInfoCastle.add(getPnCastleInfoButtons(), BorderLayout.EAST);
		}
		return pnSouthInfoCastle;
	}

	private JPanel getPnMainInfo() {
		if (pnMainInfo == null) {
			pnMainInfo = new JPanel();
			pnMainInfo.setLayout(new GridLayout(0, 1, 0, 5));
			pnMainInfo.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);

			pnMainInfo.add(getPnCastleEnchantments());
			pnMainInfo.add(getPnCastlePrice());
		}
		return pnMainInfo;
	}

	private JPanel getPnCastleEnchantments() {
		if (pnCastleEnchantments == null) {
			pnCastleEnchantments = new JPanel();
			pnCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnCastleEnchantments.setLayout(new BorderLayout(5, 0));

			pnCastleEnchantments.add(getLblCastleEnchantments(), BorderLayout.WEST);
			pnCastleEnchantments.add(getTxtCastleEnchantments());
		}
		return pnCastleEnchantments;
	}

	private JLabel getLblCastleEnchantments() {
		if (lblCastleEnchantments == null) {
			lblCastleEnchantments = new JLabel();
			lblCastleEnchantments.setFont(new Font("Arial", Font.BOLD, 12));
//			lblCastleEnchantments.setText("Enchantments:");
			lblCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastleEnchantments;
	}

	private JTextField getTxtCastleEnchantments() {
		if (txtCastleEnchantments == null) {
			txtCastleEnchantments = new JTextField();
			txtCastleEnchantments.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleEnchantments.setEditable(false);
			txtCastleEnchantments.setColumns(10);
			txtCastleEnchantments.setBorder(null);
			txtCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return txtCastleEnchantments;
	}

	private JPanel getPnCastlePrice() {
		if (pnCastlePrice == null) {
			pnCastlePrice = new JPanel();
			pnCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnCastlePrice.setLayout(new BorderLayout(5, 0));

			pnCastlePrice.add(getLblCastlePrice(), BorderLayout.WEST);
			pnCastlePrice.add(getTxtCastlePrice());
		}
		return pnCastlePrice;
	}

	private JLabel getLblCastlePrice() {
		if (lblCastlePrice == null) {
			lblCastlePrice = new JLabel();
			lblCastlePrice.setFont(new Font("Arial", Font.BOLD, 12));
//			lblCastlePrice.setText("Price:");
			lblCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastlePrice;
	}

	private JTextField getTxtCastlePrice() {
		if (txtCastlePrice == null) {
			txtCastlePrice = new JTextField();
			txtCastlePrice.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastlePrice.setEditable(false);
			txtCastlePrice.setColumns(10);
			txtCastlePrice.setBorder(null);
			txtCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return txtCastlePrice;
	}

	private JPanel getPnCastleInfoButtons() {
		if (pnCastleInfoButtons == null) {
			pnCastleInfoButtons = new JPanel();
			pnCastleInfoButtons.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnCastleInfoButtons.add(getBtnBack());

			pnCastleInfoButtons.add(getBtnMakeReservation());
		}
		return pnCastleInfoButtons;
	}

	private JButton getBtnMakeReservation() {
		if (btnMakeReservation == null) {
			btnMakeReservation = new JButton();
			btnMakeReservation.setFont(new Font("Arial", Font.BOLD, 12));
			btnMakeReservation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mw.showPnReservation();
				}
			});
			btnMakeReservation.setForeground(new Color(255, 255, 255));
			btnMakeReservation.setBackground(MainWindow.COLOR_GREEN);
//			btnMakeReservation.setToolTipText("Make reservation");
//			btnMakeReservation.setMnemonic('M');
		}
		return btnMakeReservation;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton();
			btnBack.setFont(new Font("Arial", Font.BOLD, 12));
			btnBack.setForeground(new Color(255, 255, 255));
			btnBack.setBackground(MainWindow.COLOR_BUTTONS);
			btnBack.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mw.showPnMainWindow();
				}
			});
		}
		return btnBack;
	}

	private JPanel getPnCastleCountry() {
		if (pnCastleCountry == null) {
			pnCastleCountry = new JPanel();
			pnCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnCastleCountry.setLayout(new BorderLayout(5, 0));

			pnCastleCountry.add(getLblCastleCountry(), BorderLayout.WEST);
			pnCastleCountry.add(getTxtCastleCountry());
		}
		return pnCastleCountry;
	}

	private JLabel getLblCastleCountry() {
		if (lblCastleCountry == null) {
			lblCastleCountry = new JLabel();
			lblCastleCountry.setFont(new Font("Arial", Font.BOLD, 12));
//			lblCastleCountry.setText("Country:");
			lblCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return lblCastleCountry;
	}

	private JTextField getTxtCastleCountry() {
		if (txtCastleCountry == null) {
			txtCastleCountry = new JTextField();
			txtCastleCountry.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleCountry.setEditable(false);
			txtCastleCountry.setColumns(10);
			txtCastleCountry.setBorder(null);
			txtCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return txtCastleCountry;
	}

	private JPanel getPnCastleName() {
		if (pnCastleName == null) {
			pnCastleName = new JPanel();
			pnCastleName.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
			pnCastleName.setLayout(new BorderLayout(5, 0));

			pnCastleName.add(getLblCastleName(), BorderLayout.WEST);
			pnCastleName.add(getTxtCastleName());
		}
		return pnCastleName;
	}

	private JLabel getLblCastleName() {
		if (lblCastleName == null) {
			lblCastleName = new JLabel();
			lblCastleName.setFont(new Font("Arial", Font.BOLD, 12));
//			lblCastleName.setText("Castle:");
			lblCastleName.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastleName;
	}

	private JTextField getTxtCastleName() {
		if (txtCastleName == null) {
			txtCastleName = new JTextField();
			txtCastleName.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleName.setEditable(false);
			txtCastleName.setColumns(10);
			txtCastleName.setBorder(null);
			txtCastleName.setBackground(MainWindow.COLOR_BACKGROUND_DARKER);
		}
		return txtCastleName;
	}

	private JLabel getLblImageCastle() {
		if (lblImageCastle == null) {
			lblImageCastle = new JLabel("");
			lblImageCastle.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImageCastle;
	}

	private JScrollPane getScrPnDescription() {
		if (scrPnDescription == null) {
			scrPnDescription = new JScrollPane();
//			scrPnDescription.setBorder(new LineBorder(new Color(130, 135, 144)));
			scrPnDescription.setBorder(new LineBorder(MainWindow.COLOR_BACKGROUND_DARKER));
			scrPnDescription.setViewportView(getTxtCastleDescription());
			scrPnDescription.setColumnHeaderView(getLblCastleDescription());
			scrPnDescription.setBackground(MainWindow.COLOR_BACKGROUND);
			scrPnDescription.getColumnHeader().setBackground(MainWindow.COLOR_BACKGROUND);

		}
		return scrPnDescription;
	}

	private JTextArea getTxtCastleDescription() {
		if (txtCastleDescription == null) {
			txtCastleDescription = new JTextArea();
			txtCastleDescription.setFont(new Font("Arial", Font.PLAIN, 12));
			txtCastleDescription.setBackground(MainWindow.COLOR_BACKGROUND);

		}
		return txtCastleDescription;
	}

	private JLabel getLblCastleDescription() {
		if (lblCastleDescription == null) {
			lblCastleDescription = new JLabel();
			lblCastleDescription.setFont(new Font("Arial", Font.BOLD, 12));
//			lblCastleDescription.setToolTipText("");
//			lblCastleDescription.setDisplayedMnemonic('D');
			lblCastleDescription.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblCastleDescription;
	}
}
