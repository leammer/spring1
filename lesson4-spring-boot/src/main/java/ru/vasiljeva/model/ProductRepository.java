package ru.vasiljeva.model;

import java.util.List;

public interface ProductRepository {

	public void addProduct(Long id, Product product);

	public Product getById(Long id);

	public List<Product> getAll();
	
	public void remove(Long id);
	
	public void edit(Long id, Product product);
}
