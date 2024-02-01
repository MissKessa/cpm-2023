package uo.cpm.module.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import uo.cpm.module.model.Castle;
import uo.cpm.module.model.Discount;
import uo.cpm.module.model.Reservation;

/**
 * It's the file util for loading the castles and discounts to the service, and
 * saving the reservation and discounts
 * 
 * @author paula
 *
 */
public abstract class FileUtil {

	/**
	 * Adds to the given castlesList all the castles loaded from the file with the
	 * given filename
	 * 
	 * @param fileName    with all the path and extension
	 * @param castlesList where all the castles are going to be loaded
	 */
	public static void loadCastleFile(String fileName, List<Castle> castlesList) {
		String line;
		String[] castleData = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			while (file.ready()) {
				line = file.readLine();
				castleData = line.split(";");
				castlesList.add(new Castle(castleData[0], castleData[1], castleData[2], castleData[3],
						Float.parseFloat(castleData[4]), castleData[5]));
			}
			file.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");

		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	/**
	 * Appends the given reservation to the file with the given fileName
	 * 
	 * @param fileName    without the path and extension
	 * @param reservation to be saved
	 */
	public static void saveReservationToFile(String fileName, Reservation reservation) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat", true));
			String line = reservation.toString();
			file.append(line + "\n");
			file.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	/**
	 * Adds to the given discountList all the discounts loaded from the file with
	 * the given filename
	 * 
	 * @param fileName     without the path and extension
	 * @param discountList where all the discounts are going to be loaded
	 */
	public static void loadDiscounts(String fileName, List<Discount> discountList) {
		String line;
		String[] discountData = null;

		try {
			BufferedReader file = new BufferedReader(new FileReader("files/" + fileName + ".dat"));
			while (file.ready()) {
				line = file.readLine();
				discountData = line.split(";");
				discountList.add(new Discount(discountData[0], Discount.parseDiscount(discountData[1])));
			}
			file.close();

		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found.");

		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	/**
	 * Writes all the discounts in the given list in the file with the given
	 * fileName
	 * 
	 * @param fileName     without the path and extension
	 * @param discountList with all the discounts
	 */
	public static void updateDiscounts(String fileName, List<Discount> discountList) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat"));
			for (Discount discount : discountList) {
				String line = discount.toString();
				file.write(line + "\n");
			}
			file.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");

		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}
	}

	/**
	 * Appends the given discount to the file with the given fileName
	 * 
	 * @param fileName without the path and extension
	 * @param discount to be saved
	 */
	public static void saveDiscount(String fileName, Discount discount) {
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat", true));

			String line = discount.toString();
			file.append(line + "\n");

			file.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("The file could not be saved.");
		} catch (IOException ioe) {
			new RuntimeException("I/O Error.");
		}

	}
}
