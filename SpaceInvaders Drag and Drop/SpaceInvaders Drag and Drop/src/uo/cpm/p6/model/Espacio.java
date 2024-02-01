package uo.cpm.p6.model;

public class Espacio extends Casilla {

	public Espacio() {
		setPuntos(-200);
		setTipo("Espacio");
		setImagen("/img/space.jpg");
	}
	public int descubrir (int puntosJuego) {
		return getPuntos();
		
	}
	public  boolean isEncontrado() {
	    return false;
	}
	
}