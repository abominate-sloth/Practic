package com.library.service;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service // Указывает, что это сервис
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Получить всех авторов
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Фильтрация авторов по параметрам
    public List<Author> filterAuthors(String name, Date birthDate) {
        return authorRepository.findAll((Specification<Author>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (birthDate != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("birthDate"), birthDate));
            }

            return predicate;
        });
    }

    // Сохранить автора
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Удалить автора по ID
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }

    // Получить автора по ID
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }
}