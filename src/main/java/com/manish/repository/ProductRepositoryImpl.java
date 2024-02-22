package com.manish.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.manish.Entity.Product;
import com.manish.customExceptions.DuplicateKeyException;
import com.manish.model.CategoryNameAndProductNameDTO;
import com.manish.model.Productsorted;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public void Save(Product product) throws DuplicateKeyException {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String productName = product.getProductName();
		List<String> categories = product.getCategory().stream().map(x -> x.getCategoryName())
				.collect(Collectors.toList());

		String queryString = "SELECT p.product_name,c.category_name FROM Product p\n"
				+ "	   LEFT JOIN product_category pc ON p.product_id = pc.product_id LEFT JOIN \n"
				+ "    Category c ON pc.category_code = c.category_code LEFT JOIN \n"
				+ "    Price p2 ON p.product_id = p2.product_id LEFT JOIN \n"
				+ "    Stock s ON p.product_id = s.product_id WHERE p.product_name = :productName AND c.category_name IN (:categories)";

		Query query = session.createNativeQuery(queryString);
		query.setResultTransformer(Transformers.aliasToBean(CategoryNameAndProductNameDTO.class));
		// Set parameters
		query.setParameter("productName", productName);
		query.setParameterList("categories", categories);

		List<CategoryNameAndProductNameDTO> categoryNameAndProductNameDTO = query.getResultList();

		if (!categoryNameAndProductNameDTO.isEmpty()) {
			System.out.println(categoryNameAndProductNameDTO);
			throw new DuplicateKeyException("Duplicate Entry for product name and category");
		}
		hibernateTemplate.save(product);

	}

	@Override
	public List<Product> getAllProducts() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String queryString = "SELECT DISTINCT p FROM Product p JOIN FETCH p.category ORDER BY p.productId DESC";
		TypedQuery<Product> query = session.createQuery(queryString, Product.class);
		query.setMaxResults(4);
		query.setFirstResult(0);
		List<Product> products = query.getResultList();

		return products;
	}

	@Override
	public Product getProduct(String productCode) {
		return hibernateTemplate.get(Product.class, productCode);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public List<Productsorted> getAllSortedProducts(String sortBy, int limit, int offset) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		if (sortBy.isBlank())
			sortBy = "p.product_id DESC";
		String queryString = "SELECT  p.product_id,\n"
				+ "    GROUP_CONCAT(c.category_name) AS category_name, p.product_code,\n"
				+ "    p.product_description,   p.product_name,  p2.price AS product_price,\n"
				+ "    p2.currency, s.inventory_available, s.location FROM \n"
				+ "    Product p LEFT JOIN product_category pc ON p.product_id = pc.product_id\n"
				+ "	   LEFT JOIN Category c ON pc.category_code = c.category_code LEFT JOIN \n"
				+ "    Price p2 ON p.product_id = p2.product_id LEFT JOIN \n"
				+ "    Stock s ON p.product_id = s.product_id GROUP BY p.product_id,\n"
				+ "    p.product_code, p.product_description, p.product_name, p2.price,\n"
				+ "    p2.currency,s.inventory_available, s.location ORDER BY " + sortBy;

		Query query = session.createNativeQuery(queryString);
		query.setResultTransformer(Transformers.aliasToBean(Productsorted.class));
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Productsorted> products = query.getResultList();
		return products;
	}

	@Override
	@Transactional
	public int getCount() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String queryString = "FROM Product";
		TypedQuery<Product> query = session.createQuery(queryString, Product.class);
		List<Product> products = query.getResultList();

		return products.size();
	}

	@Override
	@Transactional
	public void delete(Long productId) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Product product = session.find(Product.class, productId);
		session.delete(product);
	}
}
