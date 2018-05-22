package com.jonminter.nytmoviepicks;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import com.google.common.net.HttpHeaders;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

public class MovieRatingsServiceTest {
  MockWebServer mockHttpServer;
  WebClient webClient;
  MovieRatingsService service;

  @BeforeEach
  public void setUp() throws IOException {
    mockHttpServer = new MockWebServer();
    mockHttpServer.start();

    webClient = WebClient.builder()
        .baseUrl(mockHttpServer.url("/").toString())
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
        .build();
    
    service = new MovieRatingsService(webClient);
  }
  
  @AfterEach
  public void tearDown() throws IOException {
    mockHttpServer.shutdown();
  }
  
  @Test
  public void testGetRatings() throws InterruptedException, IOException {
    String movieTitle = "First Reformed";
    
    String ratingJson = TestUtils.getResourceContents("stubs/omdb/firstReformed.json"); 
        
    MockResponse response = new MockResponse();
    response.setResponseCode(200);
    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
    response.setBody(ratingJson);
    
    mockHttpServer.enqueue(response);
    
    MovieRating expectedRating = MovieRating
        .builder()
        .imdbRating("6.0")
        .metascore("79")
        .build();
    
    MovieRating actualRating = service.getRatingsForMovie(movieTitle).block();
    
    assertThat(actualRating).isEqualToComparingFieldByField(expectedRating);
    
    RecordedRequest actualRequest = mockHttpServer.takeRequest();
    assertEquals("/?t=First%20Reformed", actualRequest.getPath());
    assertEquals("get", actualRequest.getMethod().toLowerCase());
  }
}
