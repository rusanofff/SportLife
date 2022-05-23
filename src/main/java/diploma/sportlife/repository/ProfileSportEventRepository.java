package diploma.sportlife.repository;

import diploma.sportlife.model.ProfileSportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileSportEventRepository extends JpaRepository<ProfileSportEvent, Integer> {
    List<ProfileSportEvent> findByProfile_Id(Integer profile_id);

    List<ProfileSportEvent> findBySportEvent_id(Integer sportEvent_id);

    ProfileSportEvent getByProfile_IdAndSportEvent_Id(Integer profile_id, Integer sportEvent_id);

    boolean existsByProfile_IdAndSportEvent_Id(Integer profile_id, Integer sportEvent_id);
}
