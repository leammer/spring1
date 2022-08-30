package ru.vasiljeva.service;

import java.util.List;

import ru.vasiljeva.model.Product;

public interface ProductService {
	public void addProduct(Product product);

	public void updateProduct(Product product);

	public void removeProduct(Long id);

	public Product getProductById(Long id);

	public List<Product> getAll();
}
