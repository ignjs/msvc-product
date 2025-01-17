package com.ign.springcloud.msvc.products.controller;

import org.springframework.web.bind.annotation.RestController;
import com.ign.springcloud.msvc.products.entity.Product;
import com.ign.springcloud.msvc.products.service.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * ProductController is a REST controller that handles HTTP requests for
 * managing products.
 * It provides endpoints to list, retrieve, create, update, and delete products.
 * 
 * @author
 */
@RestController
public class ProductController {

	final private ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	/**
	 * Handles HTTP GET requests to retrieve a list of all products.
	 *
	 * @return a list of all products
	 */
	@GetMapping
	public List<Product> list() {
		return this.service.findAll();
	}

	/**
	 * Retrieves the details of a product by its ID.
	 *
	 * @param id the ID of the product to retrieve
	 * @return a ResponseEntity containing the product details if found, or a 404
	 *         Not Found status if not found
	 * @throws InterruptedException if the thread is interrupted while sleeping
	 * @throws RuntimeException     if the product ID is 10, indicating the product
	 *                              is not found
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Product> details(@PathVariable Long id) throws InterruptedException {

		/*
		 * if (id.equals(10L)) {
		 * throw new RuntimeException("Product not found");
		 * }
		 * 
		 * if (id.equals(7L)) {
		 * TimeUnit.SECONDS.sleep(3L);
		 * }
		 */

		Optional<Product> productOptional = service.findById(id);
		return productOptional.isPresent() ? ResponseEntity.ok(productOptional.orElseThrow())
				: ResponseEntity.notFound().build();
	}

	/**
	 * Creates a new product.
	 *
	 * @param product the product to be created
	 * @return a ResponseEntity containing the created product and the HTTP status
	 *         code 201 (Created)
	 */
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(product));
	}

	/**
	 * Updates an existing product with the given ID.
	 *
	 * @param id      the ID of the product to update
	 * @param product the product details to update
	 * @return a ResponseEntity containing the updated product if found, or a 404
	 *         Not Found status if the product does not exist
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
		Optional<Product> productOptional = service.findById(id);
		if (productOptional.isPresent()) {
			Product productDB = productOptional.orElseThrow();
			productDB.setName(product.getName());
			productDB.setPrice(product.getPrice());
			productDB.setCreateAt(product.getCreateAt());
			return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(productDB));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Deletes a product by its ID.
	 *
	 * @param id the ID of the product to be deleted
	 * @return a ResponseEntity with status NO_CONTENT if the product was
	 *         successfully deleted,
	 *         or NOT_FOUND if the product with the given ID does not exist
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Product> productOptional = service.findById(id);
		if (productOptional.isPresent()) {
			this.service.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.notFound().build();
	}

}
