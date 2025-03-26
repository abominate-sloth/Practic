import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Issue, IssueCreateDTO } from '../models/issue.model';
import { Book } from '../models/book.model';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class IssueService {
  private readonly apiUrl = 'http://localhost:8080/api/issues';

  constructor(private http: HttpClient) {}

  getIssues(): Observable<Issue[]> {
    return this.http.get<Issue[]>(this.apiUrl);
  }

  getIssue(id: number): Observable<Issue> {
    return this.http.get<Issue>(`${this.apiUrl}/${id}`);
  }

  createIssue(issue: IssueCreateDTO): Observable<Issue> {
    return this.http.post<Issue>(this.apiUrl, issue);
  }

  updateIssue(id: number, issue: IssueCreateDTO): Observable<Issue> {
    return this.http.put<Issue>(`${this.apiUrl}/${id}`, issue);
  }

  deleteIssue(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>('http://localhost:8080/api/books');
  }

  // Получаем всех пользователей одним запросом
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8080/api/users');
  }
}
