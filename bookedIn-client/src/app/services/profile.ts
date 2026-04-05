import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
providedIn: 'root'
})
export class ProfileService {

private baseUrl = 'http://localhost:9090/api/profile';

constructor(private http: HttpClient) {}

  saveProfile(data: any) {
    return this.http.post(`${this.baseUrl}/onboarding`, data);
  }

  getProfile() {
    return this.http.get<any>(`${this.baseUrl}/me`);
  }
}
