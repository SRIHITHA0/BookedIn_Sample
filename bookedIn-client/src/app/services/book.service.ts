import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:9090/api/books';

  constructor(private http: HttpClient) {}

  // Fetches the title, author, and synopsis for the Deep Dive
  getBookById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
}