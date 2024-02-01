package uo.cpm.module.service;

import uo.cpm.module.model.Board;
import uo.cpm.module.model.Discount;
import uo.cpm.module.model.DiscountCode;
import uo.cpm.module.model.TypeEntity;

/**
 * It's the game. It contains the board and the number of throws made
 * 
 * @author paula
 *
 */
public class Game {
	/**
	 * It's the maximum number of throws
	 */
	private static final int NUMBER_OF_THROWS = 7;
	/**
	 * It's the number of rows of the board
	 */
	public static final int NUMBER_OF_ROWS = 5;
	/**
	 * It's the number of columns of the board
	 */
	public static final int NUMBER_OF_COLUMNS = 7;

	/**
	 * It's the remaining numberOfThrows
	 */
	private int numberOfThrowsMade;

	/**
	 * It¡s the board of the game
	 */
	private Board board;

	/**
	 * It creates the game: it initializes the board
	 */
	public Game() {
		board = new Board();
	}

	/**
	 * 
	 * @return if the user has won a 25% discount
	 */
	public boolean hasWonExtra25() {
		return board.hasLeaderBeenEliminated() && board.areGangsNotCompleted();
	}

	/**
	 * 
	 * @return if the user has won a 10% discount
	 */
	public boolean hasWonExtra10() {
		return !board.hasLeaderBeenEliminated() && board.areGangsNotCompleted();
	}

	/**
	 * 
	 * @return if the game has finish (there are no more throws)
	 */
	public boolean hasFinish() {
		return getRemainingThrows() == 0;
	}

	/**
	 * 
	 * @return the remaining throws (throws - throws made)
	 */
	public int getRemainingThrows() {
		return NUMBER_OF_THROWS - numberOfThrowsMade;
	}

	/**
	 * Increases by one the number of throws made
	 */
	public void increaseNumberOfThrowsMade() {
		numberOfThrowsMade++;
	}

	/**
	 * 
	 * @return the board of the game
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * 
	 * @param i is the row in the board
	 * @param j is the column in the board
	 * @return the image of the entity in the board in the given position
	 */
	public String getImage(int i, int j) {
		return board.getImage(i, j);
	}

	/**
	 * Swaps the ghosbuster and the ghost to eliminate the ghost
	 * 
	 * @param iGb is the row of the ghosbuster in the board
	 * @param iG  is the row of the ghost in the board
	 * @param j   is the column of the both in the board
	 * @return if they have been swap
	 */
	public boolean swap(int iGb, int iG, int j) {
		return board.swap(iGb, iG, j);
	}

	/**
	 * 
	 * @param i is the row in the board
	 * @param j is the column in the board
	 * @return the type of the entity in the board in the given position
	 */
	public TypeEntity getType(int i, int j) {
		return board.getType(i, j);
	}

	/**
	 * @param id
	 * @return the discount gained by the given id
	 * 
	 */
	public Discount getDiscount(String id) {
		if (id == null)
			throw new NullPointerException("Error saving discount: the id is null");
		if (id.isBlank())
			throw new NullPointerException("Error saving discount: the id is blank");

		id = id.strip();
		Discount discount = null;

		if (hasWonExtra25())
			discount = new Discount(id, DiscountCode.EXTRA25);

		else if (hasWonExtra10())
			discount = new Discount(id, DiscountCode.EXTRA10);

		return discount;
	}

	/**
	 * It initializes the game: it creates a new board and sets the number of throws
	 * made to 0
	 */
	public void initialize() {
		board = new Board();
		numberOfThrowsMade = 0;
	}
}
