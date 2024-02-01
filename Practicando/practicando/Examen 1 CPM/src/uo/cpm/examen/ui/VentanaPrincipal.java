package uo.cpm.examen.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uo.cpm.examen.logic.Business;
import uo.cpm.examen.logic.Hotel;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Business business;

	private JLabel lblLogo;
	private JPanel pnFilters;
	private JButton btnApartamento4;
	private JButton btnApartamento8;
	private JButton btnHabitacionDoble;
	private JScrollPane scrollPane;
	private JPanel pnHotels;

	public VentanaPrincipal() {
		setTitle("Trivago");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/OC03.png")));
		business = new Business();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		contentPane.add(getLblLogo());
		contentPane.add(getPnFilters());
		contentPane.add(getScrollPane());
	}

	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.setBounds(10, 37, 150, 165);
			lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/OC03.png")));
		}
		return lblLogo;
	}

	private JPanel getPnFilters() {
		if (pnFilters == null) {
			pnFilters = new JPanel();
			pnFilters.setBounds(217, 20, 472, 140);
			pnFilters.setBorder(new TitledBorder(null, "Filters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnFilters.setLayout(new GridLayout(1, 0, 0, 0));
			pnFilters.add(getBtnApartamento4());
			pnFilters.add(getBtnApartamento8());
			pnFilters.add(getBtnHabitacionDoble());
		}
		return pnFilters;
	}

	private JButton getBtnApartamento4() {
		if (btnApartamento4 == null) {
			btnApartamento4 = new JButton("Apartamento 4 plazas");
			btnApartamento4.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnApartamento4.setMnemonic('4');
		}
		return btnApartamento4;
	}

	private JButton getBtnApartamento8() {
		if (btnApartamento8 == null) {
			btnApartamento8 = new JButton("Apartamento 8 Plazas");
			btnApartamento8.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnApartamento8.setMnemonic('8');
		}
		return btnApartamento8;
	}

	private JButton getBtnHabitacionDoble() {
		if (btnHabitacionDoble == null) {
			btnHabitacionDoble = new JButton("Habitaci\u00F3n doble");
			btnHabitacionDoble.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnHabitacionDoble.setMnemonic('D');
		}
		return btnHabitacionDoble;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(35, 239, 627, 255);
			scrollPane.setViewportView(getPnHotels());
		}
		return scrollPane;
	}

	private JPanel getPnHotels() {
		if (pnHotels == null) {
			pnHotels = new JPanel();
			pnHotels.setLayout(new GridLayout(0, 1, 0, 0));
			for (int i = 0; i < business.getNumberOfHotels(); i++) {
//				JPanel panel = new JPanel();
//				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
//				panel.setBounds(577 * (i + 1), 507, 192, 165);
//				JLabel label = new JLabel();
//				label.setText(business.getHotels().get(i).toString());
//				panel.add(label);
//
//				JButton button = new JButton();
//				button.setText("Reserve");
//				button.addActionListener(new AskInfo());
//				panel.add(button);

				pnHotels.add(new myPanel(business.getHotels().get(i), i));
			}
		}
		return pnHotels;
	}

	private class myPanel extends JPanel {
		private Hotel hotel;
		private JButton button;
		private JLabel label;

		public myPanel(Hotel hotel, int i) {
			super();
			this.hotel = hotel;
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			this.setBounds(577 * (i + 1), 507, 192, 165);
			JLabel label = new JLabel();
			label.setText(business.getHotels().get(i).toString());
			this.add(label);

			JButton button = new JButton();
			button.setText("Reserve");
			button.addActionListener(new AskInfo());
			this.add(button);
		}

	}

	private class AskInfo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new JDialog();
		}

	}

}