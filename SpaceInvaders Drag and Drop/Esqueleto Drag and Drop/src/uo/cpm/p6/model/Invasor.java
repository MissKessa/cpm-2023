package uo.cpm.p6.model;

public class Invasor extends Casilla {
	boolean encontrado;

	public Invasor() {
		setPuntos(3000);
		setImagen ( "/img/invader.jpg");
		setTipo("Invasor");
	}
	
	public boolean isEncontrado() {
		return encontrado;
	}
	
	public void setEncontrado(boolean encontrado) {
		this.encontrado = encontrado;
	}
	
	public int descubrir (int puntosJuego) {
		setEncontrado(true);
		return getPuntos();	
	}
	
}