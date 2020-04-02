package com.shopping.catalogue.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.catalogue.entity.Product;
import com.shopping.catalogue.helper.ProductSearchParameters;
import com.shopping.catalogue.repository.ProductRepository;
import com.shopping.catalogue.util.ProductConstants;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productCatalogueRepository;

	@Override
	public List<Product> getAllProduct() {
		return productCatalogueRepository.findAll();
	}

	@Override
	public Product addProduct(Product product) {
		if (product != null)
			return productCatalogueRepository.save(product);
		return new Product();
	}

	@Override
	public List<Product> searchProduct(ProductSearchParameters searchParameters) {

		boolean isCategoryAvailable = false;

		// considering category for filtering only with other single attribute
		if (searchParameters.getCategoryId() != null && !searchParameters.getCategoryId().isEmpty()) {
			isCategoryAvailable = true;
		}

		if (searchParameters.getProductName() != null && !searchParameters.getProductName().isEmpty()) {
			if (isCategoryAvailable) {
				return productCatalogueRepository.findByCategoryIdAndName(searchParameters.getCategoryId(),
						searchParameters.getProductName());
			} else {
				return productCatalogueRepository.findByName(searchParameters.getProductName());
			}
		} else if (searchParameters.getBrandId() != null && !searchParameters.getBrandId().isEmpty()) {
			if (isCategoryAvailable) {
				return productCatalogueRepository.findByCategoryIdAndBrandId(searchParameters.getCategoryId(),
						searchParameters.getBrandId());
			} else {
				return productCatalogueRepository.findByBrandId(searchParameters.getBrandId());
			}
		} else if (searchParameters.getColor() != null && !searchParameters.getColor().isEmpty()) {
			if (isCategoryAvailable) {
				return productCatalogueRepository.findByCategoryIdAndColor(searchParameters.getCategoryId(),
						searchParameters.getColor());
			} else {
				return productCatalogueRepository.findByColor(searchParameters.getColor());
			}
		} else if (searchParameters.getPrice() != null && !searchParameters.getPrice().isEmpty()) {
			if (isCategoryAvailable) {
				return productCatalogueRepository.findByCategoryIdAndPrice(searchParameters.getCategoryId(),
						searchParameters.getPrice());
			} else {
				return productCatalogueRepository.findByPrice(searchParameters.getPrice());
			}
		}

		return Collections.emptyList();
	}

	@Override
	public String deleteProduct(long id) {
		productCatalogueRepository.deleteById(id);
		return ProductConstants.SUCCESS;
	}
}
