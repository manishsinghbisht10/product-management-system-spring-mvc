package com.manish.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.manish.Entity.Category;
import com.manish.Entity.Price;
import com.manish.Entity.Product;
import com.manish.Entity.Stock;
import com.manish.customExceptions.DuplicateKeyException;
import com.manish.model.ProductResponse;
import com.manish.model.Productsorted;
import com.manish.model.SaveProduct;
import com.manish.repository.CategoryRepository;
import com.manish.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public List<ProductResponse> getAllProducts() {
		List<ProductResponse> productLists = new ArrayList<>();
		List<Product> productDB = productRepository.getAllProducts();
		for (Product p : productDB) {
			ProductResponse productResponse = new ProductResponse();

			productResponse.setProductName(p.getProductName());
			productResponse.setProductDescription(p.getProductDescription());
			productResponse.setProductCode(p.getProductCode());
			productResponse.setProductId((Long) p.getProductId());

			List<Category> categoryDB = p.getCategory();
			String string = "";
			if (!Objects.isNull(categoryDB))
				for (int i = 0; i < categoryDB.size(); i++)
					string = string + categoryDB.get(i).getCategoryName() + ",";
			string = string.substring(0, string.length()-1);
			productResponse.setCategoryName(string);

			Price priceDB = p.getPrice();
			if (!Objects.isNull(priceDB)) {
				productResponse.setCurrency(priceDB.getCurrency());
				productResponse.setPrice(priceDB.getPrice());
			}

			Stock stockDB = p.getStock();
			if (!Objects.isNull(stockDB)) {
				productResponse.setLocation(stockDB.getLocation());
				productResponse.setInventory(stockDB.getInventoryAvailable());
			}
			productLists.add(productResponse);
		}
		return productLists;
	}

	@Override
	@Transactional
	public List<ProductResponse> getAllSortedProducts(String sortBy, int limit, int offset) {
		List<Productsorted> productDB = productRepository.getAllSortedProducts(sortBy, limit, offset);

		List<ProductResponse> productLists = new ArrayList<>();

		for (Productsorted p : productDB) {
			ProductResponse productResponse = new ProductResponse();

			productResponse.setProductName(p.getProduct_name());
			productResponse.setProductDescription(p.getProduct_description());
			productResponse.setProductCode(p.getProduct_code());
			productResponse.setProductId((p.getProduct_id().longValue()));

			productResponse.setCategoryName(p.getCategory_name());

			productResponse.setCurrency(p.getCurrency());
			productResponse.setPrice(p.getProduct_price());

			productResponse.setLocation(p.getLocation());
			productResponse.setInventory(p.getInventory_available());
			productLists.add(productResponse);

		}
		return productLists;
	}

	@Override
	@Transactional
	public Product getProduct(String productCode) {
		return productRepository.getProduct(productCode);
	}

	@Override
	@Transactional
	public int getCount() {
		return productRepository.getCount();
	}

	@Override
	@Transactional
	public void delete(Long productId) {
		productRepository.delete(productId);

	}

	@Override
	@Transactional
	public void Save(SaveProduct saveProduct) throws DuplicateKeyException {
		Product product = new Product();
		Price price = new Price();
		Stock stock = new Stock();
		price.setPrice(saveProduct.getProductPrice());
		price.setCurrency(saveProduct.getCurrency());

		stock.setLocation(saveProduct.getLocation());
		stock.setInventoryAvailable(saveProduct.getInvaentoryAvailable());

		product.setProductCode(saveProduct.getProductCode());
		product.setProductDescription(saveProduct.getProductDescription());
		product.setProductName(saveProduct.getProductName());

		product.setPrice(price);
		product.setStock(stock);

		price.setProduct(product);
		stock.setProduct(product);

		String categories[] = saveProduct.getCategoryName().split(",");
		List<Category> categoriesList = new ArrayList<>();
		for (int i = 0; i < categories.length; i++) {

			Category categoryDb = categoryRepository.saveCategory(categories[i]);
			categoriesList.add(categoryDb);

		}
		product.setCategory(categoriesList);
		productRepository.Save(product);

	}
}
