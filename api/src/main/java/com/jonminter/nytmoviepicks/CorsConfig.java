package com.jonminter.nytmoviepicks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import io.swagger.models.HttpMethod;

@Configuration
public class CorsConfig {
  private static final Logger logger = LogManager.getLogger(CorsConfig.class);

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        logger.debug("Setting up CORS headers");
        registry.addMapping("/v1/**").allowedOrigins("*").allowedMethods(HttpMethod.GET.toString(),
            HttpMethod.POST.toString(), HttpMethod.PUT.toString(), HttpMethod.DELETE.toString(),
            HttpMethod.HEAD.toString(), HttpMethod.OPTIONS.toString());
      }
    };
  }
}
