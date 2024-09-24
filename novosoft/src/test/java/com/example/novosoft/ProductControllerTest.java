package com.example.novosoft;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.novosoft.controller.ProductController;
import com.example.novosoft.service.ProductService;

import com.example.novosoft.entity.Product;

public class ProductControllerTest {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @Mock
	    private ProductService productService;

	    @InjectMocks
	    private ProductController productController;

	    @BeforeEach
	    void setup() {
	        MockitoAnnotations.openMocks(this);
	        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	    }

	    @Test
	    public void testGetProduct() throws Exception {
	        Product product = new Product();
	        product.setId(1L);
	        product.setName("Test Product");

	        when(productService.getProductById(1L)).thenReturn(product);

	        mockMvc.perform(get("/api/products/1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.name").value("Test Product"));
	    }


}
