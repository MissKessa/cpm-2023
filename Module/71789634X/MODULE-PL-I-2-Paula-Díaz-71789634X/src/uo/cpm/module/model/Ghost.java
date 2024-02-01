package uo.cpm.module.model;

/**
 * Represents the entity Ghost. It has an extra attribute (eliminated) to know
 * if a ghostbuster has eliminated this ghost
 * 
 * @author paula
 *
 */
public class Ghost extends Entity {
	/**
	 * Indicates if the ghost has been eliminated by a ghostbuster
	 */
	private boolean eliminated;

//	public Ghost(TypeEntity type, int[] pos) {
//		super(type, pos);
//		if (TypeEntity.ghostbuster.equals(type))
//			throw new IllegalArgumentException("Error creating ghost: the type cannot be ghostbuster");
//	}

	/**
	 * Creates a ghost with the given type
	 * 
	 * @param type is the type of the ghost
	 * @throws IllegalArgumentException if the given type is ghostbuster
	 */
	public Ghost(TypeEntity type) {
		super(type);
		if (TypeEntity.ghostbuster.equals(type))
			throw new IllegalArgumentException("Error creating ghost: the type cannot be ghostbuster");
	}

	/**
	 * 
	 * @return if the ghost has been eliminated by a ghostbuster
	 */
	public boolean isEliminated() {
		return eliminated;
	}

	/**
	 * Sets if the ghost has been eliminated or not
	 * 
	 * @param eliminated
	 */
	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}

}
