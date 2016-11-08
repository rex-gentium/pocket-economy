package ru.rsc.edu.entities;

public class Account {
	private int number; // unique
	private String name;
	private String comment;
	
	public Account(int number, String name) {
		this.number = number;
		this.name = name;
		this.comment = null;
	}
	
	public boolean equals(Account acc) {
		// структурную идентичность определяет номер
		return (this.number == acc.number);
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
