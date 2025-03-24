package com.library.service;

import com.library.model.User;
import com.library.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service // Указывает, что это сервис
public class UserService {

    @Autowired // Внедряет репозиторий
    private UserRepository userRepository;

    // Получить всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Фильтрация пользователей по параметрам
    public List<User> filterUsers(String username, String email, Date joinDate) {
        return userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (username != null && !username.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("username"), "%" + username + "%"));
            }
            if (email != null && !email.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("email"), "%" + email + "%"));
            }
            if (joinDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("joinDate"), joinDate));
            }

            return predicate;
        });
    }

    // Получить пользователя по ID
    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
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