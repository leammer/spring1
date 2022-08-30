package ru.vasiljeva.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.dao.ProductDao;
import ru.vasiljeva.model.Product;

//@Repository
//@Slf4j
public class ProductDaoImpl {//implements ProductDao {

	/*private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public void addProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(product);
		log.info("Product added successfully. Product details: " + product);

	}

	@Transactional
	@Override
	public void updateProduct(Product product) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(product);
		log.info("Product was updated. Product details: " + product);
	}

	@Transactional
	@Override
	public void removeProduct(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.load(Product.class, id);
		if (product != null) {
			session.remove(product);
			log.info("Product was removed.");
		}

		log.info("Couldn't find product by id " + id);
	}

	@Transactional
	@Override
	public Product getProductById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Product product = (Product) session.getReference(Product.class, id);
		log.info("Product was found. Product details: " + product);
		return product;
	}

	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Product> list = session.createQuery("from Product", Product.class).list();

		for (Product product : list) {
			log.info("Product info: " + product);
		}

		return list;
	}*/

}
