package com.library.dto;

import lombok.Data;
import java.util.Set;

@Data
public class BookResponseDTO {
    private int id;
    private String title;
    private GenreResponseDTO genre;
    private Integer publishYear;
    private String isbn;
    private int copiesAvailable;
    private Set<AuthorResponseDTO> authors;
    private Double averageRating; // Только средний рейтинг
}