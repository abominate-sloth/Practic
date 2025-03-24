import { Routes } from '@angular/router';
import { TableListComponent } from './components/table-list/table-list.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { GenreListComponent } from './components/genre-list/genre-list.component';
import { GenreFormComponent } from './components/genre-form/genre-form.component';

export const routes: Routes = [
  { path: '', component: TableListComponent }, // Главная страница со списком таблиц
  { path: 'books', component: BookListComponent }, // Список книг
  { path: 'books/new', component: BookFormComponent }, // Форма создания новой книги
  { path: 'books/:id/edit', component: BookFormComponent }, // Форма редактирования книги
  { path: 'genres', component: GenreListComponent }, // Список жанров
  { path: 'genres/new', component: GenreFormComponent }, // Форма создания нового жанра
  { path: 'genres/:id/edit', component: GenreFormComponent }, // Форма редактирования жанра
];
