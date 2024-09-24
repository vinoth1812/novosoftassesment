package com.example.novosoft.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.novosoft.dto.UserDto;
import com.example.novosoft.entity.Role;
import com.example.novosoft.entity.User;
import com.example.novosoft.repos.RoleRepositary;
import com.example.novosoft.repos.UserRepository;


public class UserServiceImp implements UserSERVICE{

	
	
	
	 private UserRepository userRepository;
	    private RoleRepositary roleRepository;
	    private PasswordEncoder passwordEncoder;

	    public UserServiceImp(UserRepository userRepository,
	                           RoleRepositary roleRepository,
	                           PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.roleRepository = roleRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	@Override
	public void saveUser(UserDto userDto) {
		 User user = new User();
	        user.setUsername(userDto.getFirstName() + " " + userDto.getLastName());
	        user.setEmail(userDto.getEmail());

	        //encrypt the password once we integrate spring security
	        //user.setPassword(userDto.getPassword());
	        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	        Role role = roleRepository.findByName("ROLE_ADMIN");
	        if(role == null){
	            role = checkRoleExist();
	        }
	        user.setRoles(Arrays.asList(role));
	        userRepository.save(user);
	    }

		
	

	 @Override
	    public User findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }

	 @Override
	    public List<UserDto> findAllUsers() {
	        List<User> users = userRepository.findAll();
	        return users.stream().map((user) -> convertEntityToDto(user))
	                .collect(Collectors.toList());
	    }
	 private UserDto convertEntityToDto(User user){
	        UserDto userDto = new UserDto();
	        String[] name = user.getUsername().split(" ");
	        userDto.setFirstName(name[0]);
	        userDto.setLastName(name[1]);
	        userDto.setEmail(user.getEmail());
	        return userDto;
	    }

	    private Role checkRoleExist() {
	        Role role = new Role();
	        role.setName("ROLE_ADMIN");
	        return roleRepository.save(role);
	    }

}
