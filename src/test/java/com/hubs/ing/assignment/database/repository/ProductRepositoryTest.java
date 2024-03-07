package com.hubs.ing.assignment.database.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hubs.ing.assignment.database.entities.Product;

@DataJpaTest
class ProductRepositoryTest {
	@Autowired
	private ProductRepository productRepository;
	private Product product;

	@BeforeEach
	void setUp() {
		product = new Product();
		product.setName("test");
		product.setPrice(10.0);
		product.setDescription("test description");
		product = productRepository.save(product);
	}

	@Test
	void assertProductExistsByName() {
		assertTrue(productRepository.existsByName("test"));
	}

	@Test
	void assertProductNotFound() {
		assertFalse(productRepository.existsByName("non-existing-name"));
	}

	@Test
	void assertProductIsFoundByName() {
		var foundProduct = productRepository.findByName("test");
		assertTrue(foundProduct.isPresent());
		assertEquals(product, foundProduct.get());
	}

	@Test
	void assertProductIsNotFoundByName() {
		var foundProduct = productRepository.findByName("non-existing-name");
		assertTrue(foundProduct.isEmpty());
	}
}