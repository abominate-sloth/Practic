import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-table-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css'],
})
export class TableListComponent {
  // Список таблиц (пока только книги)
  tables = [
    { name: 'Книги', path: '/books' },
  ];
}
