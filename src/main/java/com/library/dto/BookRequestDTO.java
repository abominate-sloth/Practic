package com.library.dto;

import lombok.Data;
import java.util.Set;

@Data
public class BookRequestDTO {
    private String title;
    private Integer genreId;
    private Integer publishYear;
    private String isbn;
    private int copiesAvailable;
    private Set<Integer> authorIds; // Сет ID авторов
}