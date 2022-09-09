package com.durgesh.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.durgesh.repo.UserRepository;
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    private UserRepository userRepo;
// 
//	 @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http.csrf().disable();
//	        http.authorizeRequests().anyRequest().permitAll();
//	        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//	    }
	 
    
	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	     http.csrf().disable();
	     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	      
	     http.authorizeRequests()
	             .antMatchers("/auth/login").permitAll()
	             .anyRequest().authenticated();
	 }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(
            username -> userRepo.findByEmail(username)
                .orElseThrow(
                    () -> new UsernameNotFoundException("User " + username + " not found.")));
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }  
 
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
}