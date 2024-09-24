package com.example.novosoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.novosoft.dto.UserDto;
import com.example.novosoft.entity.Product;
import com.example.novosoft.entity.User;
import com.example.novosoft.repos.ProductRepository;
import com.example.novosoft.service.ProductService;
import com.example.novosoft.service.UserSERVICE;

import jakarta.validation.Valid;

@RestController
	@RequestMapping("/api")
	public class ProductController {

	    private final ProductService productService;
	    
	    @Autowired
	    ProductRepository productRepository;
	    
	    private UserSERVICE userService;

	    @Autowired
	    public ProductController(ProductService productService) {
	       this.productService = productService;
	    }

	    /**
	     * GET /api/products
	     * Retrieve all products.
	     */
	    @GetMapping("products/Get")
	    public ResponseEntity<List<Product>> getAllProducts() {
	        List<Product> products = productService.getAllProducts();
	        return ResponseEntity.ok(products);
	    }

	    /**
	     * GET /api/products/{id}
	     * Retrieve a product by ID.
	     */
	    @GetMapping("products/{id}")
	    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	        Product product = productService.getProductById(id);
	        return ResponseEntity.ok(product);
	    }

	    /**
	     * POST /api/products
	     * Create a new product.
	     */
	    @PostMapping("products/creat")
	    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
	        Product created = productService.createProduct(product);
	        return ResponseEntity.ok(created);
	    }

	    /**
	     * PUT /api/products/{id}
	     * Update an existing product.
	     */
	    @PutMapping("Product/{id}")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
	                                                 @Valid @RequestBody Product productDetails) {
	        Product updated = productService.updateProduct(id, productDetails);
	        return ResponseEntity.ok(updated);
	    }

	    /**
	     * DELETE /api/products/{id}
	     * Delete a product by ID.
	     */
	    @DeleteMapping("productDelete/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/page")
	    public ResponseEntity<Page<Product>> getProducts(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "10") int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<Product> productPage = productRepository.findAll(pageable);
	        return ResponseEntity.ok(productPage);
	    }
	    
	    @PostMapping("/register/save")
	    public String registration(@Valid @ModelAttribute("user") UserDto user,
	                               BindingResult result,
	                               Model model){
	        User existing = userService.findByEmail(user.getEmail());
	        if (existing != null) {
	            result.rejectValue("email", null, "There is already an account registered with that email");
	        }
	        if (result.hasErrors()) {
	            model.addAttribute("user", user);
	            return "register";
	        }
	        userService.saveUser(user);
	        return "redirect:/register?success";
	    }
	    @GetMapping("/login")
	    public String loginForm() {
	        return "login";
	    }

	}
	
	


