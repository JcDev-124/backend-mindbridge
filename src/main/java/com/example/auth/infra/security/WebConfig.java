package com.example.auth.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Value("${cors.origins}")
    private String corsOrigins;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOriginPatterns(corsOrigins.split(","))
                        .allowedOrigins("http://127.0.0.1:8083") // Adiciona o domínio específico
                        // Use allowedOriginPatterns
                        .allowedHeaders("*"); // Adiciona allowedHeaders
            }
        };
    }
}
