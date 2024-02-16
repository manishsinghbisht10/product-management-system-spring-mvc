package com.manish.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.manish.Entity.Product;

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

		// Commit the transaction
		return products;
	}

	@Override
	public Product getProduct(int id) {
		return hibernateTemplate.get(Product.class, id);
	}

}
