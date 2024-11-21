package com.example.libraryrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LibraryRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryRestApplication.class, args);
    }

}
