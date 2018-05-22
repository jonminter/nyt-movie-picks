import { protractor, browser } from 'protractor';
import { AppPage } from './app.po';
import { E2EUtils } from './e2e-utils';
import * as moment from 'moment';

describe('nyt-movie-picks App', () => {
  let page: AppPage;
  const until = protractor.ExpectedConditions;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display movie review cards', () => {
    page.navigateTo();

    page.getMovieReviewCards().each((reviewCard, index) => {
      // Movie image
      expect(page.getMovieReviewImage(reviewCard).isPresent()).toBeTruthy();

      // Review title
      const reviewTitle = page.getMovieReviewTitle(reviewCard);
      expect(reviewTitle.isPresent()).toBeTruthy();
      E2EUtils.expectNonEmptyString(reviewTitle);

      // Review author
      const author = page.getMovieReviewAuthor(reviewCard);
      expect(author.isPresent()).toBeTruthy();
      E2EUtils.expectNonEmptyString(author);

      // Review publish date
      const publishDate = page.getMovieReviewPublishDate(reviewCard);
      expect(publishDate.isPresent()).toBeTruthy();
      E2EUtils.expectNonEmptyString(publishDate);
      E2EUtils.expectValidDate(publishDate, AppPage.DATE_FORMAT);

      // Review opening date
      const openingDate = page.getMovieReviewPublishDate(reviewCard);
      expect(openingDate.isPresent()).toBeTruthy();
      E2EUtils.expectNonEmptyString(openingDate);
      E2EUtils.expectValidDate(openingDate, AppPage.DATE_FORMAT);

      // Review summary
      const summary = page.getMovieReviewSummary(reviewCard);
      expect(summary.isPresent()).toBeTruthy();
      E2EUtils.expectNonEmptyString(summary);

      // Review IMDB rating
      const imdbRating = page.getMovieReviewImdbRating(reviewCard);
      imdbRating.isPresent().then(present => {
        if (present) {
          imdbRating.getText().then(text => {
            const ratingValue = parseFloat(text);
            expect(ratingValue).not.toBeNaN();
            expect(ratingValue).toBeGreaterThan(0);
            expect(ratingValue).toBeLessThan(10);
          });
        }
      });

      // Review Metascore rating
      const metascore = page.getMovieReviewMetascore(reviewCard);
      metascore.isPresent().then(present => {
        if (present) {
          metascore.getText().then(text => {
            const ratingValue = parseInt(text, 10);
            expect(ratingValue).not.toBeNaN();
            expect(ratingValue).toBeGreaterThan(0);
            expect(ratingValue).toBeLessThan(100);
          });
        }
      });

      const readButton = page.getMovieReviewReadButton(reviewCard);
      expect(readButton.isPresent()).toBeTruthy();
    });
  });
});
