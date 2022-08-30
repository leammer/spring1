	package ru.vasiljeva.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasiljeva.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>{
//	public void addProduct(Product product);
//
//	public void updateProduct(Product product);
//
//	public void removeProduct(Long id);
//
//	public Product getProductById(Long id);
//
//	public List<Product> getAll();
}
