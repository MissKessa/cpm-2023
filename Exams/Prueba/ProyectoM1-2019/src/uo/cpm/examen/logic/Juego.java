package uo.cpm.examen.logic;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.examen.util.FileUtil;

public class Juego {
	private List<Prize> prizes = new ArrayList<Prize>();
	private int points = 0;
	private List<Prize> selected = new ArrayList<Prize>();

	public Juego() {
		FileUtil.loadFile("files/premios.dat", prizes);
	}

	public List<Prize> getAvailablePrizes() {
		List<Prize> availablePrizes = new ArrayList<Prize>();
		int availablePoints = getRemainingPoints();
		for (Prize prize : prizes) {
			if (prize.getPoints() <= availablePoints) {
				availablePrizes.add(prize);
			}
		}
		return availablePrizes;

	}

	public String[] getAvailablePrizesString() {
		List<Prize> availablePrizes = getAvailablePrizes();
		String[] availablePrizesString = new String[availablePrizes.size()];
		for (int i = 0; i < availablePrizes.size(); i++) {
			availablePrizesString[i] = availablePrizes.get(i).toString();
		}
		return availablePrizesString;
	}

	public String getSelectedString() {
		List<Prize> selected = getSelected();
		String selectedPrizesString = "";
		for (int i = 0; i < selected.size(); i++) {
			selectedPrizesString += selected.get(i).toString() + "\r\n";
		}
		return selectedPrizesString;
	}

	public String[] getAvailablePrizesByTypeString(TypePrize type) {
		List<Prize> availablePrizes = getAvailablePrizesByType(type);
		String[] availablePrizesString = new String[availablePrizes.size()];
		for (int i = 0; i < availablePrizes.size(); i++) {
			availablePrizesString[i] = availablePrizes.get(i).toString();
		}
		return availablePrizesString;
	}

	public String[] getPrizesString() {
		String[] prizesString = new String[prizes.size()];
		for (int i = 0; i < prizes.size(); i++) {
			prizesString[i] = prizes.get(i).toString();
		}
		return prizesString;
	}

	public List<Prize> getAvailablePrizesByType(TypePrize type) {
		List<Prize> availablePrizes = new ArrayList<Prize>();
		int availablePoints = getRemainingPoints();
		for (Prize prize : prizes) {
			if (prize.getPoints() <= availablePoints && prize.getType() == type) {
				availablePrizes.add(prize);
			}
		}
		return availablePrizes;
	}

	public List<Prize> getPrizes() {
		return prizes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints() {
		if (points == 0)
			this.points = Dice.lanzar();
	}

	public List<Prize> getSelected() {
		return selected;
	}

	public int getRemainingPoints() {
		int availablePoints = points;
		for (Prize prize : selected) {
			availablePoints -= prize.getPoints();
		}
		return availablePoints;
	}

	public void add(String prizeChosen) {
		Prize prizeInList = null;

		for (Prize a : prizes) {
			if (a.toString().equals(prizeChosen))
				prizeInList = a;
		}

//		if (articuloEnPedido == null) {
//			Articulo articuloAPedido = new Articulo(articuloDelCatalogo);
//			articuloAPedido.setUnidades(unidades);
//			listaPedido.add(articuloAPedido);
//		} else {
//			int totalUnidades = articuloEnPedido.getUnidades() + unidades;
//			articuloEnPedido.setUnidades(totalUnidades);
//		}
		if (prizeInList != null) {
			selected.add(prizeInList);
		}
	}

	public String getImagePrize(String selectedItem) {
		Prize prizeInList = null;

		for (Prize a : prizes) {
			if (a.toString().equals(selectedItem))
				prizeInList = a;
		}
		if (prizeInList != null) {
			return prizeInList.getPicture();
		} else
			return null;
	}
}
