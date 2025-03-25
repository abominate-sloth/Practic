import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user.model';
import { Role } from '../../models/role.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  user: User = {
    id: 0,
    username: '',
    email: '',
    roleId: undefined
  };
  roles: Role[] = [];

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadRoles();
    const id = this.route.snapshot.paramMap.get('id');
    if (id) this.loadUser(+id);
  }

  loadUser(id: number): void {
    this.userService.getUser(id).subscribe({
      next: (user) => {
        this.user = user;
        this.user.roleId = user.role?.id;
      },
      error: (err) => console.error('Ошибка загрузки пользователя', err)
    });
  }

  loadRoles(): void {
    this.userService.getRoles().subscribe({
      next: (roles) => this.roles = roles,
      error: (err) => console.error('Ошибка загрузки ролей', err)
    });
  }

  saveUser(): void {
    // Создаем копию объекта пользователя
    const userToSave = { ...this.user };

    // Для нового пользователя (без ID) просто не включаем поле id
    if (!userToSave.id) {
      const { id, ...userWithoutId } = userToSave;
      this.userService.createUser(userWithoutId as User).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => console.error('Ошибка создания пользователя', err)
      });
    } else {
      // Для существующего пользователя отправляем все данные
      this.userService.updateUser(userToSave.id, userToSave).subscribe({
        next: () => this.router.navigate(['/users']),
        error: (err) => console.error('Ошибка обновления пользователя', err)
      });
    }
  }
}
