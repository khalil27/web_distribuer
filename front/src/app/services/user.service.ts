import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable , of } from 'rxjs';
import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8083/Utilisateur/api/utilisateurs'; // Replace with your API endpoint
  private authUrl = 'http://localhost:8083/Utilisateur/api/auth';

  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }
  
  /*login(login: string, password: string): Observable<{ id: number, type: number } | null> {
    return this.http.post<{ id: number, type: number }>(this.authUrl, { username: login, password }).pipe(
      map(response => response || null)
    );
  }*/
    login(login: string, password: string): Observable<User | null> {
      return this.http.post<User>(`${this.authUrl}/login`, { username: login, password })
        .pipe(
          map(response => response || null),
          catchError(error => {
            console.error('Erreur lors de la connexion:', error);
            return of(null);
          })
        );
    }
    
    logout(userId: number): Observable<void> {
      return this.http.put<void>(`${this.authUrl}/deconnexion/${userId}`, {}).pipe(
        catchError(error => {
          console.error('Erreur lors de la déconnexion:', error);
          return of<void>(undefined);
        })
      );
    }

    deleteUser(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`, {}).pipe(
        catchError(error => {
          console.error('Erreur lors de la suppression de l\'utilisateur:', error);
          throw error;
        })
      );
    }

    updateUser(user: User): Observable<User> {
      return this.http.put<User>(`${this.apiUrl}/${user.id}`, user).pipe(
        catchError(error => {
          console.error('Erreur lors de la mise à jour de l\'utilisateur:', error);
          throw error;
        })
      );
    }

    
    
}
export interface User {
  id: number | null;
  nom: string;
  prenom: string;
  password: string;
  login: string;
  type: number;
  status: boolean;
  active: boolean;
}
