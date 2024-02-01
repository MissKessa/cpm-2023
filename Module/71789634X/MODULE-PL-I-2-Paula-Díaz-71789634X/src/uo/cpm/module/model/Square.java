package uo.cpm.module.model;

/**
 * It represents each square of the board of the game, and it contains the
 * entity that is in the square
 * 
 * @author paula
 *
 */
public class Square {
	/**
	 * It's the entity that is in the square
	 */
	private Entity entity;
//	private int[] pos;

//	public Square(Entity entity, int[] pos) {
//		if (pos == null)
//			throw new NullPointerException("Error creating square: the position cannot be null");
//		this.entity = entity;
//		this.pos = pos;
//	}
//	
	/**
	 * It creates the square with the given entity
	 * 
	 * @param entity
	 * @throws NullPointerException if the entity is null
	 */
	public Square(Entity entity) {
		if (entity == null)
			throw new NullPointerException("Error creating square: the entity cannot be null");
		this.entity = entity;
	}

//	public Square(int[] pos) {
//		this(null, pos);
//	}

	/**
	 * It creates the square with a null entity
	 */
	public Square() {

	}

	/**
	 * 
	 * @return the entity in the square
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Sets the entity that is in the square to the given one
	 * 
	 * @param entity
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

//	public int[] getPos() {
//		return pos;
//	}
//
//	public void setPos(int[] pos) {
//		this.pos = pos;
//	}

}
