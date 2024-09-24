package com.example.novosoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.novosoft.entity.Product;
import com.example.novosoft.exception.ResourceNotFoundException;
import com.example.novosoft.repos.ProductRepository;
@Service
public class ProductService {
	
	 private final ProductRepository productRepository;
	
	
	
	 @Autowired
	    public ProductService(ProductRepository productRepository) {
	        this.productRepository = productRepository;
	    }

	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    public Product getProductById(Long id) {
	        return productRepository.findById(id)
	               .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
	    }

	    public Product createProduct(Product product) {
	        // Additional business logic can be added here
	        return productRepository.save(product);
	    }

	    public Product updateProduct(Long id, Product productDetails) {
	        Product existingProduct = getProductById(id);
	        existingProduct.setName(productDetails.getName());
	        existingProduct.setDescription(productDetails.getDescription());
	        existingProduct.setPrice(productDetails.getPrice());
	        // Note: createdDate remains unchanged
	        return productRepository.save(existingProduct);
	    }

	    public void deleteProduct(Long id) {
	        Product existingProduct = getProductById(id);
	        productRepository.delete(existingProduct);
	    }
	}

