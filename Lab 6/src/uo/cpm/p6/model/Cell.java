package uo.cpm.p6.model;

public abstract class Cell {
	
	private int score;
	private String picture;
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Integer discover()
	{
		return score;
	}
}
