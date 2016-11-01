package ru.rsc.edu;

public class Account {
	private static int idSequence = 0;
	private int id;
	private int number; // unique
	private String name;
	private String comment;
	
	public Account(int number, String name) {
		this.id = idSequence++;
		this.number = number;
		this.name = name;
		this.comment = null;
	}
	
	// setters
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// getters
	public int getId() {
		return id;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}
	
	public String getComment() {
		return comment;
	}
}
