package pl.pjatk.zsb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.zsb.domain.User;

import java.util.Optional;

/*
It is possible to configure repositories in such a way that
there exists one final repository, however it is not needed
since we are working only on few Entities
*/
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMailAndPassword(String mail, String password);

    User save(User user);

}
