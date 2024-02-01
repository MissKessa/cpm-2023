package uo.cpm.examen.logic;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.examen.util.FileUtil;

public class Business {
	private List<Hotel> hotels = new ArrayList<>();

	public Business() {
		FileUtil.loadFile("files/oferta.dat", hotels);
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public int getNumberOfHotels() {
		return hotels.size();
	}
}
