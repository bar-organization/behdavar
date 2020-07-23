package com.bar.behdavarbackend;

import com.bar.behdavardatabase.BehdavarDatabaseApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(BehdavarDatabaseApplication.class)
@SpringBootApplication
public class BehdavarBackendApplication {

}
