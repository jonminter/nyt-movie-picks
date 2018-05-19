package com.jonminter.nytmoviepicks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public class MovieReviewsAndRatingsControllerTest {
  MovieReviewsRatingsFacade reviewsAndRatings;
  MovieReviewsAndRatingsController controller;
  
  @BeforeEach
  public void setUp() {
    reviewsAndRatings = mock(MovieReviewsRatingsFacade.class);
    controller = new MovieReviewsAndRatingsController(reviewsAndRatings);
  }
  
  @Test
  public void testRetrievesCriticReviewsAndConsumerRatings() {
    List<MovieItem> movieItems = new ArrayList<>();
    
    when(reviewsAndRatings.getCriticReviewsAndRatings()).thenReturn(Mono.just(movieItems));
    
    ResponseEntity<List<MovieItem>> result = controller.getCriticReviewsAndRatings();
    
    assertEquals(result.getStatusCode(), HttpStatus.OK);
    assertSame(movieItems, result.getBody());
  }
}
