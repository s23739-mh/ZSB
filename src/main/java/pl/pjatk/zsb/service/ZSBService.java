package pl.pjatk.zsb.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zsb.domain.Genres;
import pl.pjatk.zsb.domain.Book;
import pl.pjatk.zsb.repository.UsersRepository;
import pl.pjatk.zsb.repository.ZSBRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



/*
There shall be only one service created for both repositories,
since the data from both of them has some relation.
This solution is much less clear but is necessary
example: I want to check which user has the book with id=1, so
I need to join both tables on id of booked item from separate repositories
*/
@Service
public class ZSBService {
    public ZSBService(ZSBRepository zsbRepository, UsersRepository usersRepository) {
        this.zsbRepository = zsbRepository;
        this.usersRepository = usersRepository;
    }
    private final UsersRepository usersRepository;
    private final ZSBRepository zsbRepository;

    public List<Book> getAll() {
        return zsbRepository.findAll();
    }

    public Book getExampleBook() {
        Book book = new Book(10, "noname", "idk", Genres.HORROR,"polish",2010,"pjatk",null, LocalDate.now(),null);
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
        book.setOwner_ID(0);
        return zsbRepository.save(book);
    }

    public Book makeNotAvailable(Integer id) {
        Book book = getBookById(id);
        book.setOwner_ID(1);
        return zsbRepository.save(book);
    }
    public Book findById(Integer id) {
        Book book = null; // DB query
        Optional<Book> byId = zsbRepository.findById(id);
        return byId.orElse(null);
    }
}
