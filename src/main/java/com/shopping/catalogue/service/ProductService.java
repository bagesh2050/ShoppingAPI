package com.shopping.catalogue.service;

import java.util.List;

import com.shopping.catalogue.entity.Product;
import com.shopping.catalogue.helper.ProductSearchParameters;

public interface ProductService {

	List<Product> getAllProduct();

	Product addProduct(Product product);

	List<Product> searchProduct(ProductSearchParameters searchParameters);

	String deleteProduct(long id);

}