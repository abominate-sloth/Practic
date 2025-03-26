package com.library.controller;

import com.library.dto.RoleRequestDTO;
import com.library.dto.RoleResponseDTO;
import com.library.model.Role;
import com.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Фильтрация ролей (возвращает DTO)
    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> filterRoles(@RequestParam(required = false) String roleName) {
        List<Role> roles = roleService.filterRoles(roleName);
        List<RoleResponseDTO> responseDTOs = roles.stream()
                .map(this::convertToResponseDTO)
                .toList(); // Изменено с collect(Collectors.toList())
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    // Получить роль по ID (возвращает DTO)
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            return new ResponseEntity<>(convertToResponseDTO(role), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Создать роль (принимает DTO)
    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleRequestDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        Role createdRole = roleService.saveRole(role);
        return new ResponseEntity<>(convertToResponseDTO(createdRole), HttpStatus.CREATED);
    }

    // Обновить роль (принимает DTO)
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRole(@PathVariable int id, @RequestBody RoleRequestDTO roleDTO) {
        Role existingRole = roleService.getRoleById(id);
        if (existingRole != null) {
            Role role = convertToEntity(roleDTO);
            role.setId(id); // Устанавливаем ID для обновления
            Role updatedRole = roleService.saveRole(role);
            return new ResponseEntity<>(convertToResponseDTO(updatedRole), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удаление роли (остается без изменений)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable int id) {
        Role role = roleService.getRoleById(id);
        if (role != null) {
            roleService.deleteRole(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // --- Методы преобразования ---
    private Role convertToEntity(RoleRequestDTO dto) {
        Role role = new Role();
        role.setRoleName(dto.getRoleName());
        return role;
    }

    private RoleResponseDTO convertToResponseDTO(Role role) {
        return new RoleResponseDTO(role.getId(), role.getRoleName());
    }
}