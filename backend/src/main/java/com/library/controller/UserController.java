package com.library.controller;

import com.library.dto.UserRequestDTO;
import com.library.dto.UserResponseDTO;
import com.library.dto.RoleResponseDTO;
import com.library.model.User;
import com.library.service.UserService;
import com.library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService; // Добавляем сервис для работы с ролями

    // Фильтрация пользователей
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> filterUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Date joinDate) {

        List<User> users = userService.filterUsers(username, email, joinDate);
        List<UserResponseDTO> responseDTOs = users.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    // Получить пользователя по ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(convertToResponseDTO(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Создать пользователя
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDTO) {
        User user = convertToEntity(userDTO);
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(convertToResponseDTO(createdUser), HttpStatus.CREATED);
    }

    // Обновить пользователя
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable int id, @RequestBody UserRequestDTO userDTO) {
        User existingUser = userService.getUserById(id);
        if (existingUser != null) {
            User user = convertToEntity(userDTO);
            user.setId(id);
            User updatedUser = userService.saveUser(user);
            return new ResponseEntity<>(convertToResponseDTO(updatedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Удалить пользователя
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // --- Методы преобразования ---
    private User convertToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(dto.getPasswordHash());
        user.setEmail(dto.getEmail());
        user.setJoinDate(dto.getJoinDate());

        // Устанавливаем роль через roleService
        user.setRole(roleService.getRoleById(dto.getRoleId()));
        return user;
    }

    private UserResponseDTO convertToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setJoinDate(user.getJoinDate());

        // Преобразуем Role в RoleResponseDTO
        RoleResponseDTO roleDTO = new RoleResponseDTO();
        roleDTO.setId(user.getRole().getId());
        roleDTO.setRoleName(user.getRole().getRoleName());
        dto.setRole(roleDTO);

        return dto;
    }
}