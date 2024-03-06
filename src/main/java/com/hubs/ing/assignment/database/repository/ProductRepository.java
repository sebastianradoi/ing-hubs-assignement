package com.hubs.ing.assignment.database.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hubs.ing.assignment.database.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	boolean existsByName(String name);

	Optional<Product> findByName(String name);

}
