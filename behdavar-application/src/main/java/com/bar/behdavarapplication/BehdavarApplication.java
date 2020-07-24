package com.bar.behdavarapplication;

import com.bar.behdavarbackend.configuration.EnableBehdavarBackend;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBehdavarBackend
@SpringBootApplication
public class BehdavarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BehdavarApplication.class, args);
    }

}
