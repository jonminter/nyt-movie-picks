package com.jonminter.nytmoviepicks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:swagger.properties")
public class NytMoviePicksRatingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NytMoviePicksRatingsApplication.class, args);
	}
}
