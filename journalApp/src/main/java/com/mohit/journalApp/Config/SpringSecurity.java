
// that class use for spring security custom mization according to our application
package com.mohit.journalApp.Config;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;


import com.mohit.journalApp.service.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration// mark for spring it is confi.type bean
@EnableWebSecurity// it is tell about spring i am now customizing spring security  
public class SpringSecurity {
@Autowired
    private UserDetailsServiceImpl userDetailsService ;
    
    
    
	@Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
        .csrf(csrf -> csrf.disable())  // Disable CSRF protection for REST APIs
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session management
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/createuser").permitAll()
            .requestMatchers("/user/changepassword").authenticated()        // user access control
            .anyRequest().authenticated()                  // All other requests must be authenticated
        )
        
        
        .userDetailsService(userDetailsService)// Link custom UserDetailsService
    	.httpBasic(httpBasic -> httpBasic
                .authenticationEntryPoint(customAuthenticationEntryPoint())  // Custom handler for 401 Unauthorized
            );
    return http.build();
    }
	
	 @Bean
	    AuthenticationEntryPoint customAuthenticationEntryPoint() {
	        return new AuthenticationEntryPoint() {
	            @Override
	            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
	                // Custom response for unauthorized access
	                response.setStatus(HttpStatus.UNAUTHORIZED.value());
	                response.setContentType("application/json");
	                response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"You must provide valid credentials to access this resource.\"}");
	            }

				
	        };
	    }
    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
     AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	}

 