package uo.cpm.p6.model;

public class Meteorite extends Cell {
	boolean erased;

	public boolean isErased() {
		return erased;
	}

	public void setErased(boolean erased) {
		this.erased = erased;
	}

	public Meteorite() {
		setScore(0);
		setErased(false);
		setPicture("/img/meteorite.jpeg");
	}

	@Override
	public Integer discover() {
		setErased(true);
		return getScore();
	}
	
	@Override
	public boolean isFree() {
		return false;
	}
}
