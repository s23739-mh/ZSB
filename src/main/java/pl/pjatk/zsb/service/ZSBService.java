package pl.pjatk.zsb.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zsb.domain.Book;
import pl.pjatk.zsb.domain.Type;
import pl.pjatk.zsb.domain.User;
import pl.pjatk.zsb.repository.UsersRepository;
import pl.pjatk.zsb.repository.ZSBRepository;

import javax.transaction.Transactional;
import java.util.Date;
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
    private final UsersRepository usersRepository;
    private final ZSBRepository zsbRepository;


    public ZSBService(ZSBRepository zsbRepository, UsersRepository usersRepository) {
        this.zsbRepository = zsbRepository;
        this.usersRepository = usersRepository;
    }

    public User getUser(String mail) {
        return usersRepository.getUserByMail(mail);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User returnIfGoodPassword(String mail, String password) {
        Optional<User> byMail = usersRepository.findByMailAndPassword(mail, password);
        return byMail.orElse(null);
    }

    public User newUser(String fname, String sname, String mail, Date birthdate, String city, Type type, String password) {
        User user = new User(null, fname, sname, mail, birthdate, city, type, password);
        usersRepository.save(user);
        return user;
    }

    @Transactional
    public void removeUser(String mail) {
        usersRepository.deleteByMail(mail);
    }

    public List<Book> getAllBooks() {
        return zsbRepository.findAll();
    }

    public Book getBookById(int id) {
        Optional<Book> byId = zsbRepository.findById(id);
        return byId.orElse(null);
    }

    public Book addBook(Book book) {
        return zsbRepository.save(book);
    }

    @Transactional
    public void deleteBook(Integer id) {
        zsbRepository.deleteById(id);
    }


    public Book findById(Integer id) {
        Book book = null; // DB query
        Optional<Book> byId = zsbRepository.findById(id);
        return byId.orElse(null);
    }
}
