package uo.cpm.module.ui.panels;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import uo.cpm.module.ui.MainWindow;

/**
 * It's the panel shown at the end of the reservation process. It justs to
 * notify the user that the reservation was save correctly and when it clicks
 * the btnDone the app initializes again.
 * 
 * @author paula
 *
 */
public class DoneWindowPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * It's a reference to the main window
	 */
	private MainWindow mw;

	private JLabel lblDoneMessage;
	private JButton btnDone;

	/**
	 * It creates the panel
	 * 
	 * @param mw is a reference to the main window
	 */
	public DoneWindowPanel(MainWindow mw) {
		this.mw = mw;

		setLayout(new BorderLayout(0, 0));
		setBackground(MainWindow.COLOR_BACKGROUND);

		add(getLblDoneMessage(), BorderLayout.CENTER);
		add(getBtnDone(), BorderLayout.SOUTH);
	}

	/**
	 * It localizes the panel by the locale chosen in the main window, and it sets
	 * as default button btnDone
	 */
	public void localize() {
		ResourceBundle texts = mw.getTexts();

		lblDoneMessage.setText(texts.getString("lblDoneMessage.text"));
		btnDone.setText(texts.getString("btnDone.text"));
		btnDone.setToolTipText(texts.getString("btnDone.toolTip"));

		getRootPane().setDefaultButton(btnDone);
	}

	private JLabel getLblDoneMessage() {
		if (lblDoneMessage == null) {
			lblDoneMessage = new JLabel();
			lblDoneMessage.setFont(new Font("Arial", Font.ITALIC, 12));
			lblDoneMessage.setHorizontalAlignment(SwingConstants.CENTER);
			lblDoneMessage.setBackground(MainWindow.COLOR_BACKGROUND);
		}
		return lblDoneMessage;
	}

	private JButton getBtnDone() {
		if (btnDone == null) {
			btnDone = new JButton();
			btnDone.setFont(new Font("Arial", Font.BOLD, 12));
			btnDone.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mw.initialized();
				}
			});

			btnDone.setBackground(MainWindow.COLOR_BUTTONS);
			btnDone.setForeground(MainWindow.COLOR_BUTTONS_LETTER);
		}
		return btnDone;
	}
}
