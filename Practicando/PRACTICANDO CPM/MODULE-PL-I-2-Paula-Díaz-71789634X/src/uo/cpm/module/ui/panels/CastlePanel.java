package uo.cpm.module.ui.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import uo.cpm.module.model.Castle;
import uo.cpm.module.ui.MainWindow;

/**
 * Panel shown in the list of castles with the name, country, image,
 * enchantments and price. It has the view button to go to the next panel and
 * see a more detailed view
 * 
 * @author paula
 *
 */
public class CastlePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	/**
	 * ResourceBundle with the texts for the locale chosen
	 */
	private ResourceBundle texts;
	/**
	 * Castle associated to this panel
	 */
	private Castle castle;
	private MainWindow mw;

	/**
	 * Label with the image of the castle
	 */
	private JLabel lblImageCastle;
	private ImageIcon image = null;
	/**
	 * Panel containing the name, country, enchantments and price of the castle
	 */
	private JPanel pnCastleInfo;
	private JPanel pnCastleName;
	private JPanel pnCastleCountry;
	private JPanel pnCastleEnchantments;
	private JPanel pnCastlePrice;

	private JLabel lblCastleName;
	private JTextField txtCastleName;

	private JLabel lblCastleCountry;
	private JTextField txtCastleCountry;

	private JLabel lblCastleEnchantments;
	private JTextField txtCastleEnchantments;

	private JLabel lblCastlePrice;
	private JTextField txtCastlePrice;
	/**
	 * Panel containing the button View
	 */
	private JPanel pnView;
	private JButton btnView;

	private int imageHeight;

	/**
	 * Create the panel and localize it
	 */
	public CastlePanel(MainWindow mw, Castle castle) {
		this.mw = mw;
		this.texts = mw.getTexts();
		this.castle = castle;

		setLayout(new BorderLayout(10, 0));
		setBackground(MainWindow.COLOR_BACKGROUND);
		setBorder(new LineBorder(MainWindow.COLOR_BUTTONS));

		add(getLblImageCastle(), BorderLayout.WEST);
		add(getPnCastleInfo(), BorderLayout.CENTER);
		add(getPnView(), BorderLayout.SOUTH);

		localize();
	}

	/**
	 * Localizes the texts for this panel according to the locale chosen
	 */
	private void localize() {
		lblImageCastle.setToolTipText(texts.getString("lblImageCastle.toolTip"));

		lblCastleName.setText(texts.getString("lblCastleName.text"));
		txtCastleName.setToolTipText(texts.getString("txtCastleName.toolTip"));

		lblCastleCountry.setText(texts.getString("lblCastleCountry.text"));
		txtCastleCountry.setToolTipText(texts.getString("txtCastleCountry.toolTip"));

		lblCastleEnchantments.setText(texts.getString("lblCastleEnchantments.text"));
		txtCastleEnchantments.setToolTipText(texts.getString("txtCastleEnchantments.toolTip"));

		lblCastlePrice.setText(texts.getString("lblCastlePrice.text"));
		txtCastlePrice.setToolTipText(texts.getString("txtCastlePrice.toolTip"));

		btnView.setText(texts.getString("btnView.text"));
		btnView.setToolTipText(texts.getString("btnView.toolTip"));
	}

	private JLabel getLblImageCastle() {
		if (lblImageCastle == null) {
			lblImageCastle = new JLabel();
			lblImageCastle.setHorizontalAlignment(SwingConstants.CENTER);
			// lblImageCastle.setToolTipText("Image of the castle");
			scaleImage(90);
			// lblImageCastle.addComponentListener(aD);
		}
		return lblImageCastle;
	}

	/**
	 * Scales the image of the castle according to the height pass as parameter
	 * 
	 * @param height of the image
	 */
	public void scaleImage(int height) {
		image = new ImageIcon((CastlePanel.class.getResource("/img/" + castle.getCode() + ".png")));
		Image imageResized = image.getImage();
		imageResized = imageResized.getScaledInstance(height, height, Image.SCALE_FAST);
		imageHeight = height;
		lblImageCastle.setIcon(new ImageIcon(imageResized));
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public int getPnHeight() {
		return pnCastleInfo.getHeight();
	}

	private JPanel getPnCastleInfo() {
		if (pnCastleInfo == null) {
			pnCastleInfo = new JPanel();
			pnCastleInfo.setBackground(MainWindow.COLOR_BACKGROUND);
			pnCastleInfo.setLayout(new GridLayout(2, 0, 10, 5));

			pnCastleInfo.add(getPnCastleName());
			pnCastleInfo.add(getPnCastleCountry());
			pnCastleInfo.add(getPnCastleEnchantments());
			pnCastleInfo.add(getPnCastlePrice());
		}
		return pnCastleInfo;
	}

	private JPanel getPnCastleName() {
		if (pnCastleName == null) {
			pnCastleName = new JPanel();
			pnCastleName.setBackground(MainWindow.COLOR_BACKGROUND);
			pnCastleName.setLayout(new BorderLayout(5, 0));

			pnCastleName.add(getLblCastleName(), BorderLayout.WEST);
			pnCastleName.add(getTxtCastleName());
		}
		return pnCastleName;
	}

	private JPanel getPnCastleCountry() {
		if (pnCastleCountry == null) {
			pnCastleCountry = new JPanel();
			pnCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND);
			pnCastleCountry.setLayout(new BorderLayout(5, 0));

			pnCastleCountry.add(getLblCastleCountry(), BorderLayout.WEST);
			pnCastleCountry.add(getTxtCastleCountry());
		}
		return pnCastleCountry;
	}

	private JPanel getPnCastleEnchantments() {
		if (pnCastleEnchantments == null) {
			pnCastleEnchantments = new JPanel();
			pnCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND);
			pnCastleEnchantments.setLayout(new BorderLayout(5, 0));

			pnCastleEnchantments.add(getLblCastleEnchantments(), BorderLayout.WEST);
			pnCastleEnchantments.add(getTxtCastleEnchantments());
		}
		return pnCastleEnchantments;
	}

	private JPanel getPnCastlePrice() {
		if (pnCastlePrice == null) {
			pnCastlePrice = new JPanel();
			pnCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND);
			pnCastlePrice.setLayout(new BorderLayout(5, 0));

			pnCastlePrice.add(getLblCastlePrice(), BorderLayout.WEST);
			pnCastlePrice.add(getTxtCastlePrice());
		}
		return pnCastlePrice;
	}

	private JLabel getLblCastleName() {
		if (lblCastleName == null) {
			// lblCastleName = new JLabel("Castle:");
			lblCastleName = new JLabel();
			lblCastleName.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastleName.setBackground(MainWindow.COLOR_BACKGROUND);
			lblCastleName.setLabelFor(getTxtCastleName());
		}
		return lblCastleName;
	}

	private JTextField getTxtCastleName() {
		if (txtCastleName == null) {
			txtCastleName = new JTextField();
			txtCastleName.setFont(new Font("Arial", Font.PLAIN, 12));
			// txtCastleName.setToolTipText("Castle name");
			txtCastleName.setBorder(null);
			txtCastleName.setBackground(MainWindow.COLOR_BACKGROUND);
			txtCastleName.setText(castle.getName());
			txtCastleName.setEditable(false);
			txtCastleName.setColumns(10);
		}
		return txtCastleName;
	}

	private JLabel getLblCastleCountry() {
		if (lblCastleCountry == null) {
			// lblCastleCountry = new JLabel("Country:");
			lblCastleCountry = new JLabel();
			lblCastleCountry.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND);
			lblCastleCountry.setLabelFor(getTxtCastleCountry());
		}
		return lblCastleCountry;
	}

	private JTextField getTxtCastleCountry() {
		if (txtCastleCountry == null) {
			txtCastleCountry = new JTextField();
			txtCastleCountry.setFont(new Font("Arial", Font.PLAIN, 12));
			// txtCastleCountry.setToolTipText("Country of the castle");
			txtCastleCountry.setBorder(null);
			txtCastleCountry.setBackground(MainWindow.COLOR_BACKGROUND);
			txtCastleCountry.setText(castle.getCountry());
			txtCastleCountry.setEditable(false);
			txtCastleCountry.setColumns(10);
		}
		return txtCastleCountry;
	}

	private JLabel getLblCastleEnchantments() {
		if (lblCastleEnchantments == null) {
			// lblCastleEnchantments = new JLabel("Enchantments:");
			lblCastleEnchantments = new JLabel();
			lblCastleEnchantments.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastleEnchantments.setLabelFor(getTxtCastleEnchantments());
		}
		return lblCastleEnchantments;
	}

	private JTextField getTxtCastleEnchantments() {
		if (txtCastleEnchantments == null) {
			txtCastleEnchantments = new JTextField();
			txtCastleEnchantments.setFont(new Font("Arial", Font.PLAIN, 12));
			// txtCastleEnchantments.setToolTipText("List of enchantments of the castle");
			txtCastleEnchantments.setBorder(null);
			txtCastleEnchantments.setBackground(MainWindow.COLOR_BACKGROUND);
			txtCastleEnchantments.setText(castle.getEnchantments());
			txtCastleEnchantments.setEditable(false);
			txtCastleEnchantments.setColumns(10);
		}
		return txtCastleEnchantments;
	}

	private JLabel getLblCastlePrice() {
		if (lblCastlePrice == null) {
			// lblCastlePrice = new JLabel("Room price:");
			lblCastlePrice = new JLabel();
			lblCastlePrice.setFont(new Font("Arial", Font.BOLD, 12));
			lblCastlePrice.setLabelFor(getTxtCastlePrice());
		}
		return lblCastlePrice;
	}

	private JTextField getTxtCastlePrice() {
		if (txtCastlePrice == null) {
			txtCastlePrice = new JTextField();
			txtCastlePrice.setFont(new Font("Arial", Font.PLAIN, 12));
			// txtCastlePrice.setToolTipText("Room price of the castle");
			txtCastlePrice.setBorder(null);
			txtCastlePrice.setBackground(MainWindow.COLOR_BACKGROUND);
			txtCastlePrice.setEditable(false);
			txtCastlePrice.setText(mw.localizeMoney(castle.getPrice()));
			txtCastlePrice.setColumns(10);
		}
		return txtCastlePrice;
	}

	private JPanel getPnView() {
		if (pnView == null) {
			pnView = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnView.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnView.setBackground(MainWindow.COLOR_BACKGROUND);

			pnView.add(getBtnView());
		}
		return pnView;
	}

	private JButton getBtnView() {
		if (btnView == null) {
			btnView = new JButton();
			btnView.setFont(new Font("Arial", Font.BOLD, 12));
			btnView.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showNextPanel();
				}
			});

//			btnView = new JButton("View");
//			btnView.setToolTipText("View information about the castle");
			btnView.setBackground(MainWindow.COLOR_BUTTONS);
			btnView.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
		}
		return btnView;

	}

	/**
	 * Sets the chosen castle and shows the next panel (the one contained the detail
	 * information of the castle)
	 */
	private void showNextPanel() {
		mw.getService().setChoosenCastle(castle);
		mw.showPnCastleInformation();
	}

//	public void scalePnInfo(int height) {
//		pnCastleInfo.setBounds(pnCastleInfo.getX(), pnCastleInfo.getY(), pnCastleInfo.getWidth(), height);
//
//	}

}
