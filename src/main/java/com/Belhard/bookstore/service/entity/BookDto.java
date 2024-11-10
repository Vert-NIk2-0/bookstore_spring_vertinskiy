package com.belhard.bookstore.service.entity;

import java.util.Objects;

public class BookDto {
    private Long id;
    private String author;
    private String bookname;
    private String isbn;
    private Object year;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Object getYear() {
        return year;
    }

    public void setYear(Object year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(author, bookDto.author) && Objects.equals(bookname, bookDto.bookname) && Objects.equals(year, bookDto.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, bookname, year);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookname='" + bookname + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
