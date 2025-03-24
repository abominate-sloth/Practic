import { Component, OnInit } from '@angular/core';
import { RoleService } from '../../services/role.service';
import { Role } from '../../models/role.model';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-role-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './role-list.component.html',
  styleUrls: ['./role-list.component.css']
})
export class RoleListComponent implements OnInit {
  roles: Role[] = [];

  constructor(private roleService: RoleService) {}

  ngOnInit(): void {
    this.loadRoles();
  }

  loadRoles(): void {
    this.roleService.getRoles().subscribe({
      next: (roles) => this.roles = roles,
      error: (err) => console.error('Ошибка загрузки ролей', err)
    });
  }

  deleteRole(id: number): void {
    this.roleService.deleteRole(id).subscribe({
      next: () => this.roles = this.roles.filter(r => r.id !== id),
      error: (err) => console.error('Ошибка удаления роли', err)
    });
  }
}
