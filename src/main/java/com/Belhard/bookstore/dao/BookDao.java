package com.belhard.bookstore.dao;

import com.belhard.bookstore.dao.entity.Book;

import java.util.List;

public interface BookDao {

    void create(Book book);

    Book update(Book book);

    List<Book> getAll();

    Book getByIsbn(String isbn);

    boolean deleteById(Long id);

    Book getById(Long id);

    List<Book> getByAuthor(String author);

    boolean deleteByIsbn(String isbn);

    long countAll();

}
