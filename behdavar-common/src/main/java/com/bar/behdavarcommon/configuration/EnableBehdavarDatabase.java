package com.bar.behdavarcommon.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScans({@ComponentScan(basePackages = "com.bar.behdavardatabase"),
        @ComponentScan(basePackages = "com.bar.behdavarcommon")})
public @interface EnableBehdavarDatabase {
}
