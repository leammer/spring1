package ru.vasiljeva.model;

import java.util.List;

public interface ProductRepository {

	public void insert(Product product);

	public Product getById(Long id);

	public List<Product> getAll();
	
	public void remove(Long id);
	
	public void save(Product product);
}
