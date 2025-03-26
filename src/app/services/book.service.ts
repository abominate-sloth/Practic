import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '../models/book.model';
import { Genre } from '../models/genre.model';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private readonly apiUrl = 'http://localhost:8080/api/books';
  private readonly genresUrl = 'http://localhost:8080/api/genres';

  constructor(private readonly http: HttpClient) {}

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(this.apiUrl);
  }

  getBook(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.apiUrl}/${id}`);
  }

  createBook(book: Book): Observable<Book> {
    const requestData = {
      title: book.title,
      genreId: book.genreId,
      publishYear: book.publishYear,
      isbn: book.isbn,
      copiesAvailable: book.copiesAvailable,
      authorIds: book.authors?.map(a => a.id) || []
    };
    return this.http.post<Book>(this.apiUrl, requestData);
  }

  updateBook(id: number, book: Book): Observable<Book> {
    const requestData = {
      title: book.title,
      genreId: book.genreId,
      publishYear: book.publishYear,
      isbn: book.isbn,
      copiesAvailable: book.copiesAvailable,
      authorIds: book.authors?.map(a => a.id) || []
    };
    return this.http.put<Book>(`${this.apiUrl}/${id}`, requestData);
  }

  deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getGenres(): Observable<Genre[]> {
    return this.http.get<Genre[]>(this.genresUrl);
  }
}
