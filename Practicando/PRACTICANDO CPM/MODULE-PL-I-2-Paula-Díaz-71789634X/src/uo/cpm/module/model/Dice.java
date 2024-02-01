package uo.cpm.module.model;

/**
 * It's the dice for the game
 * 
 * @author paula
 *
 */
public class Dice {
	/**
	 * 
	 * @return generates randomly 1 or 2
	 */
	public static int launch() {
		return ((int) ((Math.random() * 2) + 1));
	}
}
