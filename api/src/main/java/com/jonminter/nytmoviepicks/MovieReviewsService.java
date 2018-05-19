package com.jonminter.nytmoviepicks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class MovieReviewsService {
  public static String ORDER_BY_PUBLICATION_DATE="-by-publication-date";
  
  private WebClient webClient;

  @Autowired
  public MovieReviewsService(@Qualifier("movieReviewsWebClient") WebClient webClient) {
    this.webClient = webClient;
  }

  public Flux<MovieReview> getCriticPicks() {
    return webClient
        .get()
        .uri("/reviews/picks.json?order={order}", ORDER_BY_PUBLICATION_DATE)
        .exchange()
        .flatMap(res -> res.bodyToMono(MovieReviewResponse.class))
        .flatMapMany(reviewRes -> Flux.fromIterable(reviewRes.results));
  }
}
