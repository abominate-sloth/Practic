package com.library.service;

import com.library.model.Genre;
import com.library.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Получить жанр по ID
    public Genre getGenreById(int id) {
        return genreRepository.findById(id).orElse(null);
    }

    // Сохранить жанр
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Удалить жанр по ID
    public void deleteGenre(int id) {
        genreRepository.deleteById(id);
    }
}