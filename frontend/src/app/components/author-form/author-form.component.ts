import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../../services/author.service';
import { Author } from '../../models/author.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-author-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './author-form.component.html',
  styleUrls: ['./author-form.component.css']
})
export class AuthorFormComponent implements OnInit {
  author: Author = {
    id: 0,
    name: '',
    birthDate: undefined
  };

  constructor(
    private authorService: AuthorService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) this.loadAuthor(+id);
  }

  loadAuthor(id: number): void {
    this.authorService.getAuthor(id).subscribe({
      next: (author) => this.author = author,
      error: (err) => console.error('Ошибка загрузки автора', err)
    });
  }

  saveAuthor(): void {
    const operation = this.author.id
      ? this.authorService.updateAuthor(this.author.id, this.author)
      : this.authorService.createAuthor(this.author);

    operation.subscribe({
      next: () => this.router.navigate(['/authors']),
      error: (err) => console.error('Ошибка сохранения автора', err)
    });
  }
}
