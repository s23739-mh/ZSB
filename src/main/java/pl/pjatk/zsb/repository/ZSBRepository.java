package pl.pjatk.zsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pjatk.zsb.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface ZSBRepository extends JpaRepository<Book, Integer> {
    List<Book> findAll();

    Book save(Book book);

    Optional<Book> findById(Integer id);

    @Query("select b from Book b where b.owner_mail = ?1")
    List<Book> findBooksByOwner_mail(String mail);

    void deleteById(Integer id);
}
