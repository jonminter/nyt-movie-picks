import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { MovieItem } from '../models/movie-item';

@Injectable({
  providedIn: 'root'
})
export class MovieReviewsServiceService {

  constructor(private httpClient: HttpClient) { }

  getCriticReviewsAndRatings(): Observable<Array<MovieItem>> {
    console.log('API call URL = ', environment.apiBaseUrl + 'critic-reviews-with-ratings');
    return this.httpClient.get<Array<MovieItem>>(environment.apiBaseUrl + 'critic-reviews-with-ratings');
  }
}
