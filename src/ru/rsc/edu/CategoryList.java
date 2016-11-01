package ru.rsc.edu;
import java.util.*;

public class CategoryList {
	private static Category root = new Category("root", -1);
	private List<Category> categories;
	
	public CategoryList() {
		categories = new ArrayList<Category>();
	}
	
	public CategoryList(List<Category> initial) {
		categories = initial;
	}
	
	public List<Category> get() {
		return categories;
	}
	
	public int add(String name, int superCategoryId) {
		Category cat = new Category(name, superCategoryId);
		categories.add(cat);
		return cat.getId();
	}
	
	// getters
	public Category getById(int id) {
		if (root.getId() == id)
			return root;
		for (Category category : categories)
			if (category.getId() == id)
				return category;
		return null;
	}
	
	public Category getByName(String name) {
		if (root.getName() == name)
			return root;
		for (Category category : categories)
			if (category.getName() == name)
				return category;
		return null;
	}
	
	public Category getRoot() {
		return root;
	}
	
	public CategoryList getBySuperCategory(int superCategoryId) {
		List<Category> result = new ArrayList<Category>();
		for (Category category : categories)
			if (category.getSuperCategoryId() == superCategoryId)
				result.add(category);
		return new CategoryList(result);
	}
}
