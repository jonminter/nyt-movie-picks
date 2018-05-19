package com.jonminter.nytmoviepicks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class MovieRatingsService {
  private WebClient webClient;

  @Autowired
  public MovieRatingsService(@Qualifier("movieRatingsWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  public Mono<MovieRating> getRatingsForMovie(String movieTitle) {
    return null;
  }
}
