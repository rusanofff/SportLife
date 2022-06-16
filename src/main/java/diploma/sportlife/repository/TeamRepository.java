package diploma.sportlife.repository;

import diploma.sportlife.model.SportEvent;
import diploma.sportlife.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    boolean existsByNameAndSportEventId(String name,
                                        Integer sportEventId);
    List<Team> findBySportEvent(SportEvent sportEvent);


}
