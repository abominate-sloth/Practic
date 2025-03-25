import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre.service';
import { Genre } from '../../models/genre.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-genre-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './genre-list.component.html',
  styleUrls: ['./genre-list.component.css'],
})
export class GenreListComponent implements OnInit {
  genres: Genre[] = [];

  constructor(private genreService: GenreService) {}

  ngOnInit(): void {
    this.loadGenres();
  }

  loadGenres(): void {
    this.genreService.getGenres().subscribe({
      next: (genres: Genre[]) => {
        this.genres = genres;
      },
      error: (error) => {
        console.error('Ошибка при загрузке жанров', error);
      },
    });
  }

  deleteGenre(id: number): void {
    this.genreService.deleteGenre(id).subscribe({
      next: () => {
        this.genres = this.genres.filter((genre) => genre.id !== id);
      },
      error: (error) => {
        console.error('Ошибка при удалении жанра', error);
      },
    });
  }
}
