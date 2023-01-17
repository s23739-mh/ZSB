package pl.pjatk.zsb.service;

import org.springframework.stereotype.Service;
import pl.pjatk.zsb.domain.Book;
import pl.pjatk.zsb.domain.Favourite;
import pl.pjatk.zsb.domain.Type;
import pl.pjatk.zsb.domain.User;
import pl.pjatk.zsb.repository.FavouriteRepository;
import pl.pjatk.zsb.repository.UsersRepository;
import pl.pjatk.zsb.repository.ZSBRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ZSBService {
    private final UsersRepository usersRepository;
    private final ZSBRepository zsbRepository;
    private final FavouriteRepository favouriteRepository;

    public ZSBService(ZSBRepository zsbRepository, UsersRepository usersRepository,
                      FavouriteRepository favouriteRepository) {
        this.zsbRepository = zsbRepository;
        this.usersRepository = usersRepository;
        this.favouriteRepository = favouriteRepository;
    }

    public Favourite addFavourite(Integer id_book, String mail_user) {
        Favourite favourite = new Favourite(null, id_book, mail_user);
        return favouriteRepository.save(favourite);
    }

    @Transactional
    public void removeFavourite(Integer id_book, String mail_user) {
        favouriteRepository.deleteFavouriteById_bookAndMail_user(id_book, mail_user);
    }

    public List<Favourite> getFavourites(String mail_user) {
        return favouriteRepository.getFavouritesByMail_user(mail_user);
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

    public List<Book> getAllBooksByMail(String mail) {
        return zsbRepository.findBooksByOwner_mail(mail);
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
        Optional<Book> byId = zsbRepository.findById(id);
        return byId.orElse(null);
    }
}
