package com.tn.skillexchange.skillexchange.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permet l'accès à l'API depuis le frontend Angular (localhost:4200)
        registry.addMapping("/api/**")  // Le pattern d'URL que tu veux autoriser
                .allowedOrigins("http://localhost:4200")  // L'URL de ton frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE");  // Les méthodes HTTP autorisées
    }
}