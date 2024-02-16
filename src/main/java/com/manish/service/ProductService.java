package com.manish.service;

import java.util.List;

import com.manish.Entity.Product;

public interface ProductService {

	void Save(Product product);

	List<Product> getAllProducts();

	Product getProduct(int id);
}
