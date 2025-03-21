package com.library.service;

import com.library.model.Role;
import com.library.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class RoleService {

    @Autowired // Внедряет репозиторий
    private RoleRepository roleRepository;

    // Получить все роли
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Получить роль по ID
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }

    // Получить роль по имени
    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    // Сохранить роль
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Удалить роль по ID
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }
}
