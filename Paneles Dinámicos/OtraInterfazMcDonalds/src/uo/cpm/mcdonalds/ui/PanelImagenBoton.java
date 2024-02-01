package uo.cpm.mcdonalds.ui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.LineBorder;

import uo.cpm.mcdonalds.model.Articulo;

public class PanelImagenBoton extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lbTipo;
	private JLabel lblImagen;
	private JPanel panel;
	private JButton btAñadir;
	private VentanaPrincipal vP;
	private Articulo articulo;
	private JLabel lbDenominacion;
	private JLabel lblPrecio;
	private ImageIcon imagen = null;
	private Adaptar aD;

	public PanelImagenBoton(VentanaPrincipal vP, Articulo a)  {
		this.vP = vP;
		this.articulo = a;
		// Objeto receptor del evento para adaptar la imagen del articulo a la label donde se muestra
		aD = new Adaptar();
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		setBackground(Color.white);
		setLayout(new GridLayout(1,0,0,0));
		add(getLbTipo());
		add(getLblImagen());
		add(getLbDenominacion());
		add(getLblPrecio());
		add(getPanel());
	}

	private JLabel getLbTipo() {
		if (lbTipo == null) {
			lbTipo = new JLabel();
			lbTipo.setBackground(Color.WHITE);
			lbTipo.setText("  "+articulo.getTipo());	
		}
		return lbTipo;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			imagen = new ImageIcon (PanelImagenBoton.class.getResource("/img/"+articulo.getCodigo()+".png"));
			lblImagen.setBackground(Color.WHITE);
			lblImagen.addComponentListener(aD);
		}
		return lblImagen;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 30));
			panel.add(getBtAñadir());
		}
		return panel;
	}
	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("Añadir");
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					vP.añadirAPedido(articulo);
				}
			});
		}
		return btAñadir;
	}
	
	private JLabel getLbDenominacion() {
		if (lbDenominacion == null) {
			lbDenominacion = new JLabel();
			lbDenominacion.setText(articulo.getDenominacion());
		}
		return lbDenominacion;
	}
	
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel();
			lblPrecio.setFont(new Font("Arial Black", Font.BOLD, 14));
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrecio.setText(String.valueOf(articulo.getPrecio()));
		}
		return lblPrecio;
	}
	
	private void adaptarImagenLabel(){
		  Image imgOriginal = imagen.getImage();
		  Image imgEscalada = imgOriginal.getScaledInstance((int) (90), (int) (90),Image.SCALE_FAST);
		  getLblImagen().setIcon(new ImageIcon(imgEscalada));
		}
	
	class Adaptar extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			adaptarImagenLabel();
		}
	}
}
