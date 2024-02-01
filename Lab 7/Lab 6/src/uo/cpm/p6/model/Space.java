package uo.cpm.p6.model;

public class Space extends Cell {	
	
	public Space(int position) {
	 setScore(-200);
	 setPicture("/img/space.jpg");
	}
	
	@Override
	public boolean isFree() {
		return true;
	}
}