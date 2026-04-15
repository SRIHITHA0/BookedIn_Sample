import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
providedIn: 'root'
})
export class ProfileService {
private baseUrl = 'http://localhost:9090/api/profile';

constructor(private http: HttpClient) {}

  // Save profile (temporary → LocalStorage)
  saveProfile(data: any): Observable<any> {
    localStorage.setItem('profile', JSON.stringify(data));
    return of(data);
    // Later replace with: return this.http.post(`${this.baseUrl}/onboarding`, data);
  }

  // Get profile (temporary → LocalStorage)
  getProfile(): Observable<any> {
    const stored = localStorage.getItem('profile');
    if (stored) {
      return of(JSON.parse(stored));
    }
    return of(null);
    // Later replace with: return this.http.get<any>(`${this.baseUrl}/me`);
  }
}
