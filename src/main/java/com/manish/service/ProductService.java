package com.manish.service;

import java.util.List;

import com.manish.Entity.Product;
import com.manish.model.Productsorted;

public interface ProductService {

	void Save(Product product);

	List<Product> getAllProducts();

	List<Productsorted> getAllSortedProducts(String sortBy, int limit, int offset);

	Product getProduct(String productCode);
}
