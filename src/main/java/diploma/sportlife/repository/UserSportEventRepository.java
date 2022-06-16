package diploma.sportlife.repository;

import diploma.sportlife.model.UserSportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSportEventRepository extends JpaRepository<UserSportEvent, Integer> {
    List<UserSportEvent> findByUserId(Integer profile_id);

    List<UserSportEvent> findBySportEventId(Integer sportEvent_id);

    UserSportEvent getByUserIdAndSportEventId(Integer profile_id, Integer sportEvent_id);

    boolean existsByUserIdAndSportEventId(Integer profile_id, Integer sportEvent_id);
}
