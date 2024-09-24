package com.example.novosoft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.novosoft.repos.ProductRepository;
import com.example.novosoft.service.ProductService;

import com.example.novosoft.entity.Product;

public class ProductServiceTest {
	
	

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100));

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product created = productService.createProduct(product);
        assertEquals("Test Product", created.getName());
        verify(productRepository).save(product);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product found = productService.getProductById(1L);
        assertEquals("Test Product", found.getName());
    }

    // Add more tests for update and delete
}


