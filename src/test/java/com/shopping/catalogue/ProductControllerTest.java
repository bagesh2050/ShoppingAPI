package com.shopping.catalogue;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.catalogue.Application;
import com.shopping.catalogue.controller.ProductController;
import com.shopping.catalogue.entity.Product;
import com.shopping.catalogue.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(Application.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	ProductService productCatalogueService;

	@MockBean
	ProductController target;

	List<Product> productList;

	Product product;

	private Map<String, String> allRequstParam;

	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		product = new Product();
		product.setId(1);
		product.setName("Belt");
		product.setBrandId("LEVIS");
		product.setColor("BROWN");
		product.setQuantity(3);

		productList = new ArrayList<>();
		productList.add(product);

		allRequstParam = new HashMap<>();
		allRequstParam.put("productType", "cosmetics");

		objectMapper = new ObjectMapper();
	}

	@Test
	public void testGetAllProduct() throws Exception {
		given(target.getAllProduct()).willReturn(productList);

		mockMvc.perform(get("/v1/product/all")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
	}
}
