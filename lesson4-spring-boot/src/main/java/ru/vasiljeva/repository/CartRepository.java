package ru.vasiljeva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ru.vasiljeva.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, QuerydslPredicateExecutor<Cart> {
}
