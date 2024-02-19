package com.manish.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.manish.Entity.Category;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public List<Category> saveCategory(String name) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String queryString = "FROM Category WHERE categoryName = :name";
		TypedQuery<Category> query = session.createQuery(queryString, Category.class);
		query.setParameter("name", name);

		List<Category> categories = query.getResultList();

		if (categories.isEmpty()) {
			Category category = new Category();
			category.setCategoryName(name);
			session.save(category);
			TypedQuery<Category> query2 = session.createQuery(queryString, Category.class);
			query2.setParameter("name", name);
			categories = query.getResultList();
		}
		return categories;
	}

}
