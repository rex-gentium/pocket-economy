package ru.rsc.edu;

public class Category {
	static int idSequence = 0;
	private int id;
	private int superCategoryId;
	private String name; // unique
	
	public Category(String name, int superCategoryId) {
		this.id = idSequence++;
		this.superCategoryId = superCategoryId;
		this.name = name;
	}
	
	// setters
	public void setSuperCategory(int superCategoryId) {
		this.superCategoryId = superCategoryId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// getters
	public int getId() {
		return id;
	}
	
	public int getSuperCategoryId() {
		return superCategoryId;
	}
	
	public String getName() {
		return name;
	}
 
}
