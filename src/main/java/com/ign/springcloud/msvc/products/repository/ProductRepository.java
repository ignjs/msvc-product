package com.ign.springcloud.msvc.products.repository;

import org.springframework.data.repository.CrudRepository;

import com.ign.libs.msvc.commons.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
