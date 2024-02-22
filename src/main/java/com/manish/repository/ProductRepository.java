package com.manish.repository;

import java.util.List;
import com.manish.Entity.Product;
import com.manish.customExceptions.DuplicateKeyException;
import com.manish.model.Productsorted;

public interface ProductRepository {
	void Save(Product product) throws DuplicateKeyException;

	List<Product> getAllProducts();

	List<Productsorted> getAllSortedProducts(String sortBy, int limit, int offset);

	Product getProduct(String productCode);
	
	int getCount();
	
	void delete(Long productId);
}
