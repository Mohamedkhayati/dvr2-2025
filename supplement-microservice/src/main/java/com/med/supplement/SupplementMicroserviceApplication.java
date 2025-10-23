package com.med.supplement;

import com.med.supplement.entities.Supplement;
import com.med.supplement.repos.SupplementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Paths;

@SpringBootApplication
public class SupplementMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplementMicroserviceApplication.class, args);
        // Get project root
        System.out.println("Project root: " + System.getProperty("user.dir"));


        // Alternative: using Paths
        System.out.println("Paths example: " + Paths.get("src/main/resources/application-prod.yml").toAbsolutePath());
    }

    @Bean
    CommandLineRunner commandLineRunner(SupplementRepository repository) {
        return args -> {
            repository.save(Supplement.builder().suppnom("Protéine").prix(50).build());
            repository.save(Supplement.builder().suppnom("Créatine").prix(35).build());
        };
    }
}
