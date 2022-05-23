package diploma.sportlife.repository;

import diploma.sportlife.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TeamRepository extends JpaRepository<Team, Integer> {
    boolean existsByNameAndSportEventId(String name,
                                        Integer sportEventId);
}
