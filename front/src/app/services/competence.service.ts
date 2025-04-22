import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Competence } from '../models/competence.model';
@Injectable({
  providedIn: 'root'
})
export class CompetenceService {

  // API Gateway URL
  private apiUrl = 'http://localhost:8084/competences';

  constructor(private http: HttpClient) { }

  getAllCompetences(): Observable<Competence[]> {
    return this.http.get<Competence[]>(this.apiUrl);
  }

  getCompetenceById(id: number): Observable<Competence> {
    return this.http.get<Competence>(`${this.apiUrl}/${id}`);
  }

  createCompetence(competence: Competence): Observable<Competence> {
    return this.http.post<Competence>(this.apiUrl, competence);
  }

  updateCompetence(id: number, competence: Competence): Observable<Competence> {
    return this.http.put<Competence>(`${this.apiUrl}/${id}`, competence);
  }

  deleteCompetence(id: number): Observable<string> {
    return this.http.delete<string>(`${this.apiUrl}/${id}`);
  }

  getCompetencesByCategorie(categorie: string): Observable<Competence[]> {
    return this.http.get<Competence[]>(`${this.apiUrl}/categorie/${categorie}`);
  }

  getCompetencesByNiveau(niveau: number): Observable<Competence[]> {
    return this.http.get<Competence[]>(`${this.apiUrl}/niveau/${niveau}`);
  }
}
