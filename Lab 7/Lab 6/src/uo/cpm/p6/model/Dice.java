package uo.cpm.p6.model;

import uo.cpm.p6.rules.Game.Level;

public class Dice {
	public static final int MAX_SHOTS_HARD = 3;
	public static final int MAX_SHOTS_INTERMEDIATE = 4;
	public static final int MAX_SHOTS_EASY = 5;

	public static int launch(Level level) {
		return ((int) (Math.random() * getMaxShots(level)) + 1);
	}

	private static int getMaxShots(Level level) {
		switch (level) {
		case EASY:
			return MAX_SHOTS_EASY;
		case INTERMEDIATE:
			return MAX_SHOTS_INTERMEDIATE;
		default:
			return MAX_SHOTS_HARD;
		}
	}

}
