package uo.cpm.p6.model;

import uo.cpm.p6.rules.Game.Level;

public class Board {
	public static final int ATTRIBUTE_DIM_POS = 0;
	public static final int DIM_EASY = 10;
	public static final int DIM_INTERMEDIATE = 8;
	public static final int DIM_HARD = 6;

	public final static int INV_EASY = 2;
	public final static int INV_INTERMEDIATE = 1;
	public final static int INV_HARD = 1;
	public static final int ATTRIBUTE_INV_POS = 1;

	public final static int MET_EASY = 0;
	public final static int MET_INTERMEDIATE = 1;
	public final static int MET_HARD = 2;
	public static final int ATTRIBUTE_MET_POS = 2;
	Cell[] cells;
	int dimension;

	public Board(Level level) {
		int[] attributes = getAttributesByLevel(level);
		this.dimension = attributes[ATTRIBUTE_DIM_POS];
		int numberOfInvaders = attributes[ATTRIBUTE_INV_POS];
		int numberOfMeteorites = attributes[ATTRIBUTE_MET_POS];

		cells = new Cell[dimension];
		for (int i = 0; i < dimension; i++)
			cells[i] = new Space(i);

		while (numberOfInvaders != 0) {
			int invaderPosition = (int) (Math.random() * dimension);
			if ((cells[invaderPosition]).isFree()) {
				cells[invaderPosition] = new Invader();
				numberOfInvaders--;
			}
		}

		while (numberOfMeteorites != 0) {
			int meteoritePosition = (int) (Math.random() * dimension);
			if ((cells[meteoritePosition]).isFree()) {
				cells[meteoritePosition] = new Meteorite();
				numberOfMeteorites--;
			}
		}
	}

	public Cell[] getCells() {
		return cells;
	}

	public int getDimension() {
		return dimension;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public String getPicture(Integer position) {
		return this.cells[position].getPicture();
	}

	public int[] getAttributesByLevel(Level level) {
		int[] attributes = new int[3];
		switch (level) {
		case EASY:
			attributes[ATTRIBUTE_DIM_POS] = Board.DIM_EASY;
			attributes[ATTRIBUTE_INV_POS] = Board.INV_EASY;
			attributes[ATTRIBUTE_MET_POS] = Board.MET_EASY;
			break;
		case INTERMEDIATE:
			attributes[ATTRIBUTE_DIM_POS] = Board.DIM_INTERMEDIATE;
			attributes[ATTRIBUTE_INV_POS] = Board.INV_INTERMEDIATE;
			attributes[ATTRIBUTE_MET_POS] = Board.MET_INTERMEDIATE;
			break;
		default:
			attributes[ATTRIBUTE_DIM_POS] = Board.DIM_HARD;
			attributes[ATTRIBUTE_INV_POS] = Board.INV_HARD;
			attributes[ATTRIBUTE_MET_POS] = Board.MET_HARD;
			break;
		}
		return attributes;
	}
}
