package com.bar.behdavarapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;


@ComponentScans({@ComponentScan(basePackages = "com.bar.behdavarbackend"),
        @ComponentScan(basePackages = "com.bar.behdavarcommon"),
        @ComponentScan(basePackages = "com.bar.behdavardatabase")})
@SpringBootApplication
public class BehdavarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BehdavarApplication.class, args);
    }

}
