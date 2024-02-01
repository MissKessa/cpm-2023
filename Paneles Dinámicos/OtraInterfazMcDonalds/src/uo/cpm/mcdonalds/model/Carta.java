package uo.cpm.mcdonalds.model;

import java.util.*;

import uo.cpm.mcdonalds.util.FileUtil;

public class Carta {

	private static final String FICHERO_ARTICULOS = "files/articulos.dat";
	private List<Articulo> listaArticulos = null;
	private int numArticulos;

	public Carta() {
		listaArticulos = new ArrayList<Articulo>();
		cargarArticulos();
		numArticulos = listaArticulos.size();
	}

	public int getNumArticulos() {
		return numArticulos;
	}

	private void cargarArticulos() {
		FileUtil.loadFile(FICHERO_ARTICULOS, listaArticulos);
	}

	public Articulo[] getArticulos() {
		Articulo[] articulos = listaArticulos.toArray(new Articulo[listaArticulos.size()]);
		return articulos;
	}

	public List<Articulo> getListaArticulos() {
		return listaArticulos;
	}
}