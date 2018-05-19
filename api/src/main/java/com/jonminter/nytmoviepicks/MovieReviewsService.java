package com.jonminter.nytmoviepicks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@Service
public class MovieReviewsService {
	private WebClient webClient;
	
	@Autowired
	public MovieReviewsService(@Qualifier("movieReviewsWebClient") WebClient webClient) {
		this.webClient = webClient;
	}
	
	public Flux<MovieReview> getCriticPicks() {
		return null;
	}
}