package com.durgesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}
	
//	@Bean
//	 BCryptPasswordEncoder passwordEncoder() {
//		
//		return new BCryptPasswordEncoder();
//	}

}
