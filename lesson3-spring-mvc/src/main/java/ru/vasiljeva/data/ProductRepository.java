package ru.vasiljeva.data;

import java.util.List;

public interface ProductRepository {

	public void addProduct(Long id, Product product);

	public Product findProductById(Long id);

	public List<Product> getAll();
}
