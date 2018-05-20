package com.jonminter.nytmoviepicks;

import java.time.LocalDate;
import java.util.Map;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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

    public MovieReview build() {
      return review;
    }
  }
  
  @Override
  public String toString() {
    return String.format("%s {displayTitle=%s, byline=%s, headline=%s}",
        super.toString(), displayTitle, byline, headline);
  }
  
  @Override
  public int hashCode() {
    return new HashCodeBuilder(19, 29).
      append(displayTitle).
      append(byline).
      append(headline).
      toHashCode();
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
    return new EqualsBuilder()
        .append(displayTitle, other.displayTitle)
        .append(byline, other.byline)
        .append(headline, other.headline)
        .isEquals();
  }
}
