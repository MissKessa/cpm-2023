package uo.cpm.p6.service;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.rules.Game;

public class SpaceInvaders {

	private Game game = new Game();

	public Board getBoard() {
		return game.getBoard();
	}

	public void initialize() {
		game.initialize();
	}

	public void shoot(int i) {
		game.shoot(i);
	}

	public boolean isGameOver() {
		return game.isGameOver();
	}

	public int getScore() {
		return game.getScore();
	}

	public void launch() {
		game.launch();
	}

	public int getShots() {
		return game.getShots();
	}

	public String getFinalMessage() {
		return game.getFinalMessage();
	}

}