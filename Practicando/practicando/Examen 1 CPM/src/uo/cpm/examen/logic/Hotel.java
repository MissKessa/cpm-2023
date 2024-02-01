package uo.cpm.examen.logic;

public class Hotel {
	private TypeHotel type;
	private String location;
	private double price;
	private double priceBreakfast;

	public Hotel(String type, String location, double price, double priceBreakfast) {

		if (type.equals("Apartamento 4 plazas")) {
			this.type = TypeHotel.Apartamento4;
		} else if (type.equals("Apartamento 8 plazas")) {
			this.type = TypeHotel.Apartamento8;
		} else {
			this.type = TypeHotel.HabitaciónDoble;
		}
		this.location = location;
		this.price = price;
		this.priceBreakfast = priceBreakfast;
	}

	public TypeHotel getType() {
		return type;
	}

	public String getLocation() {
		return location;
	}

	public double getPrice() {
		return price;
	}

	public double getPriceBreakfast() {
		return priceBreakfast;
	}

	@Override
	public String toString() {
		String typeString = "";
		if (this.type == TypeHotel.Apartamento4) {
			typeString = "Apartamento 4 plazas";
		} else if (this.type == TypeHotel.Apartamento8) {
			typeString = "Apartamento 8 plazas";
		} else {
			typeString = "Habitación doble";
		}
		return "Type: " + typeString + " \r\n Location: " + location + " \r\n Price: " + price + " \r\n Breakfast: "
				+ priceBreakfast;
	}

}
