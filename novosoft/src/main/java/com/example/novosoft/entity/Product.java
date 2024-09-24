package com.example.novosoft.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "products", indexes = @Index(name = "idx_product_name", columnList = "name"))

public class Product {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotBlank(message = "Product name is mandatory")
	    private String name;
	    private String description;
	    @NotNull(message = "Price is mandatory")
	    private BigDecimal price;
	    private LocalDateTime createdDate;
	    
	    
	    
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public BigDecimal getPrice() {
			return price;
		}
		public void setPrice(BigDecimal price) {
			this.price = price;
		}
		public LocalDateTime getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}
	    
	    
	    

}
