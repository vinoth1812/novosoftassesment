package com.example.novosoft.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.novosoft.entity.Product;
import com.example.novosoft.entity.Role;
@Repository
public interface RoleRepositary extends JpaRepository<Role, Long>  {

	Role findByName(String string);

}
