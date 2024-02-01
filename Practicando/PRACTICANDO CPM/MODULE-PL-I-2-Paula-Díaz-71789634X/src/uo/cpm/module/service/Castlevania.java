package uo.cpm.module.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import uo.cpm.module.model.Castle;
import uo.cpm.module.model.Discount;
import uo.cpm.module.model.Reservation;
import uo.cpm.module.model.TypeOfEnchantment;
import uo.cpm.module.util.FileUtil;

/**
 * It's the class representing the castle reservation business. It has the list
 * of castles and discounts; the reservation and chosen castle; and the
 * enchantment and price filter
 * 
 * @author paula
 *
 */
public class Castlevania {
	// private static final String CASTLES_FILE = "files/castles.dat";
	/**
	 * It's the file where all the reservations are saved
	 */
	private static final String SAVE_RESERVATION_FILE = "bookings";
	/**
	 * It's the file where all the discounts are loaded and saved
	 */
	private static final String DISCOUNTS_FILE = "discounts";

	/**
	 * It's the list of available castles
	 */
	private List<Castle> castleList;
	/**
	 * It's the list of discounts saved
	 */
	private List<Discount> discountList;

	/**
	 * It's the list of enchaments to filter with
	 */
	private List<TypeOfEnchantment> enchantmentFilter;
	/**
	 * It sets the limits for each range of prices
	 */
	private float[] priceFilter = new float[5];

	/**
	 * It's the current reservation
	 */
	private Reservation reservation;
	/**
	 * It's the chosen castle to reserve
	 */
	private Castle choosenCastle;
	/**
	 * It indicates if the app has been initialize
	 */
	private boolean initialized = true;

	/**
	 * It creates the business app. It initializes all the lists, adds all the
	 * enchantments to the filter and loads the discounts
	 */
	public Castlevania() {
		castleList = new ArrayList<>();
		discountList = new ArrayList<>();
		reservation = new Reservation();
		addAllEnchanmentToFilter();

		loadDiscounts();
	}

	/**
	 * Creates a new reservation
	 */
	public void createReservation() {
		reservation = new Reservation();
	}

	/**
	 * Sets the given information to the reservation
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
		id = id.strip();
		castleCode = castleCode.strip();
		reservation.makeReservation(id, castleCode, reservationDate, days, numberOfRooms, numberOfPeople);

	}

	/**
	 * Adds the given enchantment to the filter if it doesn't contain them already
	 * 
	 * @param enchantment
	 * @throws NullPointerException if the enchantment is null
	 */
	public void addEnchanmentToFilter(TypeOfEnchantment enchantment) {
		if (enchantment == null)
			throw new NullPointerException("Filter enchantment error: The enchanment cannot be null");

		if (!enchantmentFilter.contains(enchantment))
			enchantmentFilter.add(enchantment);
	}

	/**
	 * Adds all the enchantments to the filter
	 */
	public void addAllEnchanmentToFilter() {
		enchantmentFilter = new ArrayList<>();

		TypeOfEnchantment[] enchantments = TypeOfEnchantment.values();

		for (TypeOfEnchantment ench : enchantments) {
			enchantmentFilter.add(ench);
		}
	}

	/**
	 * Removes the given enchantment to the filter
	 * 
	 * @param enchantment
	 * @throws NullPointerException if the enchantment is null
	 */
	public void removeEnchanmentToFilter(TypeOfEnchantment enchantment) {
		if (enchantment == null)
			throw new NullPointerException("Filter enchantment error: The enchanment is null");

		enchantmentFilter.remove(enchantment);
	}

	/**
	 * Removes all the enchantments on the filter
	 */
	public void removeAllEnchanmentToFilter() {
		enchantmentFilter = new ArrayList<>();
	}

	/**
	 * 
	 * @return the list of enchantments of the filter
	 */
	public List<TypeOfEnchantment> getEnchantmentFilter() {
		return enchantmentFilter;
	}

	/**
	 * Loads the castles from the given file to the list of castles, and calculates
	 * the limits for the price filer
	 * 
	 * @param fileName
	 * @throws IllegalArgumentException if the fileName is null or blank
	 */
	public void loadCastles(String fileName) {
		if (fileName == null || fileName.isBlank() || fileName.isEmpty())
			throw new IllegalArgumentException("Loading castles error: the fileName is invalid");
		fileName = fileName.strip();

		castleList = new ArrayList<>();
		FileUtil.loadCastleFile("files/" + fileName + ".dat", castleList);

		calculateLimitsPrice();
	}

	/**
	 * Loads the discounts from the discounts file to the list of discounts
	 */
	public void loadDiscounts() {
		discountList = new ArrayList<>();
		FileUtil.loadDiscounts(DISCOUNTS_FILE, discountList);
	}

