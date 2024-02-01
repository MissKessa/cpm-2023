package uo.cpm.module.model;

import uo.cpm.module.service.Game;

/**
 * It's the board of the game with the matrix of squares, the list of gangs and
 * the leader ghost
 * 
 * @author paula
 *
 */
public class Board {
	private static final int TYPES_OF_GHOSTS = 6;

	/**
	 * It's the list of gangs in the game (one for each type of ghost)
	 */
	private Gang[] gangs = new Gang[TYPES_OF_GHOSTS];
	/**
	 * It's the ghost leader
	 */
	private Ghost leader;
	/**
	 * It's the matrix of squares (the board)
	 */
	private Square[][] squares = new Square[Game.NUMBER_OF_ROWS][Game.NUMBER_OF_COLUMNS];

	/**
	 * It's an array indicating if the gang in that position in the gangs array is
	 * full (has 3 ghosts)
	 */
	private boolean[] gangsFull = new boolean[TYPES_OF_GHOSTS];

	/**
	 * It creates the board: it creates the list of gangs with each type,
	 * initializes each square and it puts each entity in the corresponding position
	 */
	public Board() {
//		gangs[0] = new Gang(TypeEntity.ghost_1);
//		gangs[1] = new Gang(TypeEntity.ghost_2);
//		gangs[2] = new Gang(TypeEntity.ghost_3);
//		gangs[3] = new Gang(TypeEntity.ghost_4);
//		gangs[4] = new Gang(TypeEntity.ghost_5);
		for (int i = 0; i < gangs.length; i++) {
			gangs[i] = new Gang(TypeEntity.valueOf("ghost_" + (i + 1)));
		}

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[i].length; j++) {
				int[] pos = new int[] { i, j };
//				squares[i][j] = new Square(pos); // creates the square
				squares[i][j] = new Square(); // creates the square

				if (i == 0 && j == 3) { // puts the ghost leader in its position
//					leader = new Ghost(TypeEntity.ghost_leader, pos);
					leader = new Ghost(TypeEntity.ghost_leader);
					squares[i][j].setEntity(leader);
				}
				if (i == 4) { // puts the ghostBusters in the last row
//					squares[i][j].setEntity(new Ghostbuster(pos));
					squares[i][j].setEntity(new Ghostbuster());
				}

				// localizes the ghosts in their corresponding position
				if (i == 1 && (j >= 1 && j <= 4))
					localizeGhost(pos);
				else if (i == 2 && (j >= 0 && j <= 6))
					localizeGhost(pos);
				else if (i == 3 && (j >= 0 && j <= 6))
					localizeGhost(pos);
			}
		}
	}

	/**
	 * Localizes a random ghost in a random position, and it's added to its
	 * corresponding gang. If the gang is full another type of ghost is chosen
	 * 
	 * @param pos is the position in the board for the ghost
	 * @return true if the ghost has been put in the board
	 */
	private void localizeGhost(int[] pos) {
//		int type = ((int) ((Math.random() * 5) + 1));
		int type;

		while (true) {
			do {
				type = ((int) ((Math.random() * 6) + 1));
			} while (gangsFull[type - 1]);

			TypeEntity typeEnt = TypeEntity.valueOf("ghost_" + (type));
//			Ghost ghost = new Ghost(typeEnt, pos);
			Ghost ghost = new Ghost(typeEnt);

			if (gangs[type - 1].size() < Gang.MAXIMUM_NUMBER_OF_GHOSTS) { // if gang is not completed
				if (gangs[type - 1].size() == 2) // now the gang is completed
					gangsFull[type - 1] = true;

				int i = pos[0];
				int j = pos[1];
				gangs[type - 1].addGhost(ghost);
				squares[i][j].setEntity(ghost);
				break;
			} else {
				gangsFull[type - 1] = true;
			}

//			while (gangsFull[type - 1]) {
//				type = ((int) ((Math.random() * 5) + 1));
//			}

//			for (TypeEntity typeEnt : types) {
//				String typeEntString = typeEnt.toString();
//				TypeEntity[] types = TypeEntity.values();
//				if (typeEntString.substring(typeEntString.length() - 1, typeEntString.length()).equals("" + type)) {
//					Ghost ghost = new Ghost(typeEnt, pos);
//					if (gangs[type - 1].size() < 3) {
//						if (gangs[type - 1].size() == 2)
//							gangsFull[type - 1] = true;
//						int i = pos[0];
//						int j = pos[1];
//						gangs[type - 1].addGhost(ghost);
//						squares[i][j].setEntity(ghost);
//						return true;
//					} else {
//						gangsFull[type - 1] = true;
//						break;
//					}
//
//				}
//			}
		}

	}

	/**
	 * Returns the image for the entity in the square with that i and j
	 * 
	 * @param i is the row in the board
	 * @param j is the column in the board
	 * @return a String corresponding to the picture with the extension .png
	 */
	public String getImage(int i, int j) {
		return squares[i][j].getEntity().getType() + ".png";
	}

	/**
	 * It swaps the position of the given ghostbuster and the given ghost (it
	 * eliminates the ghost, it moves the ghostbuster to the ghost' square and sets
	 * the entity of the old position to null)
	 * 
	 * @param iGb is the row of the ghostbuster in the board
	 * @param iG  is the row of the ghost in the board
	 * @param j   is the column of both in the board
	 * @return if they have been swap
	 */
	public boolean swap(int iGb, int iG, int j) {
		if (iG >= 0 && squares[iG][j].getEntity() != null) {
			((Ghost) squares[iG][j].getEntity()).setEliminated(true);

			squares[iG][j].setEntity(squares[iGb][j].getEntity());
			squares[iGb][j].setEntity(null);
			return true;
		}
		return false;
	}

	/**
	 * @param i is the row in the board
	 * @param j is the column in the board
	 * @return the type of the entity in the square of the given i and j
	 */
	public TypeEntity getType(int i, int j) {
		if (squares[i][j].getEntity() == null)
			return null;
		return squares[i][j].getEntity().getType();

	}

	/**
	 * 
	 * @return if in all the gangs at least one ghost has been eliminated
	 */
	public boolean areGangsNotCompleted() {
		for (Gang gang : gangs) {
			if (gang.isCompleted())
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @return if the leader has been eliminated
	 */
	public boolean hasLeaderBeenEliminated() {
		return leader.isEliminated();
	}

}
