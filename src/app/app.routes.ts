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
  // Статические маршруты (SSR)
  {
    path: '',
    component: TableListComponent,
    data: { ssr: true }
  },
  {
    path: 'books',
    component: BookListComponent,
    data: { ssr: true }
  },
  {
    path: 'books/new',
    component: BookFormComponent,
    data: { ssr: true }
  },
  {
    path: 'genres',
    component: GenreListComponent,
    data: { ssr: true }
  },
  {
    path: 'genres/new',
    component: GenreFormComponent,
    data: { ssr: true }
  },
  {
    path: 'authors',
    component: AuthorListComponent,
    data: { ssr: true }
  },
  {
    path: 'authors/new',
    component: AuthorFormComponent,
    data: { ssr: true }
  },
  {
    path: 'roles',
    component: RoleListComponent,
    data: { ssr: true }
  },
  {
    path: 'roles/new',
    component: RoleFormComponent,
    data: { ssr: true }
  },
  {
    path: 'users',
    component: UserListComponent,
    data: { ssr: true }
  },
  {
    path: 'users/new',
    component: UserFormComponent,
    data: { ssr: true }
  },
  {
    path: 'reviews',
    component: ReviewListComponent,
    data: { ssr: true }
  },
  {
    path: 'reviews/new',
    component: ReviewFormComponent,
    data: { ssr: true }
  },
  {
    path: 'issues',
    component: IssueListComponent,
    data: { ssr: true }
  },
  {
    path: 'issues/new',
    component: IssueFormComponent,
    data: { ssr: true }
  },

  // Динамические маршруты (CSR)
  {
    path: 'books/:id/edit',
    component: BookFormComponent,
    data: { ssr: false }
  },
  {
    path: 'genres/:id/edit',
    component: GenreFormComponent,
    data: { ssr: false }
  },
  {
    path: 'authors/:id/edit',
    component: AuthorFormComponent,
    data: { ssr: false }
  },
  {
    path: 'roles/:id/edit',
    component: RoleFormComponent,
    data: { ssr: false }
  },
  {
    path: 'users/:id/edit',
    component: UserFormComponent,
    data: { ssr: false }
  },
  {
    path: 'reviews/:id/edit',
    component: ReviewFormComponent,
    data: { ssr: false }
  },
  {
    path: 'issues/:id/edit',
    component: IssueFormComponent,
    data: { ssr: false }
  }
];