	/**
	 * Saves the given discount on the discounts file and add it to the list of
	 * discounts
	 * 
	 * @param discount
	 * @throws NullPointerException if the discount is null
	 */
	public void saveDiscount(Discount discount) {
		if (discount == null)
			throw new NullPointerException("Error saving the discount: The discount cannot be null");
		discountList.add(discount);
		FileUtil.saveDiscount(DISCOUNTS_FILE, discount);
		loadDiscounts();
	}

	/**
	 * Removes the discount from the list of discounts, and updates the discounts
	 * file
	 */
	public void removeDiscount() {
		Discount applied = reservation.getDiscountApplied();
		if (applied != null) {
			for (Discount discount : discountList) {
				if (discount.getId().equals(applied.getId())) {
					discountList.remove(discount);
					break;
				}
			}
			FileUtil.updateDiscounts(DISCOUNTS_FILE, discountList);
		}
//		loadDiscounts();
		// reservation.setDiscountApplied(null);
	}

	/**
	 * Saves the current reservation to the bookings file
	 */
	public void saveReservation() {
		FileUtil.saveReservationToFile(SAVE_RESERVATION_FILE, reservation);
	}

	/**
	 * 
	 * @return the current reservation
	 */
	public Reservation getReservation() {
		return reservation;
	}

	/**
	 * Sets the current reservation to the given one
	 * 
	 * @param reservation
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	/**
	 * 
	 * @return the sorted list of all the countries from the list of castles
	 */
	public ArrayList<String> getCountries() {
		ArrayList<String> countries = new ArrayList<>();

		for (Castle castle : castleList) {
			if (!countries.contains(castle.getCountry())) {
				countries.add(castle.getCountry());
			}
		}
		Collections.sort(countries);

		return countries;
	}

	/**
	 * Filters the given list of castles by the selectedCountry. If the selected
	 * index is 0, the list of castles is return as it is (the default option is
	 * chosen)
	 * 
	 * @param castles         is the list of castles
	 * @param selectedCountry
	 * @param selectedIndex
	 * @return the filtered list of castles by country
	 */
	public List<Castle> filterByCountry(List<Castle> castles, String selectedCountry, int selectedIndex) {
		selectedCountry = selectedCountry.strip();
		if (selectedIndex == 0) { // it's the default option
			return castles;
		}
		List<Castle> filteredByCountry = new ArrayList<>();
		for (Castle castle : castles) {
			if (castle.getCountry().equals(selectedCountry)) {
				filteredByCountry.add(castle);
			}

		}
		return filteredByCountry;
	}

	/**
	 * It calculates the limits for the price filter by picking the minimum price
	 * and maximum price of the castles
	 */
	private void calculateLimitsPrice() {
		// ArrayList<String> prices = new ArrayList<>();
		float minPrice = castleList.get(0).getPrice();
		float maxPrice = castleList.get(0).getPrice();
		// prices.add(texts.getString("cbPrice.baseOption"));
		for (Castle castle : castleList) {
			if (castle.getPrice() < minPrice)
				minPrice = castle.getPrice();
			else if (castle.getPrice() > maxPrice)
				maxPrice = castle.getPrice();
		}

		minPrice = (float) Math.floor(minPrice);
		maxPrice = (float) Math.ceil(maxPrice);
		float difference = maxPrice - minPrice;

		float min = minPrice;
		float max = minPrice + difference / 4;
		for (int i = 1; i < 5; i++) {
			max = minPrice + (difference / 4) * i;
			priceFilter[i - 1] = min;
			// prices.add("" + min + " - " + max);
			min = max;
		}
		priceFilter[4] = max;
		// return prices;
	}

	/**
	 * Filters the given list of castles by the price. From the selectedIndex we
	 * obtain the maximum value for the price (same index as in priceFilter) and the
	 * minimum value (index-1 as in priceFilter)
	 * 
	 * @param castles       is the list of castles
	 * @param selectedIndex
	 * @return the filtered list of castles by price
	 */
	public List<Castle> filterByPrice(List<Castle> castles, int selectedIndex) {
		if (selectedIndex == 0) {
			return castles;
		}
		List<Castle> filteredByPrice = new ArrayList<>();

		for (Castle castle : castles) {
			float price = castle.getPrice();
			if (price >= priceFilter[selectedIndex - 1] && price <= priceFilter[selectedIndex]) {
				filteredByPrice.add(castle);
			}

		}
		return filteredByPrice;
	}

	/**
	 * 
	 * @return the price filter array
	 */
	public float[] getPriceFilter() {
		return priceFilter;
	}

	/**
	 * 
	 * @return the list of castles filtered by enchantments
	 */
	public List<Castle> getCastleList() {
		List<Castle> filteredByEnchantments = new ArrayList<>();
		for (Castle castle : castleList) {
			String[] enchantments = (castle.getEnchantments().split("-"));
			for (String ench : enchantments) {
				if (enchantmentFilter.contains(TypeOfEnchantment.valueOf(ench))) {
					filteredByEnchantments.add(castle);
					break;
				}
			}
		}
		return filteredByEnchantments;
	}

//	/**
//	 * Sets the castle list to the given one
//	 * 
//	 * @param castleList
//	 */
//	private void setCastleList(List<Castle> castleList) {
//		this.castleList = castleList;
//	}

