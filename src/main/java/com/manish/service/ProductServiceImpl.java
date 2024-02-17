package com.manish.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.manish.Entity.Product;
import com.manish.model.Productsorted;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProductServiceImpl implements ProductService {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public void Save(Product product) {
		hibernateTemplate.save(product);
	}

	@Override
	@Transactional
	public List<Product> getAllProducts() {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String queryString = "FROM Product";
		TypedQuery<Product> query = session.createQuery(queryString, Product.class);
		List<Product> products = query.getResultList();

		return products;
	}

	@Override
	public Product getProduct(String productCode) {
		return hibernateTemplate.get(Product.class, productCode);
	}

	@Override
	@Transactional
	public List<Productsorted> getAllSortedProducts(String sortBy, int limit, int offset) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

		String queryString = "SELECT p.product_code, p.product_description, p.product_name, \n"
				+ " p2.price as product_price, p2.currency, s.inventory_available, s.location, \n"
				+ " c.category_code, c.category_name FROM Product p \n"
				+ " INNER JOIN Price p2 ON p.product_code = p2.product_code  \n"
				+ " INNER JOIN Stock s ON s.product_code = p.product_code  \n"
				+ " INNER JOIN Category c ON c.category_code = p.category ORDER BY " + sortBy;

		SQLQuery query = session.createSQLQuery(queryString);
		query.setResultTransformer(Transformers.aliasToBean(Productsorted.class));
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<Productsorted> products = query.list();
		return products;
	}

}
