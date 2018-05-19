package com.jonminter.nytmoviepicks;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/critic-reviews-with-ratings")
public class MovieReviewsAndRatingsController {
  MovieReviewsRatingsFacade reviewsAndRatings;
  
  @Autowired
  public MovieReviewsAndRatingsController(MovieReviewsRatingsFacade reviewsAndRatings) {
    this.reviewsAndRatings = reviewsAndRatings;
  }
  
  @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<MovieItem>> getCriticReviewsAndRatings() {
    return null;
  }
}
