// src/app/services/gamification.service.ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Badge } from '../components/badge/badge.component';

export interface UserProgress {
  position: number;
  name: string;
  points: number;
  badges: number;
}
export interface User {
  id: number;
  name: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class GamificationService {
  
  private apiUrl = '/api/gamification/leaderboard';

  constructor(private http: HttpClient) {}
  

  getLeaderboard(): Observable<UserProgress[]> {
    return this.http.get<UserProgress[]>(this.apiUrl);
  }
  getBadges(): Observable<Badge[]> {
    return this.http.get<Badge[]>('/api/gamification/badges');
  }
}
