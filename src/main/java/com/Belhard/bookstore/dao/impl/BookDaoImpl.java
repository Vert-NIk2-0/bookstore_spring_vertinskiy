package com.belhard.bookstore.dao.impl;

import com.belhard.bookstore.dao.BookDao;
import com.belhard.bookstore.connection.impl.ConnectionManagerImpl;
import com.belhard.bookstore.dao.entity.Book;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Repository
public class BookDaoImpl implements BookDao {

    private static final String SELECT_BOOK_BY_ISBN =
            "SELECT * FROM books WHERE isbn = ?";

    private static final String SELECT_BOOK_BY_AUTHOR =
            "SELECT * FROM books WHERE author = ?";

    private static final String COUNT_ALL =
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

    private static final String UPDATE_BOOK_BY_ID_NP =
            "UPDATE books " +
                    "SET author = :author, bookname = :bookname, isbn = :isbn, number_of_pages = :number_of_pages, price = :price, year = :year WHERE id = :id";

    private static final String SELECT_ALL_BOOKS =
            "SELECT id, author, bookname, isbn, number_of_pages, price, year FROM books";

    private static final String SELECT_BOOK_BY_ID =
            "SELECT * FROM books WHERE id = ?";

    private final ConnectionManagerImpl connectionManagerImpl;
    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate namedTemplate;

    @Override
    public Book create(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update((connectionManagerImpl) -> getStatement(book), keyHolder);
        Long key = keyHolder.getKeyAs(Long.class);
        return getById(key);
    }

    @Override
    public Book update(Book book) {
        namedTemplate.update(UPDATE_BOOK_BY_ID_NP, getMap(book));
        return getById(book.getId());
    }

    @Override
    public List<Book> getAll() {
        return template.query(SELECT_ALL_BOOKS, this::setData);
    }

    @Override
    public Book getByIsbn(String isbn) {
        return template.queryForObject(SELECT_BOOK_BY_ISBN, this::setData, isbn);
    }

    @Override
    public Book getById(Long id) {
        return template.queryForObject(SELECT_BOOK_BY_ID, this::setData, id);
    }

    @Override
    public List<Book> getByAuthor(String author) {
        return template.query(SELECT_BOOK_BY_AUTHOR, this::setData, author);
    }

    @Override
    public boolean deleteByIsbn(String isbn) {
        return template.update(DELETE_BOOK_BY_ISBN, isbn) == 1;
    }

    @Override
    public boolean deleteById(Long id) {
        return template.update(DELETE_BOOK_BY_ID, id) == 1;
    }

    @Override
    public long countAll() {
        Long count = template.queryForObject(COUNT_ALL, (rs, rowNum) -> rs.getLong("row_count"));
        return count != null ? count : 0L;
    }

    private Book setData(ResultSet resultSet, int row) {
        Book book = new Book();
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
        return book;
    }

    private Map<String, Object> getMap(Book book) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", book.getId());
        map.put("year", book.getYear());
        map.put("number_of_pages", book.getNumberOfPages());
        map.put("isbn", book.getIsbn());
        map.put("author", book.getAuthor());
        map.put("bookname", book.getBookname());
        map.put("price", book.getPrice());
        return map;
    }

    private PreparedStatement getStatement(Book book) {
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
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
