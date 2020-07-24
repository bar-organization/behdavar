package com.bar.behdavardatabase.configuration;

import com.bar.behdavardatabase.BehdavarDatabaseApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(BehdavarDatabaseApplication.class)
public @interface EnableBehdavarDatabase {
}
