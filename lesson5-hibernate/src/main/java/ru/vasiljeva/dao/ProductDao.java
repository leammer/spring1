	package ru.vasiljeva.dao;

import java.util.List;

import ru.vasiljeva.model.Product;

public interface ProductDao {
	public void addProduct(Product product);

	public void updateProduct(Product product);

	public void removeProduct(Long id);

	public Product getProductById(Long id);

	public List<Product> getAll();
}
