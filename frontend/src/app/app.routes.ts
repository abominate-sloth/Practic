import { Routes } from '@angular/router';
import { TableListComponent } from './components/table-list/table-list.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookFormComponent } from './components/book-form/book-form.component';
import { GenreListComponent } from './components/genre-list/genre-list.component';
import { GenreFormComponent } from './components/genre-form/genre-form.component';
import { AuthorListComponent } from './components/author-list/author-list.component';
import { AuthorFormComponent } from './components/author-form/author-form.component';
import { RoleListComponent } from './components/role-list/role-list.component';
import { RoleFormComponent } from './components/role-form/role-form.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ReviewListComponent } from './components/review-list/review-list.component';
import { ReviewFormComponent } from './components/review-form/review-form.component';
import { IssueListComponent } from './components/issue-list/issue-list.component';
import { IssueFormComponent } from './components/issue-form/issue-form.component';

export const routes: Routes = [
  { path: '', component: TableListComponent }, // Главная страница со списком таблиц
  { path: 'books', component: BookListComponent }, // Список книг
  { path: 'books/new', component: BookFormComponent }, // Форма создания новой книги
  { path: 'books/:id/edit', component: BookFormComponent }, // Форма редактирования книги
  { path: 'genres', component: GenreListComponent }, // Список жанров
  { path: 'genres/new', component: GenreFormComponent }, // Форма создания нового жанра
  { path: 'genres/:id/edit', component: GenreFormComponent }, // Форма редактирования жанра
  { path: 'authors', component: AuthorListComponent },
  { path: 'authors/new', component: AuthorFormComponent },
  { path: 'authors/:id/edit', component: AuthorFormComponent },
  { path: 'roles', component: RoleListComponent },
  { path: 'roles/new', component: RoleFormComponent },
  { path: 'roles/:id/edit', component: RoleFormComponent },
  { path: 'users', component: UserListComponent },
  { path: 'users/new', component: UserFormComponent },
  { path: 'users/:id/edit', component: UserFormComponent },
  { path: 'reviews', component: ReviewListComponent },
  { path: 'reviews/new', component: ReviewFormComponent },
  { path: 'reviews/:id/edit', component: ReviewFormComponent },
  { path: 'issues', component: IssueListComponent },
  { path: 'issues/new', component: IssueFormComponent },
  { path: 'issues/:id/edit', component: IssueFormComponent }
];
