package uo.cpm.module.model;

/**
 * It represents each creature of the board: ghosts and ghostbusters
 * 
 * @author paula
 *
 */
public abstract class Entity {
	/**
	 * It's the type of entity
	 */
	private TypeEntity type;
//	private int[] pos; // i,j

	/**
	 * Creates the entity with the given type
	 * 
	 * @param type of the entity
	 * @throw NullPointerException if the type is null
	 */
	public Entity(TypeEntity type) {
		if (type == null) {
			throw new NullPointerException("Error creating entity: the type must be not null");
		}
		this.type = type;
	}

	/**
	 * 
	 * @return the type of entity
	 */
	public TypeEntity getType() {
		return type;
	}

//	public Entity(TypeEntity type, int[] pos) {
//	if (type == null || pos == null) {
//		throw new NullPointerException("Error creating entity: the type and the pos must be not null");
//	}
//	this.type = type;
////	this.pos = pos;
//}

//	public void setType(TypeEntity type) {
//	this.type = type;
//}

//public void setPos(int[] pos) {
//	this.pos = pos;
//}

//	public int[] getPos() {
//		return pos;
//	}

}
