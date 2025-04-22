package tn.esprit.ms.competence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CompetenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompetenceApplication.class, args);
    }
}
