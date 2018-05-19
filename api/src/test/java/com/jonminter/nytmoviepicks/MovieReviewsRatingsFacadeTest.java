package com.jonminter.nytmoviepicks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MovieReviewsRatingsFacadeTest {
  MovieReviewsService reviewService;
  MovieRatingsService ratingsService;
  MovieReviewsRatingsFacade reviewsAndRatings;
  
  @BeforeEach
  public void setUp() {
    reviewService = mock(MovieReviewsService.class);
    ratingsService = mock(MovieRatingsService.class);
    reviewsAndRatings = new MovieReviewsRatingsFacade(reviewService, ratingsService);
  }
  
  @Test
  public void testRetrieveRatingsAndReviews() {
    String blackPantherTitle = "Black Panther";
    String thorTitle = "Thor";
    
    MovieReview blackPantherReview = MovieReview.builder()
        .displayTitle(blackPantherTitle)
        .build();
    
    MovieReview thorReview = MovieReview.builder()
        .displayTitle(thorTitle)
        .build();
    
    List<MovieReview> reviewsList = Arrays.asList(blackPantherReview, thorReview);
    
    MovieRating blackPantherRating = MovieRating.builder()
        .imdbRating("10.0")
        .build();
    
    MovieRating thorRating = MovieRating.builder()
        .imdbRating("8.0")
        .build();
    
    when(reviewService.getCriticPicks()).thenReturn(Flux.fromIterable(reviewsList));
    when(ratingsService.getRatingsForMovie(blackPantherTitle)).thenReturn(Mono.just(blackPantherRating));
    when(ratingsService.getRatingsForMovie(thorTitle)).thenReturn(Mono.just(thorRating));
    
    List<MovieItem> items = reviewsAndRatings.getCriticReviewsAndRatings()
        .block();
    
    List<MovieItem> expectedItems = new ArrayList<>(Arrays.asList(
        MovieItem.builder()
          .review(blackPantherReview)
          .rating(blackPantherRating)
          .build(),
        MovieItem.builder()
          .review(thorReview)
          .rating(thorRating)
          .build()));
    
    assertEquals(expectedItems, items);
  }
}
