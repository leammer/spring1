package ru.vasiljeva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ru.vasiljeva.model.Role;
import ru.vasiljeva.model.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, QuerydslPredicateExecutor<Role> {
	Role findByName(String name);
	List<Role> findAllByName(String name);
}
