package com.example.novosoft.dto;

public class ExternalPriceResponse {
	
	  private Long productId;
	    private Double latestPrice;

	    // Constructors
	    public ExternalPriceResponse() {}

	    public ExternalPriceResponse(Long productId, Double latestPrice) {
	        this.productId = productId;
	        this.latestPrice = latestPrice;
	    }

	    // Getters and Setters
	    public Long getProductId() {
	        return productId;
	    }

	    public void setProductId(Long productId) {
	        this.productId = productId;
	    }

	    public Double getLatestPrice() {
	        return latestPrice;
	    }

	    public void setLatestPrice(Double latestPrice) {
	        this.latestPrice = latestPrice;
	    }

}
