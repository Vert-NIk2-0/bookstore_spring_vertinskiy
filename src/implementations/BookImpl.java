package implementations;

import model.Book;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookImpl extends Implementation implements BookDao{
    private static final String SELECT_ALL_BOOKS =
            "SELECT id, bookname, author, year FROM books";

    private static final String SELECT_BOOK_BY_ISBN =
            "SELECT * FROM books WHERE isbn = ?";

    private static final String SELECT_BOOK_BY_AUTHOR =
            "SELECT * FROM books WHERE author = ?";

    private static final String SIZE_DATABASE =
            "SELECT COUNT(*) AS row_count FROM books";

    private static final String DELETE_BOOK_BY_ISBN =
            "DELETE FROM books WHERE isbn = ?";

    private static final String CREATE_BOOK =
            "INSERT INTO books (author, bookname, isbn, number_of_pages, price, year) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_BOOK_BY_ID =
            "SELECT author, bookname, isbn, number_of_pages, price, year FROM books WHERE isbn = ?";

    private static final String SELECT_ALL_COLUMNS =
            "SELECT author, bookname, isbn, number_of_pages, price, year FROM books";

    @Override
    public void updateRS(Book book) {
        try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_BOOK_BY_ID, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            statement.setString(1, book.getIsbn());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultSet.updateString("author", book.getAuthor());
                resultSet.updateString("bookname", book.getBookname());
                resultSet.updateString("isbn", book.getIsbn());
                resultSet.updateObject("number_of_pages", book.getNumberOfPages());
                resultSet.updateBigDecimal("price", book.getPrice());
                resultSet.updateObject("year", book.getYear());
                resultSet.updateRow();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createRS(Book book) {
        try (Statement statement = getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_COLUMNS);
            resultSet.moveToInsertRow();
            resultSet.updateString("author", book.getAuthor());
            resultSet.updateString("bookname", book.getBookname());
            resultSet.updateString("isbn", book.getIsbn());
            resultSet.updateObject("number_of_pages", book.getNumberOfPages());
            resultSet.updateBigDecimal("price", book.getPrice());
            resultSet.updateObject("year", book.getYear());
            resultSet.insertRow();
            resultSet.moveToCurrentRow();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBook(Book book) {
        try (PreparedStatement statement = getConnection().prepareStatement(CREATE_BOOK, Statement.RETURN_GENERATED_KEYS)){

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
    public List<Book> getAllBooks() {
        List<Book> bookList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BOOKS);
            while (resultSet.next()) {
                Book book = new Book();
                try {
                    book.setId(resultSet.getLong("id"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setBookname(resultSet.getString("bookname"));
                    book.setYear(resultSet.getObject("year"));
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                bookList.add(book);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookList;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Book book = new Book();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BOOK_BY_ISBN)){
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
    public List<Book> getByAuthor(String author) {
        List<Book> bookList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(SELECT_BOOK_BY_AUTHOR)){
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
    public boolean deleteBookByIsbn(String isbn) {
        int resultUpdate = 0;
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_BOOK_BY_ISBN)){
            statement.setString(1, isbn);
            resultUpdate = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultUpdate > 0;
    }

    @Override
    public Long countAll() {
        long rowCount = 0L;
        try (Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(SIZE_DATABASE);
            if (resultSet.next())
                rowCount = resultSet.getLong("row_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowCount;
    }

    @Override
    public void printTableInfo() {
        try (
            FileWriter fileWriter = new FileWriter("output.txt");
            Statement statement = getConnection().createStatement();
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT collation_name FROM information_schema.columns " +
                    "WHERE table_name = 'books' AND column_name = ?")
        ){
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BOOKS);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            fileWriter.write("                              Table \"book\"\n");
            fileWriter.write("     Column     |        Type        |       Collation       | Nullable |\n");
            fileWriter.write("----------------+--------------------+-----------------------+----------|\n");

            // Печатаем данные столбцов
            for (int i = 1; i <= columnCount; i++) {
                preparedStatement.setString(1, metaData.getColumnName(i));
                ResultSet resultSetCollation = preparedStatement.executeQuery();

                String columnName = metaData.getColumnName(i);
                String columnType = metaData.getColumnTypeName(i);
                String collation = resultSetCollation.next() + "";
                String nullable = metaData.isNullable(i) == ResultSetMetaData.columnNullable ? "" : "not null";

                fileWriter.write(String.format("%-16s| %-19s| %-22s| %-9s|%n", columnName, columnType, collation, nullable));
            }



        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
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
