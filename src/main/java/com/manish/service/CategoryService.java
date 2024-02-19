package com.manish.service;

import java.util.List;

import com.manish.Entity.Category;

public interface CategoryService {
	List<Category> saveCategory(String name);
}
