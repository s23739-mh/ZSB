package pl.pjatk.zsb.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zsb.domain.Genres;
import pl.pjatk.zsb.domain.Book;
import pl.pjatk.zsb.repository.ZSBRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ZSBService {
    public ZSBService(ZSBRepository zsbRepository) {
        this.zsbRepository = zsbRepository;
    }

    private final ZSBRepository zsbRepository;

    public List<Book> getAll() {
        return zsbRepository.findAll();
    }

    public Book getExampleBook() {
        Book book = new Book(10, "noname", "idk", Genres.HORROR,"polish",2010,"pjatk",true);
        zsbRepository.save(book);
        return book;
    }

    public Book getBookById(int id) {
        Optional<Book> byId = zsbRepository.findById(id);
        return byId.orElse(null);
    }

    public Book addBook(Book book) {
        return zsbRepository.save(book);
    }

    public Book updateBook(Integer id, Book book) {
        zsbRepository.save(book);
        return book;
    }

    public void deleteBook(Integer id) {
        zsbRepository.deleteById(id);
    }

    public Book makeAvailable(Integer id) {
        Book book = getBookById(id);
        book.setAvailable(true);
        return zsbRepository.save(book);
    }

    public Book makeNotAvailable(Integer id) {
        Book book = getBookById(id);
        book.setAvailable(false);
        return zsbRepository.save(book);
    }
    public Book findById(Integer id) {
        Book book = null; // DB query
        Optional<Book> byId = zsbRepository.findById(id);
        return byId.orElse(null);
    }
}
