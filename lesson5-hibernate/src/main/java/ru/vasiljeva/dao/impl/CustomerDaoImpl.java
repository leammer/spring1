package ru.vasiljeva.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.dao.CustomerDao;
import ru.vasiljeva.model.Customer;

@Repository
@Slf4j
public class CustomerDaoImpl implements CustomerDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public void addCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(customer);
		log.info("Customer added successfully. Customer details: " + customer);

	}

	@Transactional
	@Override
	public void updateCustomer(Customer customer) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(customer);
		log.info("Customer was updated. Customer details: " + customer);
	}

	@Transactional
	@Override
	public void removeCustomer(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.load(Customer.class, id);
		if (customer != null) {
			session.remove(customer);
			log.info("Customer was removed.");
		}

		log.info("Couldn't find Customer by id " + id);
	}

	@Transactional
	@Override
	public Customer getCustomerById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Customer customer = (Customer) session.getReference(Customer.class, id);
		log.info("Customer was found. Customer details: " + customer);
		return customer;
	}

	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Customer> list = session.createQuery("from Customer", Customer.class).list();

		for (Customer customer : list) {
			log.info("Customer info: " + customer);
		}

		return list;
	}

}
