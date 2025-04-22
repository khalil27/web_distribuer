package com.esprit.api_getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGetwayApplication.class, args);
    }
    @Bean
    public RouteLocator getwayRoutes(RouteLocatorBuilder builder)
    {
        return builder.routes()
                // Route vers le service gestion_utilisateur
                .route("gestion_utilisateur", r -> r
                        .path("/gestion_utilisateur/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8083"))

                // Route vers le service competences
                .route("competence", r -> r
                        .path("/competences/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8084"))

                // Route vers le service rating
                .route("rating-service", r -> r
                        .path("/rating/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8082/ratingService"))

                // Route vers le service gamification (skillexchange)
                .route("skillexchange", r -> r
                        .path("/gamification/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8081/gamification"))

                .build();
    }

}
