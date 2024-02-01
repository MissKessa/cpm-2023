package uo.cpm.p6.model;

public abstract class Casilla {
	
	private int puntos;
	private String tipo;
	private String imagen;
	

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	public abstract int descubrir (int puntosJuego);
	public abstract boolean isEncontrado();
	
}

