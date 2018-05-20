package com.jonminter.nytmoviepicks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApplicationRootController {
  public static class HealthyResponse {
    public final String message = "Welcome";
  }
  
  @GetMapping
  public ResponseEntity<HealthyResponse> root() {
    return ResponseEntity.ok(new HealthyResponse());
  }
}
