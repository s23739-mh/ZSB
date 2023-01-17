package pl.pjatk.zsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pjatk.zsb.domain.Favourite;

import java.util.List;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Integer> {

    @Transactional
    @Modifying
    @Query("delete from Favourite f where f.id_book = ?1 and f.mail_user = ?2")
    void deleteFavouriteById_bookAndMail_user(Integer id_book, String mail_user);

    @Query("select f from Favourite f where f.mail_user = ?1")
    List<Favourite> getFavouritesByMail_user(String mail_user);
}
