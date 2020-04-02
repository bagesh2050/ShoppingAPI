package com.shopping.catalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.shopping.catalogue.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategoryIdAndName(String categoryId, String name);

	List<Product> findByName(String name);

	List<Product> findByBrandId(String brandId);

	List<Product> findByCategoryIdAndBrandId(String categoryId, String brandId);

	List<Product> findByCategoryIdAndColor(String categoryId, String color);

	List<Product> findByColor(String color);

	List<Product> findByCategoryIdAndPrice(String categoryId, String price);

	List<Product> findByPrice(String price);

	@Modifying
	@Transactional
	@Query("delete from Product products where products.id = :id ")
	void deleteById(@Param("id") Long id);
}
