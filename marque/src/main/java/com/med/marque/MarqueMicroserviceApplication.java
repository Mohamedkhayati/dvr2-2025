package com.med.marque;

import com.med.marque.entities.Marque;
import com.med.marque.repos.MarqueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
@EnableFeignClients

@SpringBootApplication
public class MarqueMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarqueMicroserviceApplication.class, args);
    }

    // Initialize data
    @Bean
    CommandLineRunner initData(MarqueRepository repository) {
        return args -> {
            repository.save(Marque.builder().nom("Optimum Nutrition").pays("USA").Suppnom("Créatine").build());
        };
    }
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
