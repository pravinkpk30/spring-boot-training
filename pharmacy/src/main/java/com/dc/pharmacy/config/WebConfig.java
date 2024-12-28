package com.dc.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   @Override
   public void addCorsMappings(CorsRegistry registry) {
       registry.addMapping("/**") // Allow CORS for all endpoints
               .allowedOrigins("*") // Change to specific origins for production
               .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
   }
}

