package uo.cpm.p6.model;
import uo.cpm.p6.game.Juego;


public class Dado {
	
	public static int lanzar()
	{ 
		return ((int) (Math.random() * Juego.maxDisparos) + 1);
	}
}
