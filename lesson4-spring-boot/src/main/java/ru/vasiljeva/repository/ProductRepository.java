	package ru.vasiljeva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.vasiljeva.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
