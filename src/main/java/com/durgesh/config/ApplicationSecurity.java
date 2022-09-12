package com.durgesh.config;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.durgesh.jwt.JwtTokenFilter;
import com.durgesh.repo.UserRepository;
@EnableWebSecurity(debug = true)
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(
  //  prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
//)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	
	 @Autowired
	    private UserRepository userRepo;
	 @Autowired private JwtTokenFilter jwtTokenFilter;
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
			.antMatchers("/auth/login", "/users/saveUser").permitAll()
					.anyRequest().authenticated();
			
	        http.exceptionHandling()
	                .authenticationEntryPoint(
	                    (request, response, ex) -> {
	                        response.sendError(
	                            HttpServletResponse.SC_UNAUTHORIZED,
	                            ex.getMessage()
	                        );
	                    }
	                );
	        
			http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
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