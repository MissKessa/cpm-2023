package uo.cpm.p6.model;

public class Board {

	public static final int DIM = 8;
	Cell[] cells;

	public Board() {
		cells = new Cell[DIM];
		for (int i = 0; i < DIM; i++)
			cells[i] = new Space(i);
		int invaderPosition = (int) (Math.random() * DIM);
		int meteoritePosition = (int) (Math.random() * DIM);
		while (meteoritePosition == invaderPosition)
			meteoritePosition = (int) (Math.random() * DIM);
		cells[meteoritePosition] = new Meteorite();
		cells[invaderPosition] = new Invader();
	}

	public Cell[] getCells() {
		return cells;
	}

	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public String getPicture(Integer position) {
		return this.cells[position].getPicture();
	}
}
