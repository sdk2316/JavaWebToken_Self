package com.durgesh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.model.User;
import com.durgesh.service.UserService;



@RestController
@RequestMapping("/users")
public class UserAuthController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	//@RolesAllowed("ROLE_EDITOR")
//	{
//		"email":"dk@gmail.com",
//		"password":"abcd"
//
//		   
//		}
	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userService.saveUser(user);
		
	}
	
	@GetMapping("/getAllUser")
	public List<User> getAllUser(){
		return userService.getAllUser();
		
	}
	
	@GetMapping("/Uk")
	public String hello(){
		return "Welcome to UK";
		
	}
	
	@GetMapping("/cog")
	public String Cog(){
		return "Welcome to Cognizant";
		
	}
	
	
	@GetMapping("/Usa")
	public String USA(){
		return "Welcome to USA";
		
	}
	
	@GetMapping("/India")
	public String India(){
		return "Welcome to India";
		
	}
	
	@GetMapping("/Germany")
	public String Germany(){
		return "Welcome to Germany";
		
	}

}
