package pl.workshop5.model;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBooks();

    Optional<Book> getBookById(Long id);

    void addBook(Book book);

    boolean editBook(Book book);

    boolean deleteBook(Long id);

    Long generateNewId();

    boolean checkIfBookByIdExists(Long id);
}
