package com.jonminter.nytmoviepicks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
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
  
  public Mono<List<MovieItem>> getCriticReviewsAndRatings() {
    List<MovieReview> reviews = reviewsService.getCriticPicks()
      .collectList()
      .block();
    
    List<Mono<MovieItem>> items = new ArrayList<>();
    for (MovieReview review : reviews) {
      
      Mono<MovieItem> itemMono = ratingsService.getRatingsForMovie(review.displayTitle)
          .flatMap(rating -> Mono.just(MovieItem.builder().review(review).rating(rating).build()));
      items.add(itemMono);
    }
    return Mono.zip(
        items, 
        res -> Arrays.asList(Arrays.copyOf(res, res.length, MovieItem[].class)));
  }
}
