package ru.vasiljeva.dao;

import java.util.List;

import ru.vasiljeva.model.Product;

public interface ProductDao {
	void addProduct(Product product);

	void updateProduct(Product product);

	void removeProduct(Long id);

	Product getProductById(Long id);

	List<Product> getAll();
}
