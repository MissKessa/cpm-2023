package uo.cpm.p6.model;

public class Tablero {

	private int dim = 8;
	Casilla[] casillas;

	public Tablero() {
		casillas = new Casilla[dim];
		for (int i = 0; i < dim; i++)
			casillas[i] = new Espacio();
		colocaInvasor();
	}

	private void colocaInvasor() {
		int posicionInvasor = (int) (Math.random() * dim);
		casillas[posicionInvasor] = new Invasor();
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}

	public Casilla[] getCasillas() {
		return casillas;
	}

	public String obtenerImagen(int i) {
		return casillas[i].getImagen();
	}
}
