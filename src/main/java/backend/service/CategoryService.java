package backend.service;

import java.util.List;

import backend.model.Category;

public interface CategoryService {
	public List<Category> getCategory();

	public void addCategory(Category category);
}
