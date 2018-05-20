import { Component, OnInit } from '@angular/core';
import { MovieReviewsServiceService } from './services/movie-reviews-service.service';
import { MovieItem } from './models/movie-item';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  apiDocsUrl: string = environment.apiBaseUrl + 'api-docs';
  movies: Array<MovieItem>;

  constructor(private reviewService: MovieReviewsServiceService) {
  }

  ngOnInit() {
    console.log('init app comp');
    this.reviewService.getCriticReviewsAndRatings().subscribe(res => this.movies = res);
  }
}
