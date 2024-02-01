package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnButtons;
	private JPanel pnDateTime;
	private JPanel pnFlag;
	private JButton btSpanish;
	private JButton btEnglish;
	private JButton btFrench;
	private JLabel lbDate;
	private JLabel lbTime;
	private JLabel lbFlag;
	private JTextArea taText;

	private ResourceBundle texts;
	private JButton btItaly;
	private JLabel lblCountry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
		setTitle("Ejemplo de Internacionalización");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnButtons(), BorderLayout.NORTH);
		contentPane.add(getPnDateTime(), BorderLayout.SOUTH);
		contentPane.add(getPnFlag(), BorderLayout.WEST);
		contentPane.add(getTaText(), BorderLayout.CENTER);
		localize(Locale.getDefault(Locale.Category.FORMAT));
	}

	private void localize(Locale localization) {

		texts = ResourceBundle.getBundle("rcs/messages", localization);

		String flagPicture = "/img/" + texts.getString("Image");
		String title = texts.getString("Title");
		setTitle(title);
		String text = texts.getString("Text");
		String country = texts.getString("Country");
		getLblCountry().setText(country);

		getTaText().setText(text);

		String tooltipSpanish = texts.getString("tooltipSpain");
		getBtSpanish().setToolTipText(tooltipSpanish);

		String tooltipEnglish = texts.getString("tooltipUK");
		getBtEnglish().setToolTipText(tooltipEnglish);

		String tooltipItalian = texts.getString("tooltipItaly");
		getBtItaly().setToolTipText(tooltipItalian);

		String tooltipFrench = texts.getString("tooltipFrance");
		getBtFrench().setToolTipText(tooltipFrench);

		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource(flagPicture)));
		getLbFlag().setIcon(new ImageIcon(MainWindow.class.getResource(flagPicture)));

		Date dateTime = new Date();

		DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.LONG, localization);
		DateFormat formatDate = DateFormat.getDateInstance(DateFormat.LONG, localization);
		lbTime.setText(formatoHora.format(dateTime));
		lbDate.setText(formatDate.format(dateTime).toString());
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnButtons.setBackground(Color.WHITE);
			FlowLayout fl_pnButtons = (FlowLayout) pnButtons.getLayout();
			fl_pnButtons.setAlignment(FlowLayout.RIGHT);
			pnButtons.add(getBtSpanish());
			pnButtons.add(getBtEnglish());
			pnButtons.add(getBtFrench());
			pnButtons.add(getBtItaly());
		}
		return pnButtons;
	}

	private JPanel getPnDateTime() {
		if (pnDateTime == null) {
			pnDateTime = new JPanel();
			pnDateTime.setLayout(new GridLayout(1, 2, 0, 0));
			pnDateTime.add(getLbDate());
			pnDateTime.add(getLbTime());
		}
		return pnDateTime;
	}

	private JPanel getPnFlag() {
		if (pnFlag == null) {
			pnFlag = new JPanel();
			pnFlag.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			pnFlag.setBackground(Color.WHITE);
			pnFlag.add(getLbFlag());
			pnFlag.add(getLblCountry());
		}
		return pnFlag;
	}

	private JButton getBtSpanish() {
		if (btSpanish == null) {
			btSpanish = new JButton("es");
			btSpanish.setMnemonic('e');
			btSpanish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					localize(new Locale("es"));
				}
			});
		}
		return btSpanish;
	}

	private JButton getBtEnglish() {
		if (btEnglish == null) {
			btEnglish = new JButton("en");
			btEnglish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("en"));
				}
			});
			btEnglish.setMnemonic('n');
		}
		return btEnglish;
	}

	private JButton getBtFrench() {
		if (btFrench == null) {
			btFrench = new JButton("fr");
			btFrench.setMnemonic('f');
			btFrench.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					localize(new Locale("fr"));
				}
			});
		}
		return btFrench;
	}

	private JLabel getLbDate() {
		if (lbDate == null) {
			lbDate = new JLabel("");
		}
		return lbDate;
	}

	private JButton getBtItaly() {
		if (btItaly == null) {
			btItaly = new JButton("it");
			btItaly.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					localize(new Locale("it"));
				}
			});
			btItaly.setMnemonic('i');
		}
		return btItaly;
	}

	private JLabel getLbTime() {
		if (lbTime == null) {
			lbTime = new JLabel("");
		}
		return lbTime;
	}

	private JLabel getLbFlag() {
		if (lbFlag == null) {
			lbFlag = new JLabel("");
			lbFlag.setIcon(new ImageIcon(MainWindow.class.getResource("/img/spain.PNG")));
		}
		return lbFlag;
	}

	private JTextArea getTaText() {
		if (taText == null) {
			taText = new JTextArea();
			taText.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			taText.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 20));
			taText.setWrapStyleWord(true);
			taText.setLineWrap(true);
		}
		return taText;
	}

	private JLabel getLblCountry() {
		if (lblCountry == null) {
			lblCountry = new JLabel("");
		}
		return lblCountry;
	}
}
