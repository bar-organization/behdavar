package com.bar.behdavarbackend.configuration;

import com.bar.behdavarbackend.BehdavarBackendApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(BehdavarBackendApplication.class)
public @interface EnableBehdavarBackend {
}
