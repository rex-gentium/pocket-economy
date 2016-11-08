package ru.rsc.edu.collections;
import java.util.*;

import ru.rsc.edu.entities.Category;
import ru.rsc.edu.exceptions.AlreadyExistsException;

public class CategoryList {
	//private static Category root = new Category("root", -1);
	private List<Category> categories;
	
	public CategoryList() {
		categories = new ArrayList<Category>();
	}
	
	public CategoryList(List<Category> initial) {
		categories = initial;
	}
	
	public void add(Category category) throws AlreadyExistsException {
		if (!categories.contains(category))
			categories.add(category);
		else throw new AlreadyExistsException("Category " + category.getName() + " already exists");
	}
	
	// getters
	public List<Category> get() {
		return categories;
	}
	
	public Category getByName(String name) {
		for (Category category : categories)
			if (category.getName() == name)
				return category;
		return null;
	}
	
	public CategoryList selectBySuperCategory(Category superCategory) {
		List<Category> result = new ArrayList<Category>();
		for (Category category : categories)
			if (category.getSuperCategory().equals(superCategory))
				result.add(category);
		return new CategoryList(result);
	}
}
