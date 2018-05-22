package com.jonminter.nytmoviepicks;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Configuration
public class ApplicationBeanConfig {
  private static final Logger logger = LogManager.getLogger(ApplicationBeanConfig.class);
  
  @Bean
  public WebClient movieReviewsWebClient(@Value("${apis.nytReviews.baseUrl}") String apiBaseUrl, @Value("${apis.nytReviews.apiKey}") String apiKey) {
    return WebClient.builder()
        .baseUrl(apiBaseUrl)
        .filter(addApiKeyToUri("apikey", apiKey))
        .filter(logRequest())
        .build();
  }
  
  @Bean
  public WebClient movieRatingsWebClient(@Value("${apis.omdb.baseUrl}") String apiBaseUrl, @Value("${apis.omdb.apiKey}") String apiKey) {
    return WebClient.builder()
        .baseUrl(apiBaseUrl)
        .filter(addApiKeyToUri("apikey", apiKey))
        .filter(logRequest())
        .build();
  }
  
  private static ExchangeFilterFunction addApiKeyToUri(String key, String value) {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
      ClientRequest newRequest;
      try {
         newRequest = ClientRequest.from(clientRequest)
            .url(new URI(clientRequest.url().toString() + "&" + key + "=" + value))
            .build();
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      }
      return Mono.just(newRequest);
    });
  }
  
  private static ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
        logger.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
        clientRequest.headers().forEach((name, values) -> values.forEach(value -> logger.debug("{}={}", name, value)));
        return Mono.just(clientRequest);
    });
}
}
