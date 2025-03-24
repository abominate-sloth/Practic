import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../../services/author.service';
import { Author } from '../../models/author.model';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-author-list',
  standalone: true,
  imports: [CommonModule, RouterModule, DatePipe],
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {
  authors: Author[] = [];

  constructor(private authorService: AuthorService) {}

  ngOnInit(): void {
    this.loadAuthors();
  }

  loadAuthors(): void {
    this.authorService.getAuthors().subscribe({
      next: (authors) => this.authors = authors,
      error: (err) => console.error('Ошибка загрузки авторов', err)
    });
  }

  deleteAuthor(id: number): void {
    this.authorService.deleteAuthor(id).subscribe({
      next: () => this.authors = this.authors.filter(a => a.id !== id),
      error: (err) => console.error('Ошибка удаления автора', err)
    });
  }
}
