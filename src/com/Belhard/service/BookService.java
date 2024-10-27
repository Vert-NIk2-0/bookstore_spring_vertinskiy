package com.Belhard.service;

import com.Belhard.model.BookDto;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    BookDto getById(Long id);

    void create(BookDto dto);

    BookDto update(BookDto dto);

    boolean delete(Long id);

    BigDecimal getCostAuthorsBooks(String author);

}
