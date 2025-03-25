import { Role } from '../models/role.model';

export interface User {
  id: number;
  username: string;
  passwordHash?: string;
  email?: string;
  joinDate?: Date;
  role?: Role;
  roleId?: number;
}
