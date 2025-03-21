package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class UserService {

    @Autowired // Внедряет репозиторий
    private UserRepository userRepository;

    // Получить всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Получить пользователя по ID
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // Получить пользователя по имени пользователя
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Получить пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Сохранить пользователя
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Удалить пользователя по ID
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}