package com.example.novosoft.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	 @Bean
	    public GroupedOpenApi api() {
	        return GroupedOpenApi.builder()
	                .group("v1")
	                .pathsToMatch("/api/**")
	                .build();
	    }	
	

}
