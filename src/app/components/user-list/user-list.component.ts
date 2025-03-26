import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule, RouterModule, DatePipe],
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private readonly userService: UserService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getUsers().subscribe({
      next: (users) => this.users = users,
      error: (err) => console.error('Ошибка загрузки пользователей', err)
    });
  }

  deleteUser(id: number): void {
    if (confirm('Удалить пользователя?')) {
      this.userService.deleteUser(id).subscribe({
        next: () => this.users = this.users.filter(u => u.id !== id),
        error: (err) => console.error('Ошибка удаления пользователя', err)
      });
    }
  }
}
