package ru.vasiljeva.data;

import java.util.List;

public interface ProductRepository {

	public void insert(Product product);

	public Product getById(Long id);

	public void remove(Long id);

	public Product update(Product product);

	public List<Product> getAll();
}