	/**
	 * 
	 * @param dni
	 * @return if there is a discount with the dni associated
	 */
	public boolean hasDiscount(String dni) {
		dni = dni.strip();
		for (Discount discount : discountList) {
			if (discount.getId().equals(dni)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param dni
	 * @return the discount associated to the given dni. If there is no discount, it
	 *         returns null
	 */
	public Discount searchDiscount(String dni) {
		dni = dni.strip();
		for (Discount discount : discountList) {
			if (discount.getId().equals(dni)) {
				return discount;
			}
		}
		return null;
	}

	/**
	 * Sets the discountApplied to the given one
	 * 
	 * @param discountApplied
	 */
	public void setDiscountApplied(Discount discountApplied) {
		reservation.setDiscountApplied(discountApplied);
	}

	/**
	 * 
	 * @return the final price of the reservation
	 */
	public float getFinalPrice() {
		return reservation.getFinalPrice();
	}

	/**
	 * It calculates the final price depending on the given numberOfRooms *
	 * numberOfDays
	 * 
	 * @param numberOfRooms
	 * @param numberOfDays
	 * @return the final price
	 */
	public float calculateFinalPrice(int numberOfRooms, int numberOfDays) {
		if (numberOfRooms <= 0 && numberOfDays <= 0)
			throw new IllegalArgumentException(
					"Error calculating the final price: the number of rooms and days cannot be 0 or negative");
		return reservation.calculateFinalPrice(numberOfRooms, numberOfDays);
	}

	/**
	 * 
	 * @return if the app has been initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets if the app has been initialized
	 * 
	 * @param initialized
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * 
	 * @return the number of people of the reservation
	 */
	public int getNumberOfPeople() {
		return reservation.getNumberOfPeople();
	}

	/**
	 * 
	 * @return the number of rooms of the reservation
	 */
	public int getNumberOfRooms() {
		return reservation.getNumberOfRooms();
	}

	/**
	 * 
	 * @return the number fo days of the reservation
	 */
	public int getNumberOfDays() {
		return reservation.getDays();
	}

	/**
	 * 
	 * @return the date of the reservation
	 */
	public String getDate() {
		return reservation.getReservationDate();
	}

	/**
	 * 
	 * @return the id of the reservation
	 */
	public String getID() {
		return reservation.getId();
	}

	/**
	 * Sets the room price of the reservation by the given one
	 * 
	 * @param price
	 * @throws IllegalArgumentException if the price is 0 or negative
	 */
	public void setRoomPrice(float price) {
		if (price <= 0)
			throw new IllegalArgumentException(
					"Error setting room price of the reservation: it cannot be 0 or negative");
		reservation.setRoomPrice(price);
	}

	/**
	 * Sets the name and surname of the reservation by the given ones
	 * 
	 * @param name
	 * @param surnames
	 * @throws NullPointerException     if one of the parameters is null
	 * @throws IllegalArgumentException if one of the parameters is blank
	 */
	public void setFullName(String name, String surnames) {
		if (name == null || surnames == null)
			throw new NullPointerException("Error setting full name: the name or surnames are null");
		if (name.isBlank() || surnames.isBlank())
			throw new IllegalArgumentException("Error setting full name: the name or surnames are blank");

		name = name.strip();
		surnames = surnames.strip();
		reservation.setName(name + " " + surnames);
	}

	/**
	 * Sets the email to the given one
	 * 
	 * @param email
	 * @throws NullPointerException     if email is null
	 * @throws IllegalArgumentException if email is blank
	 */
	public void setEmail(String email) {
		if (email == null)
			throw new NullPointerException("Error setting email: the email is null");
		if (email.isBlank())
			throw new IllegalArgumentException("Error setting email: the email is blank");

		email = email.strip();
		reservation.setEmail(email);
	}

	/**
	 * Sets the comments to the given ones
	 * 
	 * @param text
	 * @throws NullPointerException if text is null
	 */
	public void setComments(String text) {
		if (text == null)
			throw new NullPointerException("Error setting comments: the text is null");

		text = text.strip();
		reservation.setComments(text);
	}

//	/**
//	 * 
//	 * @return the number of castles
//	 */
//	private int getNumberOfCastles() {
//		return castleList.size();
//	}

	/**
	 * 
	 * @return the chosen castle
	 */
	public Castle getChoosenCastle() {
		return choosenCastle;
	}

	/**
	 * Sets the choosenCastle to the given one, and sets the code of the castle in
	 * the reservation
	 * 
	 * @param choosenCastle
	 */
	public void setChoosenCastle(Castle choosenCastle) {
		this.choosenCastle = choosenCastle;
		this.reservation.setCastleCode(choosenCastle.getCode());
	}

}
