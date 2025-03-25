import { Component, OnInit } from '@angular/core';
import { GenreService } from '../../services/genre.service';
import { Genre } from '../../models/genre.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-genre-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './genre-form.component.html',
  styleUrls: ['./genre-form.component.css'],
})
export class GenreFormComponent implements OnInit {
  genre: Genre = {
    id: 0,
    name: '',
  };

  constructor(
    private genreService: GenreService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadGenre(+id);
    }
  }

  loadGenre(id: number): void {
    this.genreService.getGenre(id).subscribe({
      next: (genre: Genre) => {
        this.genre = genre;
      },
      error: (error) => {
        console.error('Ошибка при загрузке жанра', error);
      },
    });
  }

  saveGenre(): void {
    if (this.genre.id) {
      // Редактирование существующего жанра
      this.genreService.updateGenre(this.genre.id, this.genre).subscribe(() => {
        this.router.navigate(['/genres']);
      });
    } else {
      // Создание нового жанра
      this.genreService.createGenre(this.genre).subscribe(() => {
        this.router.navigate(['/genres']);
      });
    }
  }
}
