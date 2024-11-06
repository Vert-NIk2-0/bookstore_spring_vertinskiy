package com.Belhard.bookstore.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private Long id;
    private String author;
    private String bookname;
    private String isbn;
    private Object numberOfPages;
    private BigDecimal price;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Object numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Object getYear() {
        return year;
    }

    public void setYear(Object year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(bookname, book.bookname) && Objects.equals(isbn, book.isbn) && Objects.equals(id, book.id) && Objects.equals(numberOfPages, book.numberOfPages) && Objects.equals(price, book.price) && Objects.equals(year, book.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, bookname, isbn, id, numberOfPages, price, year);
    }

    public String printSomeFields() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookname='" + bookname + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookname='" + bookname + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", price=" + price +
                ", year=" + year +
                '}';
    }
}
