import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book.service';
import { Book } from '../../models/book.model'; // Импортируем интерфейсы
import { Genre } from '../../models/genre.model'; // Импортируем интерфейсы
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css'],
})
export class BookFormComponent implements OnInit {
  book: Book = {
    id: 0,
    title: '',
    genreId: null, // Идентификатор жанра
    genre: null,
    publishYear: 0,
    isbn: '',
    copiesAvailable: 0,
    authors: [], // Авторы (массив объектов Author)
  };

  genres: Genre[] = []; // Список жанров

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadGenres(); // Загружаем список жанров

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadBook(+id);
    }
  }

  loadGenres(): void {
    this.bookService.getGenres().subscribe({
      next: (genres: Genre[]) => {
        this.genres = genres;
      },
      error: (error) => {
        console.error('Ошибка при загрузке жанров', error);
      },
    });
  }

  loadBook(id: number): void {
    this.bookService.getBook(id).subscribe({
      next: (book: Book) => {
        this.book = book;
      },
      error: (error) => {
        console.error('Ошибка при загрузке книги', error);
      },
    });
  }

  saveBook(): void {
    if (this.book.id) {
      // Редактирование существующей книги
      this.bookService.updateBook(this.book.id, this.book).subscribe(() => {
        this.router.navigate(['/books']);
      });
    } else {
      // Создание новой книги
      this.bookService.createBook(this.book).subscribe(() => {
        this.router.navigate(['/books']);
      });
    }
  }
}
