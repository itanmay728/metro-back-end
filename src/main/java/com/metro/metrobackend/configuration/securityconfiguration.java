package com.metro.metrobackend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

@Configuration
public class securityconfiguration {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
 
   @Bean
    public UserDetailsService userDetailsService() {
        // empty user store — so no users, no password generated
        return new InMemoryUserDetailsManager();
    }
   
   
   @Bean
   public WebMvcConfigurer corsConfigurer() {
       return new WebMvcConfigurer() {
           @Override
           public void addCorsMappings(CorsRegistry registry) {
               registry.addMapping("/**") // all endpoints
                       .allowedOrigins("http://localhost:5173") // your frontend
                       .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                       .allowedHeaders("*");
           }
       };
   }
   
   @Bean
   public ObjectMapper objectMapper() {
       ObjectMapper mapper = new ObjectMapper();
       // Automatically convert camelCase → snake_case in JSON
       mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
       return mapper;
   }

}
