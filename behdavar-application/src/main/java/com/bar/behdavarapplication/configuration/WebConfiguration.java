package com.bar.behdavarapplication.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/app/**/*")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath,
                                                   Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                : new ClassPathResource("/static/index.html");
                    }
                });
    }

    //   TODO Only for disable cors
    //    @Bean
    //    CorsConfigurationSource corsConfigurationSource() {
    //        UrlBasedCorsConfigurationSource source = new
    //                UrlBasedCorsConfigurationSource();
    //        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    //        return source;
    //    }
    //    @Override
    //    public void addCorsMappings(CorsRegistry registry) {
    //        registry.addMapping("/**")
    //                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
    //                .exposedHeaders("Authorization");
    //    }
}
