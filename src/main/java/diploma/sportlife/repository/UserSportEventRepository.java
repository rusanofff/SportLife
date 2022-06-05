package diploma.sportlife.repository;

import diploma.sportlife.model.UserSportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSportEventRepository extends JpaRepository<UserSportEvent, Integer> {
    List<UserSportEvent> findByProfile_Id(Integer profile_id);

    List<UserSportEvent> findBySportEvent_id(Integer sportEvent_id);

    UserSportEvent getByProfile_IdAndSportEvent_Id(Integer profile_id, Integer sportEvent_id);

    boolean existsByProfile_IdAndSportEvent_Id(Integer profile_id, Integer sportEvent_id);
}
