package uo.cpm.module.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * It's the reservation that the user makes for a castle. It contains all the
 * information for saving the booking, the discount applied, room price and
 * number of people
 * 
 * @author paula
 *
 */
public class Reservation {
	public static final String STRING_DATE_FORMAT = "dd-MM-yyyy";
	/**
	 * It's the id of the user that makes the reservation
	 */
	private String id;
	/**
	 * It's the name of the user that makes the reservation
	 */
	private String name;
	/**
	 * It's the email of the user that makes the reservation
	 */
	private String email;
	/**
	 * It's the code of the castle that the user is reserving
	 */
	private String castleCode;
	/**
	 * It's the starting date of the reservation
	 */
	private String reservationDate;
	/**
	 * It's the number of days of the reservation
	 */
	private int days;
	/**
	 * It's the number of rooms of the reservation
	 */
	private int numberOfRooms;
	/**
	 * It's the final price of the reservation
	 */
	private float finalPrice;
	/**
	 * It's the comments of the user that makes the reservation
	 */
	private String comments;

	/**
	 * It's the discount applied to the price of the reservation
	 */
	private Discount discountApplied;
	/**
	 * It's the price of the room
	 */
	private float roomPrice;
	/**
	 * It's the number of people of the reservation
	 */
	private int numberOfPeople;

	/**
	 * It creates the reservation
	 */
	public Reservation() {
	}

	/**
	 * Calculates the final price as the roomPrice * numberOfRooms * numberOfDays *
	 * discount applied (if there is any)
	 * 
	 * @param numberOfRooms to calculate the final price
	 * @param numberOfDays
	 * @return the final price
	 */
	public float calculateFinalPrice(int numberOfRooms, int numberOfDays) {
		this.numberOfRooms = numberOfRooms;
		if (discountApplied == null) {
			finalPrice = roomPrice * numberOfRooms * numberOfDays;
		} else if ((DiscountCode.EXTRA10).equals(getValueOfDiscount())) {
			finalPrice = (float) (roomPrice * numberOfRooms * numberOfDays * 0.9);
		} else if ((DiscountCode.EXTRA25).equals(getValueOfDiscount())) {
			finalPrice = (float) (roomPrice * numberOfRooms * numberOfDays * 0.75);
		} else if ((DiscountCode.EXTRA5).equals(getValueOfDiscount())) {
			finalPrice = (float) (roomPrice * numberOfRooms * numberOfDays * 0.95);

		} else {
			finalPrice = roomPrice * numberOfRooms * numberOfDays;
		}
		return finalPrice;
	}

	/**
	 * Sets the given information of the reservation
	 * 
	 * @param id
	 * @param castleCode
	 * @param reservationDate
	 * @param days
	 * @param numberOfRooms
	 * @param numberOfPeople
	 */
	public void makeReservation(String id, String castleCode, Date reservationDate, int days, int numberOfRooms,
			int numberOfPeople) {
		this.id = id;
//		if (discountApplied != null)
//			discountApplied.setId(id);
		this.castleCode = castleCode;
		DateFormat dateFormat = new SimpleDateFormat(STRING_DATE_FORMAT);
		this.reservationDate = dateFormat.format(reservationDate);

		this.days = days;
		this.numberOfRooms = numberOfRooms;
		this.numberOfPeople = numberOfPeople;

	}

	/**
	 * @return a String representing the reservation with this format: Client's ID;
	 *         Name and surname;Email;Castle code;Reservation date;Days;Number of
	 *         rooms;Final price;Comments
	 */
	@Override
	public String toString() {
		return "" + id + ";" + name + ";" + email + ";" + castleCode + ";" + reservationDate + ";" + days + ";"
				+ numberOfRooms + ";" + finalPrice + ";" + comments;
	}

	/**
	 * 
	 * @return the value of the discount
	 */
	public DiscountCode getValueOfDiscount() {
		return discountApplied.getCode();
	}

	/**
	 * Sets the discount applied to the given one
	 * 
	 * @param discountApplied
	 */
	public void setDiscountApplied(Discount discountApplied) {
		this.discountApplied = discountApplied;
	}

	/**
	 * 
	 * @return the discount applied to the price
	 */
	public Discount getDiscountApplied() {
		return discountApplied;
	}

	/**
	 * Sets the room price as the given one
	 * 
	 * @param roomPrice
	 */
	public void setRoomPrice(float roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * Sets the number of people as the given one
	 * 
	 * @param numberOfPeople
	 */
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * 
	 * @return the number of people of the reservation
	 */
	public int getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * Sets the id as the given one
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the name as the given one
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the email as the given one
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the castleCode as the given one
	 * 
	 * @param castleCode
	 */
	public void setCastleCode(String castleCode) {
		this.castleCode = castleCode;
	}

	/**
	 * 
	 * @return the castleCode
	 */
	public String getCastleCode() {
		return castleCode;
	}

	/**
	 * Sets the reservationDate as the given one
	 * 
	 * @param reservationDate
	 */
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	/**
	 * 
	 * @return the reservationDate
	 */
	public String getReservationDate() {
		return reservationDate;
	}

	/**
	 * Sets the days as the given one
	 * 
	 * @param days
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * 
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * Sets the numberOfRooms as the given one
	 * 
	 * @param numberOfRooms
	 */
	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	/**
	 * 
	 * @return the numberOfRooms
	 */
	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	/**
	 * Sets the finalPrice as the given one
	 * 
	 * @param finalPrice
	 */
	public void setFinalPrice(float finalPrice) {
		this.finalPrice = finalPrice;
	}

	/**
	 * 
	 * @return the finalPrice
	 */
	public float getFinalPrice() {
		return finalPrice;
	}

	/**
	 * Sets the comments as the given one
	 * 
	 * @param comments
	 */
	public void setComments(String comments) {
		this.comments = comments.replace("\n", " ");
	}

	/**
	 * 
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

}
