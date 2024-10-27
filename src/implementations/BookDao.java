package implementations;

import model.Book;

import java.util.List;

public interface BookDao {

    void createRS(Book book);

    void createBook(Book book);

    void updateRS(Book book);

    List<Book> getAllBooks();

    Book getBookByIsbn(String isbn);

    List<Book> getByAuthor(String author);

    boolean deleteBookByIsbn(String isbn);

    Long countAll();

    void printTableInfo();
}
