package com.example.novosoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> 
                        authorize
                                .requestMatchers("/register/**", "/login", "/register/save**").permitAll()  // Allow access to registration and login pages
                                .requestMatchers(HttpMethod.GET, "/products/Get**").permitAll()  // Allow GET product requests to everyone
                                .requestMatchers(HttpMethod.POST, "/api/products/creat**").hasRole("ADMIN")  // Restrict POST product creation to ADMIN role
                                .requestMatchers(HttpMethod.PUT, "/api/products/{id}**").hasRole("ADMIN")   // Restrict PUT product updates to ADMIN role
                                .requestMatchers(HttpMethod.DELETE, "/api/products/{id}**").hasRole("ADMIN")  // Restrict DELETE product to ADMIN role
                                .anyRequest().authenticated()  // All other requests require authentication
                ).formLogin(
                        form -> form
                              .loginPage("/login")  // Define custom login page
                                .defaultSuccessUrl("/users")  // Redirect to /users upon successful login
                                .permitAll()  // Allow anyone to access login page
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()  // Allow everyone to logout
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
