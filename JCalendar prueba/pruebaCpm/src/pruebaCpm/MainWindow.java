package pruebaCpm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

/**
 * 1. Crear un scrollpane (porque el contenido te puede crecer demasiado)
 * 
 * 2. Dentro del scrollpane, un panel con GridLaoutout forzando a tener 1
 * columna y 0 filas.
 * 
 * 3. Dentro del gridlayout, tantos paneles como productos.
 * 
 * ¿Lo tenías así? ¿Qué problema te daba
 * 
 *
 */
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel label;
	private JButton btnNewButton;
	private JTextArea textArea;
	private JCalendar calendar = new JCalendar();
	// private JPanel panel_1;
	// private JLabel lblNewLabel;
	// private JButton btnNewButton;
	// private JTextArea txtrHolanholanholan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(getScrollPane());
		calendar.getYearChooser().setStartYear(2023);

		calendar.setMinSelectableDate(new Date());
		calendar.setTodayButtonVisible(true);
		calendar.setTodayButtonText("Today");
		contentPane.add(calendar);

	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getPanel());
		}
		return scrollPane;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setSize(3000, 5000);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			for (int i = 0; i < 10; i++) {
				// JPanel a = addPanelProduct();
				// panel.add(a);
				JPanel a = addPanel_1();
				panel.add(a);

			}
		}
		return panel;
	}

	private JPanel addPanelProduct() {
		JPanel panelHotel = new JPanel();
		panelHotel.setBounds(70, 232, 5000, 50000000);
		// panelHotel.setLayout(new GridLayout(1, 0, 0, 0));
		panelHotel.add(getLblImagen());
		panelHotel.add(getLblDatos());
		panelHotel.add(getBtnNewButtonProduct());
		return panelHotel;
	}

	private JLabel getLblImagen() {
		JLabel a = new JLabel();
		a.setIcon(new ImageIcon(MainWindow.class.getResource("/pruebaCpm/todos.png")));
		return a;

	}

	private JTextArea getLblDatos() {
		JTextArea a = new JTextArea("Nombre: A\nCountry: B\tPrice: 40\nDetalles varios");
		a.setEditable(false);
		a.setBackground(getForeground());
		return a;
	}

	private JButton getBtnNewButtonProduct() {
		return new JButton("Reserve");

	}

	private JPanel addPanel_1() {
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(70, 252, 316, 80);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(getLabel(), BorderLayout.WEST);
		panel_1.add(getBtnNewButton(), BorderLayout.EAST);
		panel_1.add(getTextArea(), BorderLayout.CENTER);
		return panel_1;
	}

	private JLabel getLabel() {
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainWindow.class.getResource("/pruebaCpm/todos.png")));

		return label;
	}

	private JButton getBtnNewButton() {
		JButton btnNewButton = new JButton("New button");

		return btnNewButton;
	}

	private JTextArea getTextArea() {
		JTextArea textArea = new JTextArea();
		textArea.setText("Datos\nCountry\tPrice\nDetails");
		textArea.setEditable(false);

		return textArea;
	}
}
