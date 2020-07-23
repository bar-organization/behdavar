package com.bar.behdavarapplication;

import com.bar.behdavarbackend.BehdavarBackendApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(BehdavarBackendApplication.class)
@SpringBootApplication
public class BehdavarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BehdavarApplication.class, args);
    }

}
