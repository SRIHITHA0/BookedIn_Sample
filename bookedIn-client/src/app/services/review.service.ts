import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private apiUrl = 'http://localhost:9090/api/reviews';

  constructor(private http: HttpClient) {}

  // Fetches the list of reviews (Emma R., Michael T., etc.)
  getReviewsByBook(bookId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/book/${bookId}`);
  }

  // Fetches the 4.9 average rating for the header
  getAverageRating(bookId: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/book/${bookId}/average`);
  }
}