package com.example.novosoft.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.novosoft.repos.ProductRepository;
import com.example.novosoft.service.ExternalApiService;

public class PriceUpdateScheduler {
	
	 private final ExternalApiService externalApiService;
	    private final ProductRepository productRepository;

	    @Autowired
	    public PriceUpdateScheduler(ExternalApiService externalApiService, ProductRepository productRepository) {
	        this.externalApiService = externalApiService;
	        this.productRepository = productRepository;
	    }

	   
	    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
	    public void updateProductPrices() {
	        System.out.println("Starting scheduled task to update product prices...");
	        externalApiService.updateAllProductPrices(productRepository);
	        System.out.println("Completed scheduled task to update product prices.");
	    }

}
