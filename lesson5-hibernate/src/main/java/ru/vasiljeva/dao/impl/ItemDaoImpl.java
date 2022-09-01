package ru.vasiljeva.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import ru.vasiljeva.dao.ItemDao;
import ru.vasiljeva.model.Item;

@Repository
@Slf4j
public class ItemDaoImpl implements ItemDao {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public void addItem(Item item) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(item);
		log.info("Item added successfully. Item details: " + item);

	}

	@Transactional
	@Override
	public void updateItem(Item item) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(item);
		log.info("Item was updated. Item details: " + item);
	}

	@Transactional
	@Override
	public void removeItem(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Item item = (Item) session.load(Item.class, id);
		if (item != null) {
			session.remove(item);
			log.info("Item was removed.");
		}

		log.info("Couldn't find Item by id " + id);
	}

	@Transactional
	@Override
	public Item getItemById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Item item = (Item) session.getReference(Item.class, id);
		log.info("Item was found. Item details: " + item);
		return item;
	}

	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public List<Item> getAll() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Item> list = session.createQuery("from Item", Item.class).list();

		for (Item item : list) {
			log.info("Item info: " + item);
		}

		return list;
	}

}
