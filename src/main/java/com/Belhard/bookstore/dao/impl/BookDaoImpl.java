package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.dao.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private static final String SELECT_BOOK_BY_ISBN =
            "SELECT * FROM books WHERE isbn = ?";

    private static final String SELECT_BOOK_BY_AUTHOR =
            "SELECT * FROM books WHERE author = ?";

    private static final String SIZE_DATABASE =
            "SELECT COUNT(*) AS row_count FROM books";

    private static final String DELETE_BOOK_BY_ISBN =
            "DELETE FROM books WHERE isbn = ?";

    private static final String DELETE_BOOK_BY_ID =
            "DELETE FROM books WHERE id = ?";

    private static final String CREATE_BOOK =
            "INSERT INTO books (author, bookname, isbn, number_of_pages, price, year) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_BOOK_BY_ID =
            "UPDATE books " +
                    "SET author = ?, bookname = ?, isbn = ?, number_of_pages = ?, price = ?, year = ? WHERE id = ?";

    private static final String SELECT_ALL_BOOKS =
            "SELECT id, author, bookname, isbn, number_of_pages, price, year FROM books";

    private static final String SELECT_BOOK_BY_ID =
            "SELECT * FROM books WHERE id = ?";

    private final ConnectionManagerImpl connectionManagerImpl;

    public BookDaoImpl(ConnectionManagerImpl connectionManagerImpl) {
        this.connectionManagerImpl = connectionManagerImpl;
    }
    @Override
    public void create(Book book) {

        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS)
        ){
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getBookname());
            statement.setString(3, book.getIsbn());
            statement.setObject(4, book.getNumberOfPages());
            statement.setBigDecimal(5, book.getPrice());
            statement.setObject(6, book.getYear());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            book.setId(generatedKeys.getLong("id"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book update(Book book) {
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_BY_ID)
        ) {
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getBookname());
            statement.setString(3, book.getIsbn());
            statement.setObject(4, book.getNumberOfPages());
            statement.setBigDecimal(5, book.getPrice());
            statement.setObject(6, book.getYear());
            statement.setLong(7, book.getId());

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        try (
                Connection connection = connectionManagerImpl.getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                Book book = new Book();
                setData(book, resultSet);
                bookList.add(book);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public Book getByIsbn(String isbn) {
        Book book = new Book();
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_ISBN)
        ){
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            setData(book, resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public Book getById(Long id) {
        Book book = new Book();
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_ID)
        ){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            setData(book, resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        List<Book> bookList = new ArrayList<>();
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_AUTHOR)
        ){
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                setData(book, resultSet);
                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public boolean deleteByIsbn(String isbn) {
        int resultUpdate;
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_BY_ISBN)
        ){
            statement.setString(1, isbn);
            resultUpdate = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultUpdate > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        int resultUpdate = 0;
        try (
                Connection connection = connectionManagerImpl.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_BY_ID)
        ){
            statement.setLong(1, id);
            resultUpdate = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultUpdate > 0;
    }

    @Override
    public long countAll() {
        long rowCount = 0L;
        try (
                Connection connection = connectionManagerImpl.getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(SIZE_DATABASE);
            if (resultSet.next())
                rowCount = resultSet.getLong("row_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowCount;
    }

    private void setData(Book book, ResultSet resultSet) {
        try {
            book.setId(resultSet.getLong("id"));
            book.setAuthor(resultSet.getString("author"));
            book.setBookname(resultSet.getString("bookname"));
            book.setIsbn(resultSet.getString("isbn"));
            book.setNumberOfPages(resultSet.getObject("number_of_pages"));
            book.setPrice(resultSet.getBigDecimal("price"));
            book.setYear(resultSet.getObject("year"));
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
