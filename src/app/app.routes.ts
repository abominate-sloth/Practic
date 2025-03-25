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
  // Статические маршруты (SSR + prerender)
  {
    path: '',
    component: TableListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'books',
    component: BookListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'books/new',
    component: BookFormComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'genres',
    component: GenreListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'genres/new',
    component: GenreFormComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'authors',
    component: AuthorListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'authors/new',
    component: AuthorFormComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'roles',
    component: RoleListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'roles/new',
    component: RoleFormComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'users',
    component: UserListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'users/new',
    component: UserFormComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'reviews',
    component: ReviewListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'reviews/new',
    component: ReviewFormComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'issues',
    component: IssueListComponent,
    data: { renderMode: 'server' }
  },
  {
    path: 'issues/new',
    component: IssueFormComponent,
    data: { renderMode: 'server' }
  },

  // Динамические маршруты (CSR)
  {
    path: 'books/:id/edit',
    component: BookFormComponent,
    data: { renderMode: 'client-only' }
  },
  {
    path: 'genres/:id/edit',
    component: GenreFormComponent,
    data: { renderMode: 'client-only' }
  },
  {
    path: 'authors/:id/edit',
    component: AuthorFormComponent,
    data: { renderMode: 'client-only' }
  },
  {
    path: 'roles/:id/edit',
    component: RoleFormComponent,
    data: { renderMode: 'client-only' }
  },
  {
    path: 'users/:id/edit',
    component: UserFormComponent,
    data: { renderMode: 'client-only' }
  },
  {
    path: 'reviews/:id/edit',
    component: ReviewFormComponent,
    data: { renderMode: 'client-only' }
  },
  {
    path: 'issues/:id/edit',
    component: IssueFormComponent,
    data: { renderMode: 'client-only' }
  }
];
