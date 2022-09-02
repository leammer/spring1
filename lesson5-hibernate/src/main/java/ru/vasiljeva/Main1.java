package ru.vasiljeva;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.cfg.Configuration;

import ru.vasiljeva.model.Product;

public class Main1 {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// INSERT
		{
			System.out.println("Insert products to table");

			entityManager.getTransaction().begin();

			entityManager.persist(new Product("Milk", new BigDecimal(10.5)));
			entityManager.persist(new Product("Bread", new BigDecimal(18.0)));
			entityManager.persist(new Product("Carrot", new BigDecimal(32.50)));
			entityManager.persist(new Product("Onion", new BigDecimal(400.00)));
			entityManager.persist(new Product("Chicken", new BigDecimal(429.53)));

			entityManager.getTransaction().commit();
		}

		// GET BY ID
		{
			entityManager.getTransaction().begin();
			Product product = entityManager.find(Product.class, 1L);
			System.out.println("Get product " + product);
		}

		// GET ALL
		{
			List<Product> products = entityManager
					.createQuery("SELECT p FROM Product p WHERE p.id IN (1, 2)", Product.class).getResultList();
			for (Product product : products) {
				System.out.println(product);
			}
		}

		// UPDATE
		{
			Product product = entityManager.find(Product.class, 1L);
			product.setName("New Title");

			entityManager.getTransaction().commit();
		}

		{
			entityManager.getTransaction().begin();

			Product product = new Product("Product2", new BigDecimal(20.0));
			product.setId(2L);
			entityManager.merge(product);

			entityManager.getTransaction().commit();
		}

		// DELETE
		{
			entityManager.getTransaction().begin();
			Product product = entityManager.find(Product.class, 2L);
			entityManager.remove(product);

			entityManager.createQuery("DELETE FROM Product p where p.id = 3").executeUpdate();

			entityManager.getTransaction().commit();

		}
		entityManager.close();
		entityManagerFactory.close();

	}

}
