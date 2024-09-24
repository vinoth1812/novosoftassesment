package com.example.novosoft.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.novosoft.dto.ExternalPriceResponse;

import com.example.novosoft.entity.Product;
import com.example.novosoft.repos.ProductRepository;
@Service
public class ExternalApiService {
	
	 private final RestTemplate restTemplate;
	 
	 
	    private final String EXTERNAL_API_URL = "https://ffc4ce27-c18f-4e63-9721-8b9bd42a4288.mock.pstmn.io"; // Replace with actual URL

	    @Autowired
	    public ExternalApiService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	    /**
	     * Fetches the latest price for a given product from the external API.
	     * This method is cached to avoid excessive API calls.
	     *x
	     * @param productId The ID of the product.
	     * @return The latest price as a Double.
	     */
	    @Cacheable(value = "productPrices", key = "#productId")
	    public Double fetchLatestPrice(Long productId) {
	        ResponseEntity<ExternalPriceResponse> response = restTemplate.getForEntity(EXTERNAL_API_URL, ExternalPriceResponse.class, productId);
	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            return response.getBody().getLatestPrice();
	        } else {
	            // Handle unsuccessful response or null body
	            throw new RuntimeException("Failed to fetch latest price for product ID: " + productId);
	        }
	    }

	    /**
	     * Fetches and updates the prices of all products.
	     *
	     * @param productRepository The repository to interact with the Product entity.
	     */
	    public void updateAllProductPrices(ProductRepository productRepository) {
	        List<Product> products = productRepository.findAll();
	        for (Product product : products) {
	            try {
	                Double latestPrice = fetchLatestPrice(product.getId());
	                product.setPrice(BigDecimal.valueOf(latestPrice));
	                productRepository.save(product);
	            } catch (Exception e) {
	                // Log the exception and continue with next product
	                System.err.println("Error updating price for product ID " + product.getId() + ": " + e.getMessage());
	            }
	        }
	    }

}
