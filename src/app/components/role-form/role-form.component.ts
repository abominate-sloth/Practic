import { Component, OnInit } from '@angular/core';
import { RoleService } from '../../services/role.service';
import { Role } from '../../models/role.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-role-form',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './role-form.component.html',
  styleUrls: ['./role-form.component.css']
})
export class RoleFormComponent implements OnInit {
  role: Role = {
    id: 0,
    roleName: ''
  };

  constructor(
    private roleService: RoleService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) this.loadRole(+id);
  }

  loadRole(id: number): void {
    this.roleService.getRole(id).subscribe({
      next: (role) => this.role = role,
      error: (err) => console.error('Ошибка загрузки роли', err)
    });
  }

  saveRole(): void {
    const operation = this.role.id
      ? this.roleService.updateRole(this.role.id, this.role)
      : this.roleService.createRole(this.role);

    operation.subscribe({
      next: () => this.router.navigate(['/roles']),
      error: (err) => console.error('Ошибка сохранения роли', err)
    });
  }
}
