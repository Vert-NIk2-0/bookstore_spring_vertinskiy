package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.dao.entity.Book;
import com.belhard.bookstore.service.entity.BookDto;
import com.belhard.bookstore.service.BookService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BookDtoImpl implements BookService {

    private final BookDao bookDao;

    public BookDtoImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> getAll() {
        List<BookDto> bookList = new ArrayList<>();

        for (Book book : bookDao.getAll()) {

            BookDto bookDto = new BookDto();
            setBookDto(bookDto, book);

            bookList.add(bookDto);
        }
        return bookList;
    }

    @Override
    public BookDto getById(Long id) {
        BookDto bookDto = new BookDto();
        Book book = bookDao.getById(id);

        setBookDto(bookDto, book);

        return bookDto;
    }

    @Override
    public void create(BookDto dto) {
        Book book = new Book();

        setBook(dto, book);

        bookDao.create(book);
    }

    @Override
    public BookDto update(BookDto dto) {

        Book book = bookDao.getById(dto.getId());

        setBook(dto, book);
        System.out.println(book);
        bookDao.update(book);
        return dto;
    }

    @Override
    public boolean delete(Long id) {
        bookDao.deleteById(id);

        return bookDao.deleteById(id);
    }

    @Override
    public BigDecimal getCostAuthorsBooks(String author) {
        BigDecimal result = new BigDecimal(0);
        for (Book book : bookDao.getByAuthor(author)) {
            result = result.add(book.getPrice());
        }
        return result;
    }

    private static void setBookDto(BookDto bookDto, Book book) {
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setBookname(book.getBookname());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setYear(book.getYear());
    }

    private static void setBook(BookDto dto, Book book) {
        book.setId(dto.getId());
        book.setAuthor(dto.getAuthor());
        book.setBookname(dto.getBookname());
        book.setIsbn(dto.getIsbn());
        book.setYear(dto.getYear());
    }
}
