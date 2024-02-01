package uo.cpm.p6.service;

import uo.cpm.p6.game.Juego;
import uo.cpm.p6.model.Tablero;

public class SpaceInvaders {

	private Juego juego = new Juego();

	public Tablero getTablero()
	{
		return juego.getTablero();
	}

	public void inicializar()
	{
		juego.inicializarJuego();
	}

	public void dipara(int i)
	{
		juego.dispara(i);
	}

	public boolean isPartidaFinalizada()
	{
		return juego.isPartidaFinalizada();
	}
	
	public int getPuntuacion()
	{
		return juego.getPuntos();
	}

	public void lanzarDado()
	{
		juego.lanzar();
	}

	public int getDisparos()
	{
		return juego.getDisparos();
	}

	public String obtenerImagen(int i) 
	{
		return juego.obtenerImagen(i);
	}

}