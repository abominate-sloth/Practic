package com.library.service;

import com.library.model.Genre;
import com.library.repository.GenreRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class GenreService {

    @Autowired // Внедряет репозиторий
    private GenreRepository genreRepository;

    // Получить все жанры
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Фильтрация жанров по имени
    public List<Genre> filterGenres(String name) {
        return genreRepository.findAll((Specification<Genre>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }

            return predicate;
        });
    }

    // Сохранить жанр
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Удалить жанр по ID
    public void deleteGenre(int id) {
        genreRepository.deleteById(id);
    }

    // Получить жанр по ID
    public Genre getGenreById(int id) {
        return genreRepository.findById(id).orElse(null);
    }
}