package ru.vasiljeva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.vasiljeva.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select c from Product c where (?1 is null or c.cost >= ?1) and (?2 is null or c.cost <= ?2)")
	List<Product> findAll(double minPrice, double maxPrice);
}
