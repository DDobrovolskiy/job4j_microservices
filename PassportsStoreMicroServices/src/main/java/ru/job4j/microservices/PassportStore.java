package ru.job4j.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PassportStore {
    public static void main(String[] args) {
        SpringApplication.run(PassportStore.class, args);
        System.out.println("RUN");
    }
}
