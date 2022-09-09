package com.durgesh;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreatePassword {
	public static void main(String[] args) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String password = passwordEncoder.encode("gupta");
	        System.out.println(password);
	}

}
