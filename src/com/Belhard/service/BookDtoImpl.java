package com.Belhard.service;

import com.Belhard.dao.BookDao;
import com.Belhard.model.Book;
import com.Belhard.model.BookDto;

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

        for (Book book : bookDao.getAllBooks()) {

            BookDto bookDto = new BookDto();
            setBookDto(bookDto, book);

            bookList.add(bookDto);
        }
        return bookList;
    }

    @Override
    public BookDto getById(Long id) {
        BookDto bookDto = new BookDto();
        Book book = bookDao.getBookById(id);

        setBookDto(bookDto, book);

        return bookDto;
    }

    @Override
    public void create(BookDto dto) {
        Book book = new Book();

        setBook(dto, book);

        bookDao.createBook(book);
    }

    @Override
    public BookDto update(BookDto dto) {

        Book book = bookDao.getBookById(dto.getId());

        setBook(dto, book);
        System.out.println(book);
        bookDao.updateBook(book);
        return dto;
    }

    @Override
    public boolean delete(Long id) {
        bookDao.deleteBookById(id);

        return bookDao.deleteBookById(id);
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
