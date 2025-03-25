package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Указывает, что это сервис
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Получить все книги
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Фильтрация книг по параметрам
    public List<Book> filterBooks(String title, Integer genreId, Integer publishYear, String isbn, Integer copiesAvailable) {
        return bookRepository.findAll((Specification<Book>) (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (title != null && !title.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            }
            if (genreId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("genre").get("id"), genreId));
            }
            if (publishYear != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("publishYear"), publishYear));
            }
            if (isbn != null && !isbn.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("isbn"), "%" + isbn + "%"));
            }
            if (copiesAvailable != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("copiesAvailable"), copiesAvailable));
            }

            return predicate;
        });
    }

    // Сохранить книгу
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Удалить книгу по ID
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    // Получить книгу по ID
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
}