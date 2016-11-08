package ru.rsc.edu.entities;

public class Category {
	private Category superCategory;
	private String name; // unique
	
	public Category(String name) {
		this.superCategory = null;
		this.name = name;
	}
	
	public Category(String name, Category superCategory) {
		this.superCategory = superCategory;
		this.name = name;
	}
	
	public boolean equals(Category category) {
		// структурную идентичность определяет имя
		return (this.name.equals(category.name));
	}
	
	// setters
	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// getters
	public Category getSuperCategory() {
		return superCategory;
	}
	
	public String getName() {
		return name;
	}
 
}
