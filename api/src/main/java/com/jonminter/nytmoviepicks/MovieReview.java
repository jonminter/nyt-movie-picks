package com.jonminter.nytmoviepicks;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.MoreObjects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieReview {
  public String displayTitle;
  public String byline;
  public String headline;
  public LocalDate publicationDate;
  public LocalDate openingDate;
  public String articleUrl;
  public String imageUrl;
  
  @JsonProperty("link")
  private void unpackArticleUrl(Map<String, String> article) {
    articleUrl = article.get("url");
  }
  
  @JsonProperty("multimedia")
  private void unpackImageUrl(Map<String, String> multimedia) {
    imageUrl = multimedia.get("src");
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private MovieReview review;

    public Builder() {
      this.review = new MovieReview();
    }

    public Builder displayTitle(String displayTitle) {
      review.displayTitle = displayTitle;
      return this;
    }

    public Builder byline(String byline) {
      review.byline = byline;
      return this;
    }

    public Builder headline(String headline) {
      review.headline = headline;
      return this;
    }
    
    public Builder publicationDate(LocalDate publicationDate) {
      review.publicationDate = publicationDate;
      return this;
    }
    
    public Builder openingDate(LocalDate openingDate) {
      review.openingDate = openingDate;
      return this;
    }
    
    public Builder articleUrl(String articleUrl) {
      review.articleUrl = articleUrl;
      return this;
    }
    
    public Builder imageUrl(String imageUrl) {
      review.imageUrl = imageUrl;
      return this;
    }

    public MovieReview build() {
      return review;
    }
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("displayTitle", displayTitle)
        .add("byline", byline)
        .add("headline", headline)
        .toString();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(displayTitle, byline, headline);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    MovieReview other = (MovieReview) obj;
    return Objects.equals(displayTitle, other.displayTitle) &&
        Objects.equals(byline, other.byline) &&
        Objects.equals(headline, other.headline);
  }
}
