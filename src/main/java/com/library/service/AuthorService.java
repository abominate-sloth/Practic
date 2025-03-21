package com.library.service;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class AuthorService {

    @Autowired // Внедряет репозиторий
    private AuthorRepository authorRepository;

    // Получить всех авторов
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Получить автора по ID
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Получить авторов по имени
    public List<Author> getAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    // Сохранить автора
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Удалить автора по ID
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}