package com.manish.service;

import java.util.List;
import com.manish.Entity.Product;
import com.manish.customExceptions.DuplicateKeyException;
import com.manish.model.ProductResponse;
import com.manish.model.Productsorted;
import com.manish.model.SaveProduct;

public interface ProductService {

	void Save(SaveProduct product) throws DuplicateKeyException;

	List<ProductResponse> getAllProducts();

	List<ProductResponse> getAllSortedProducts(String sortBy, int limit, int offset);

	Product getProduct(String productCode);
	
	int getCount();
	
	void delete(Long productId);
}
