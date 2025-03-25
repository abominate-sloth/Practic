import { Component, OnInit } from '@angular/core';
import { BookService } from '../../services/book.service';
import { AuthorService } from '../../services/author.service';
import { Book } from '../../models/book.model';
import { Genre } from '../../models/genre.model';
import { Author } from '../../models/author.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-book-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  book: Book = {
    id: 0,
    title: '',
    genreId: null,
    publishYear: 0,
    isbn: '',
    copiesAvailable: 0,
    authors: []
  };

  genres: Genre[] = [];
  allAuthors: Author[] = [];
  selectedAuthorIds: number[] = [];

  constructor(
    private bookService: BookService,
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadGenres();
    this.loadAuthors();

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadBook(+id);
    }
  }

  loadGenres(): void {
    this.bookService.getGenres().subscribe(genres => {
      this.genres = genres;
    });
  }

  loadAuthors(): void {
    this.authorService.getAuthors().subscribe(authors => {
      this.allAuthors = authors;
    });
  }

  loadBook(id: number): void {
    this.bookService.getBook(id).subscribe(book => {
      this.book = book;
      this.selectedAuthorIds = book.authors.map(a => a.id);
    });
  }

  isAuthorSelected(authorId: number): boolean {
    return this.selectedAuthorIds.includes(authorId);
  }

  toggleAuthor(authorId: number): void {
    if (this.isAuthorSelected(authorId)) {
      this.selectedAuthorIds = this.selectedAuthorIds.filter(id => id !== authorId);
    } else {
      this.selectedAuthorIds = [...this.selectedAuthorIds, authorId];
    }
  }

  saveBook(): void {
    this.book.authors = this.allAuthors.filter(a => this.selectedAuthorIds.includes(a.id));

    if (this.book.id) {
      this.bookService.updateBook(this.book.id, this.book).subscribe(() => {
        this.router.navigate(['/books']);
      });
    } else {
      this.bookService.createBook(this.book).subscribe(() => {
        this.router.navigate(['/books']);
      });
    }
  }
}
