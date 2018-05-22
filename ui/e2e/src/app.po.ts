import { browser, by, element, ElementFinder, ElementArrayFinder } from 'protractor';

export class AppPage {
  static readonly DATE_FORMAT = 'MMMM D, YYYY';

  navigateTo() {
    return browser.get('/');
  }

  getFirstMovieReviewCard() {
    return element(by.css('.movie-review.card'));
  }

  getMovieReviewCards() {
    return element.all(by.css('.movie-review.card'));
  }

  getMovieReviewImage(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.card-img-top'));
  }

  getMovieReviewTitle(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.card-title'));
  }

  getMovieReviewAuthor(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.card-text .author'));
  }

  getMovieReviewPublishDate(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.card-text .publish-date'));
  }

  getMovieReviewOpeningDate(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.card-text .opening-date'));
  }

  getMovieReviewSummary(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.card-text .review-summary'));
  }

  getMovieReviewImdbRating(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.ratings .imdb'));
  }

  getMovieReviewMetascore(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.ratings .metascore'));
  }

  getMovieReviewReadButton(reviewCard: ElementFinder) {
    return reviewCard.element(by.css('.btn.read-review'));
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }
}
