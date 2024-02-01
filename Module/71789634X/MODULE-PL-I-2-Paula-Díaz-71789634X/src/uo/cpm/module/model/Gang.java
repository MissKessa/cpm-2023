package uo.cpm.module.model;

import java.util.ArrayList;
import java.util.List;

/**
 * It's the group of maximum ghosts of the same type
 * 
 * @author paula
 *
 */
public class Gang {

	/**
	 * It's the maximum number of ghosts that can be in the gang
	 */
	public final static int MAXIMUM_NUMBER_OF_GHOSTS = 3;
	/**
	 * It's the list of ghosts that are in the gang
	 */
	private List<Ghost> ghosts;

	/**
	 * It's the type of the ghosts in the gang
	 */
	private TypeEntity type;

	/**
	 * Creates the gang
	 * 
	 * @param type is of ghosts of the gang
	 * @throws NullPointerException if the type is null
	 */
	public Gang(TypeEntity type) {
		if (type == null)
			throw new NullPointerException("Error creating gang: the type cannot be null");
		this.type = type;
		ghosts = new ArrayList<>(3);
	}

	/**
	 * 
	 * @return the list of ghosts in the gang
	 */
	public List<Ghost> getGhosts() {
		return ghosts;
	}

	/**
	 * 
	 * @return the type of the ghosts in the gang
	 */
	public TypeEntity getType() {
		return type;
	}

	/**
	 * Adds the given ghost
	 * 
	 * @param ghost to be added
	 * @throws IndexOutOfBoundsException if the gang is full
	 * @throws NullPointerException      if the ghost is null
	 * @throws IllegalArgumentException  if the ghost doesn't have the same type as
	 *                                   the gang
	 */
	public void addGhost(Ghost ghost) {
		if (ghosts.size() == MAXIMUM_NUMBER_OF_GHOSTS)
			throw new IndexOutOfBoundsException("Error adding ghost to gang: the gang is already full");

		if (ghost == null)
			throw new NullPointerException("Error adding ghost to gang: the ghost cannot be null");

		if (!type.equals(ghost.getType()))
			throw new IllegalArgumentException(
					"Error adding ghost to gang: the ghost doesn't have the same type as the gang");
		ghosts.add(ghost);
	}

	/**
	 * 
	 * @return true if none of the ghost in the gang have been eliminated
	 */
	public boolean isCompleted() {
		for (Ghost ghost : ghosts) {
			if (ghost.isEliminated())
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @return the number of ghosts in the gang
	 */
	public int size() {
		return ghosts.size();
	}

}
