package implementations;

import model.Book;

import java.util.List;

public interface BookDao {

    void createBook(Book book);

    List<Book> getAllBooks();

    Book getBookByIsbn(String isbn);

    List<Book> getByAuthor(String author);

    boolean deleteBookByIsbn(String isbn);

    Long countAll();

}
