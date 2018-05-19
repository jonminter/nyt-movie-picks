package com.jonminter.nytmoviepicks;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MovieItem {
  public MovieReview review;
  public MovieRating rating;
  
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private MovieItem item;

    public Builder() {
      this.item = new MovieItem();
    }

    public Builder review(MovieReview review) {
      item.review = review;
      return this;
    }

    public Builder rating(MovieRating rating) {
      item.rating = rating;
      return this;
    }

    public MovieItem build() {
      return item;
    }
  }
  
  @Override
  public String toString() {
    return String.format("%s {review=%s, rating=%s}",
        super.toString(), review, rating);
  }
  
  @Override
  public int hashCode() {
    return new HashCodeBuilder(31, 41).
      append(review).
      append(rating).
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
    MovieItem other = (MovieItem) obj;
    return new EqualsBuilder()
        .append(review, other.review)
        .append(rating, other.rating)
        .isEquals();
  }
}
