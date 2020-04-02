package com.shopping.catalogue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.catalogue.entity.Product;
import com.shopping.catalogue.helper.ProductSearchParameters;
import com.shopping.catalogue.service.ProductService;

@RestController
@RequestMapping("/v1/")
public class ProductController {

	@Autowired
	ProductService productCatalogueService;

	@GetMapping(value = "/product/all")
	@ResponseBody
	public List<Product> getAllProduct() {
		return productCatalogueService.getAllProduct();
	}

	@GetMapping(value = "/product")
	@ResponseBody
	public List<Product> searchProduct(@Validated ProductSearchParameters searchParameters) {
		return productCatalogueService.searchProduct(searchParameters);
	}

	@PostMapping("/product")
	@ResponseBody
	public Product addProduct(@Validated Product product) {
		return productCatalogueService.addProduct(product);
	}

	@DeleteMapping("/product/{id}")
	@ResponseBody
	public String deleteProduct(@PathVariable("id") long id) {
		return productCatalogueService.deleteProduct(id);
	}

}
