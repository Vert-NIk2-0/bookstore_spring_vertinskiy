package com.Belhard.dao;

import com.Belhard.model.Book;

import java.util.List;

public interface BookDao {

    void createBook(Book book);

    Book updateBook(Book book, String oldIsbn);

    List<Book> getAllBooks();

    Book getBookByIsbn(String isbn);

    boolean deleteBookById(String id);

    Book getBookById(Long id);

    List<Book> getByAuthor(String author);

    boolean deleteBookByIsbn(String isbn);

    Long countAll();

}
