package pl.pjatk.zsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.zsb.domain.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    Rent findAllByMail(String mail);

    // Rent findAllByBook(Book book);
}
