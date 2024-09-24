package com.example.novosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.novosoft.repos.ProductRepository;
import com.example.novosoft.service.ExternalApiService;

@RestController
public class PriceController {

	
	  private final ExternalApiService externalApiService;
	    private final ProductRepository productRepository;

	    @Autowired
	    public PriceController(ExternalApiService externalApiService, ProductRepository productRepository) {
	        this.externalApiService = externalApiService;
	        this.productRepository = productRepository;
	    }

	    /**
	     * POST /api/prices/update : Manually trigger price update.
	     *
	     * @return A message indicating the update has been initiated.
	     */
	    @PostMapping("/update")
	    public ResponseEntity<String> updatePrices() {
	        externalApiService.updateAllProductPrices(productRepository);
	        return ResponseEntity.ok("Product prices updated successfully.");
	    }
}
