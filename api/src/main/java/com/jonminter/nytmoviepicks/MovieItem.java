package com.jonminter.nytmoviepicks;

import java.util.Objects;
import com.google.common.base.MoreObjects;

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
    return MoreObjects.toStringHelper(this)
        .add("review", review)
        .add("rating", rating)
        .toString();
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(review, rating);
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
    return Objects.equals(review, other.review) &&
        Objects.equals(rating, other.rating);
  }
}
