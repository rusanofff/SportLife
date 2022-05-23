package diploma.sportlife.repository;

import diploma.sportlife.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    boolean existsParticipantByFullNameAndDateOfBirth(String name, String surname, Date dateOfBirth);

    Profile findByFullNameAndDateOfBirth(String name, String surname, Date dateOfBirth);
}
