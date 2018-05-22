package com.jonminter.nytmoviepicks;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.base.MoreObjects;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MovieRating {
  @JsonProperty("Metascore")
  public String metascore;
  @JsonProperty("imdbRating")
  public String imdbRating;
  
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private MovieRating rating;

    public Builder() {
      this.rating = new MovieRating();
    }

    public Builder metascore(String metascore) {
      rating.metascore = metascore;
      return this;
    }

    public Builder imdbRating(String imdbRating) {
      rating.imdbRating = imdbRating;
      return this;
    }

    public MovieRating build() {
      return rating;
    }
  }
  
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("metascore", metascore)
        .add("imdbRating", imdbRating)
        .toString();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(metascore, imdbRating);
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
    MovieRating other = (MovieRating) obj;
    return Objects.equals(metascore, other.metascore) &&
        Objects.equals(imdbRating, other.imdbRating);
  }
}
