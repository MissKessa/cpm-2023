package uo.cpm.examen.logic;

public class Dice {
	public static int lanzar() {
		return ((int) (Math.random() * (5 - 1 + 1) + 1) * 100);
	}
}
