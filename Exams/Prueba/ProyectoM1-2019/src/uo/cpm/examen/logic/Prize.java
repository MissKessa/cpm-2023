package uo.cpm.examen.logic;

public class Prize {
	private String picture;
	private TypePrize type;
	private String name;
	private float points;

	public Prize(String picture, String type, String name, float points) {
		this.picture = picture + ".png";

		if (type.equals(TypePrize.Electronica.toString()))
			this.type = TypePrize.Electronica;
		else if (type.equals(TypePrize.Infantil.toString()))
			this.type = TypePrize.Infantil;
		else
			this.type = TypePrize.Ocio;

		this.name = name;
		this.points = points;
	}

	public String getPicture() {
		return picture;
	}

	public TypePrize getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public float getPoints() {
		return points;
	}

	@Override
	public String toString() {
		return type.toString() + " - " + name + " - " + points;
	}

}
