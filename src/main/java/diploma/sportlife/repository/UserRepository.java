package diploma.sportlife.repository;

import diploma.sportlife.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsParticipantByNameAndSurnameAndDateOfBirth(String name, String surname, Date dateOfBirth);

    User findByNameAndSurnameAndDateOfBirth(String name, String surname, Date dateOfBirth);

    User findByEmail(String email);
}
