package com.jonminter.nytmoviepicks;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

public class MovieReviewsRatingsFacade {
  private MovieReviewsService reviewsService;
  private MovieRatingsService ratingsService;
  
  @Autowired
  public MovieReviewsRatingsFacade(
      MovieReviewsService reviewsService,
      MovieRatingsService ratingsService) {
    this.reviewsService = reviewsService;
    this.ratingsService = ratingsService;
  }
  
  public Flux<MovieItem> getCriticReviewsAndRatings() {
    return null;
  }
}
