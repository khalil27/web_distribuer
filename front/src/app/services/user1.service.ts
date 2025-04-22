import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id: number;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService1 {
  private apiUrl = 'http://localhost:8081/api/gamification/users'; 
  private apiUrl1 = 'http://localhost:8081/api/gamification/history'; 
  private baseUrl = 'http://localhost:8081/api/gamification/assign-badge';


  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  assignBadge(userId: number, title: string, description: string): Observable<any> {
    const params = new HttpParams()
      .set('userId', userId.toString())
      .set('title', title)
      .set('description', description);

    return this.http.post<any>(this.baseUrl, null, { params });
  }

  getAllActivities(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl1);
  }
}