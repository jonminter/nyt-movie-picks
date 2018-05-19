package com.jonminter.nytmoviepicks;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.google.common.net.HttpHeaders;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.core.publisher.Flux;

public class MovieReviewServiceTest {
  MockWebServer mockHttpServer;
  WebClient webClient;
  MovieReviewsService service;

  @BeforeEach
  public void setUp() throws IOException {
    mockHttpServer = new MockWebServer();
    mockHttpServer.start();

    webClient = WebClient.builder()
        .baseUrl(mockHttpServer.url("/").toString())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        .build();
    
    service = new MovieReviewsService(webClient);
  }
  
  @AfterEach
  public void tearDown() throws IOException {
    mockHttpServer.shutdown();
  }
  
  @Test
  public void testGetCriticReviews() throws InterruptedException, IOException {
    String criticReviewsJson = TestUtils.getResourceContents("stubs/nytMovieReviews/criticReviews.json"); 
        
    MockResponse response = new MockResponse();
    response.setResponseCode(200);
    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
    response.setBody(criticReviewsJson);
    
    mockHttpServer.enqueue(response);
    
    List<MovieReview> expectedReviews = new ArrayList<>(Arrays.asList(
        MovieReview.build()
          .displayTitle("First Reformed")
          .byline("A. O. SCOTT")
          .headline("Review: ‘First Reformed’ Is an Epiphany. Ethan Hawke Is, Too.")
          .build(),
        MovieReview.build()
          .displayTitle("Sollers Point")
          .byline("GLENN KENNY")
          .headline("Review: In ‘Sollers Point,’ a Hard Road to the Straight and Narrow")
          .build()
    ));
    
    Flux<MovieReview> reviews = service.getCriticPicks();
    List<MovieReview> reviewList = reviews
      .collectList()
      .block();
    
    assertEquals(expectedReviews, reviewList);
    
    RecordedRequest actualRequest = mockHttpServer.takeRequest();
    assertEquals("/reviews/picks.json?order=by-publication-date", actualRequest.getPath());
    assertEquals("get", actualRequest.getMethod().toLowerCase());
  }
}
