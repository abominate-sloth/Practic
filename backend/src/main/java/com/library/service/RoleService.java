package com.library.service;

import com.library.model.Role;
import com.library.repository.RoleRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class RoleService {

    @Autowired // Внедряет репозиторий
    private RoleRepository roleRepository;

    // Получить всех ролей
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Фильтрация ролей по параметрам
    public List<Role> filterRoles(String roleName) {
        return roleRepository.findAll((Specification<Role>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (roleName != null && !roleName.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("roleName"), "%" + roleName + "%"));
            }

            return predicate;
        });
    }

    // Сохранить роль
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Удалить роль по ID
    public void deleteRole(int id) {
        roleRepository.deleteById(id);
    }

    // Получить роль по ID
    public Role getRoleById(int id) {
        return roleRepository.findById(id).orElse(null);
    }
}