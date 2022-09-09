package com.durgesh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cog/v1")
public class SecureApi {
	
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
