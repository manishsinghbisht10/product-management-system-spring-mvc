package com.manish.repository;

import java.util.Objects;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.manish.Entity.Category;
import jakarta.transaction.Transactional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public Category saveCategory(String name) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String queryString = "FROM Category WHERE categoryName = :name";
		Category category = session.createQuery(queryString, Category.class).setParameter("name", name).uniqueResult();

		if (Objects.isNull(category)) {
			Category categorySave = new Category();
			categorySave.setCategoryName(name);
			session.save(categorySave);
			return categorySave;
		} else
			return category;
	}
}
