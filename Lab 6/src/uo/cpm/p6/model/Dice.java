package uo.cpm.p6.model;

import uo.cpm.p6.rules.Game;

public class Dice {
	
	public static int launch()
	{ 
		return ((int) (Math.random() * Game.MAX_SHOTS) + 1);
	}


}
