package com.example.novosoft.service;

import java.util.List;

import com.example.novosoft.dto.UserDto;
import com.example.novosoft.entity.User;



public interface UserSERVICE {
	
	 void saveUser(UserDto userDto);

	    User findByEmail(String email);

	    List<UserDto> findAllUsers();
	}



