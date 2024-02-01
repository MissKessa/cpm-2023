package uo.cpm.p6.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.border.LineBorder;
import uo.cpm.p6.service.SpaceInvaders;

import java.awt.Toolkit;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lbPlaneta;
	private JPanel pnTablero;
	private SpaceInvaders spaceInvaders;
	private JLabel lblPuntos;
	private JTextField textPuntos;
	private JLabel lbNave;
	private JMenuBar menuBar;
	private JButton btDado;
	private JPanel pnDisparos;
	private JMenu mnJuego;
	private JMenuItem mntmNuevo;
	private JMenuItem mntmSalir;
	private JSeparator separator;
	private JMenu mnAyuda;
	private JMenuItem mntmContenidos;
	private JMenuItem mntmAcercaDe;
	private JSeparator separator_1;
	//PASO 2 DRAG AND DROP
	//PASO 7 DRAG AND DROP


	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
		setTitle("Invasi\u00F3n espacial");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/invader.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 383);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setJMenuBar(getMenuBar_1());
		contentPane.setLayout(null);
		contentPane.add(getPnTablero());
		contentPane.add(getLbPlaneta());
		contentPane.add(getLblPuntos());
		contentPane.add(getTextPuntos());
		textPuntos.setText(String.valueOf(spaceInvaders.getPuntuacion()));
		contentPane.add(getLbNave());
		contentPane.add(getBtDado());
		contentPane.add(getPnDisparos());
	}
	
	private JPanel getPnTablero() {
		if (pnTablero == null) {
			pnTablero = new JPanel();
			pnTablero.setBorder(new LineBorder(new Color(100, 149, 237), 5));
			pnTablero.setBackground(Color.BLACK);
			pnTablero.setBounds(45, 193, 770, 104);
			pnTablero.setLayout(new GridLayout(0, 8, 0, 0));
			rellenaTablero();
		}
		return pnTablero;
	}

	//PASO 0_1: Cambiamos los botones del tablero por Labels
	private void rellenaTablero() {
		for (int i=0;i<spaceInvaders.getTablero().getDim(); i++)
			getPnTablero().add(nuevaLabel(i));
		validate();
	}
	
	//PASO 0_2:Creamos cada Label
	private JLabel nuevaLabel (int i) {
		//Creamos y ponemos un texto (numero) a cada etiqueta
		JLabel labelTablero = new JLabel("" + i);
		//Fondo y texto en negro para que no se vean los numeros
		labelTablero.setBackground(Color.BLACK);
		labelTablero.setForeground(Color.BLACK);
		labelTablero.setBorder(new LineBorder(new Color(100, 149, 237), 3));
		//PASO 5 DRAG AND DROP
		
		//PASO 7 DRAG AND DROP
	
		return labelTablero;
	}

	private JLabel getLbPlaneta() {
		if (lbPlaneta == null) {
			lbPlaneta = new JLabel("");
			lbPlaneta.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/earth.jpg")));
			lbPlaneta.setBounds(653, 0, 183, 180);
		}
		return lbPlaneta;
	}

	private void dispara(int i) {
		spaceInvaders.dispara(i);
		representaspaceInvaders(i);
	}

	private void representaspaceInvaders(int i) {
		pintaPuntos();
		despintaDisparo();
		pintaCasilla(i);
		compruebaFin();
	}
	
	private void pintaPuntos() {
		this.getTextPuntos().setText(String.valueOf(spaceInvaders.getPuntuacion()));
	}
	
	private void compruebaFin() {
		if (spaceInvaders.isPartidaFinalizada()) {
			JOptionPane.showMessageDialog(null, "Partida finalizada", "Invasion espacial",JOptionPane.INFORMATION_MESSAGE);	
			descubreTablero();
			//PASO 8 DRAG AND DROP

		}
	}

	private void descubreTablero() {
		for (int i=0;i<pnTablero.getComponents().length; i++)
			pintaCasilla(i);
	}
		
	private void despintaDisparo() {
		getPnDisparos().remove(0);
		getPnDisparos().repaint();
		validate();
	}

	private void pintaCasilla(int i) {
		ImageIcon imagen = new ImageIcon(VentanaPrincipal.class.getResource(spaceInvaders.obtenerImagen(i)));
		((JLabel)pnTablero.getComponent(i)).setIcon(imagen);

	}
	
	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel("Puntos");
			lblPuntos.setForeground(Color.WHITE);
			lblPuntos.setFont(new Font("Tahoma", Font.PLAIN, 22));
			lblPuntos.setBounds(560, 21, 81, 22);
		}
		return lblPuntos;
	}
	
	private JTextField getTextPuntos() {
		if (textPuntos == null) {
			textPuntos = new JTextField();
			textPuntos.setBackground(Color.BLACK);
			textPuntos.setForeground(Color.GREEN);
			textPuntos.setFont(new Font("Consolas", Font.BOLD, 30));
			textPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			textPuntos.setEditable(false);
			textPuntos.setBounds(545, 57, 99, 35);
			textPuntos.setColumns(10);
		}
		return textPuntos;
	}
	
	private JLabel getLbNave() {
		if (lbNave == null) {
			lbNave = new JLabel("");
			lbNave.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/spaceship.png")));
			lbNave.setBounds(267, 0, 137, 98);
		}
		return lbNave;
	}
	
	private JButton getBtDado() {
		if (btDado == null) {
			btDado = new JButton("");
			btDado.setDisabledIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btDado.setBorderPainted(false);
			btDado.setFocusPainted(false);
			btDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarJuego();
				}
			});
			btDado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/dice.jpg")));
			btDado.setBounds(12, 13, 114, 109);
		}
		return btDado;
	}

	protected void lanzarDado() {
		spaceInvaders.lanzarDado();
	}
	
	private JPanel getPnDisparos() {
		if (pnDisparos == null) {
			pnDisparos = new JPanel();
			pnDisparos.setBackground(Color.BLACK);
			pnDisparos.setBounds(169, 98, 345, 78);
		}
		return pnDisparos;
	}
	
	public void pintaDisparos() {
		for (int i=0;i<spaceInvaders.getDisparos();i++) {
			getPnDisparos().add(nuevoDisparo());
		}
		validate();
	}

	private JLabel nuevoDisparo() {
		JLabel lbDisparo = new JLabel("");
		lbDisparo.setBorder(new LineBorder(Color.GREEN, 1));
		lbDisparo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/img/shoot.png")));
		//PASO 3 DRAG AND DROP
	
		//PASO 4 DRAG AND DROP
		
		return lbDisparo;
	}

	private void iniciarJuego() {
		lanzarDado();
		pintaDisparos();
		getBtDado().setEnabled(false);
	}
	
	//PASO 1 DRAG AND DROP

	
	//PASO 6 DRAG AND DROP

	
	protected void inicializar() {
		spaceInvaders.inicializar();
		getPnDisparos().removeAll();
		getTextPuntos().setText(String.valueOf(spaceInvaders.getPuntuacion()));
		btDado.setEnabled(true);
		//PASO 9 DRAG AND DROP
		
	}


	//PASO 9 DRAG AND DROP

	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnJuego());
			menuBar.add(getMnAyuda());
		}
		return menuBar;
	}
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu("Juego");
			mnJuego.setMnemonic('J');
			mnJuego.add(getMntmNuevo());
			mnJuego.add(getSeparator());
			mnJuego.add(getMntmSalir());
		}
		return mnJuego;
	}
	private JMenuItem getMntmNuevo() {
		if (mntmNuevo == null) {
			mntmNuevo = new JMenuItem("Nuevo");
			mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
			mntmNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					inicializar();
				}
			});
			mntmNuevo.setMnemonic('N');
		}
		return mntmNuevo;
	}
	private JMenuItem getMntmSalir() {
		if (mntmSalir == null) {
			mntmSalir = new JMenuItem("Salir");
			mntmSalir.setMnemonic('S');
		}
		return mntmSalir;
	}
	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu("Ayuda");
			mnAyuda.setMnemonic('A');
			mnAyuda.add(getMntmContenidos());
			mnAyuda.add(getSeparator_1());
			mnAyuda.add(getMntmAcercaDe());
		}
		return mnAyuda;
	}
	private JMenuItem getMntmContenidos() {
		if (mntmContenidos == null) {
			mntmContenidos = new JMenuItem("Contenidos");
			mntmContenidos.setMnemonic('C');
			mntmContenidos.setEnabled(false);
			mntmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mntmContenidos;
	}
	private JMenuItem getMntmAcercaDe() {
		if (mntmAcercaDe == null) {
			mntmAcercaDe = new JMenuItem("Acerca de");
			mntmAcercaDe.setMnemonic('r');
		}
		return mntmAcercaDe;
	}
	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}
}
