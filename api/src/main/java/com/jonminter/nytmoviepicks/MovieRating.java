package com.jonminter.nytmoviepicks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieRating {
  @JsonProperty("Metascore")
  public String metascore;
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
    return String.format("%s {metascore=%s, imdbRating=%s}",
        super.toString(), metascore, imdbRating);
  }
  
  @Override
  public int hashCode() {
    return new HashCodeBuilder(11, 47).
      append(metascore).
      append(imdbRating).
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
    MovieRating other = (MovieRating) obj;
    return new EqualsBuilder()
        .append(metascore, other.metascore)
        .append(imdbRating, other.imdbRating)
        .isEquals();
  }
}
