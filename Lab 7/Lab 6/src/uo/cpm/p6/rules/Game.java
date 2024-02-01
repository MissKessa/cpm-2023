package uo.cpm.p6.rules;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.model.Dice;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Meteorite;
import uo.cpm.p6.model.Space;

public class Game {

	public enum Level {
		EASY, INTERMEDIATE, HARD;
	}

	int score;
	int shots;
	private Board board;
	private boolean invaderFound;
	private boolean meteoriteFound;
	private Level level;

	public Board getBoard() {
		return board;
	}

	public Game(Level level) {
		this.level = level;
		initialize(level);
	}

	public void initialize(Level level) {
		board = new Board(level);
		score = 800;
		shots = 0;
	}

	public void shoot(int i) {
		shots--;

		if (board.getCells()[i] instanceof Invader) {
			((Invader) board.getCells()[i]).setErased(true);
			invaderFound = true;
		}
		if (board.getCells()[i] instanceof Meteorite) {
			((Meteorite) board.getCells()[i]).setErased(true);
			meteoriteFound = true;
		}
		if (meteoriteFound)
			score = 0;
		else
			score = score + board.getCells()[i].discover();

	}

	private void shoot(Invader invader) {
		System.out.println("Invader!");
	}

	private void shoot(Space space) {
		System.out.println("Space!");
	}

	private void shoot(Meteorite meteorite) {
		System.out.println("Meteorite!");
	}

	public boolean isGameOver() {
		if (invaderFound || shots == 0 || meteoriteFound) {
			setShots(0);
			return true;
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	public void launch() {
		setShots(Dice.launch(this.level));
	}

	public int getShots() {
		return shots;
	}

	private void setShots(int shots) {
		this.shots = shots;
	}

	public String getFinalMessage() {
		if (invaderFound)
			return "You found an invader!";
		else if (meteoriteFound)
			return "A meteorite has destroyed you!";
		return "You ran out of shots!";
	}

	public int getDimension() {
		return board.getDimension();

	}

	public Level getLevel() {
		return level;
	}

}
