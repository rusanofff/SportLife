package diploma.sportlife.repository;

import diploma.sportlife.model.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface SportEventRepository extends JpaRepository<SportEvent, Integer> {
    boolean existsByNameAndStartDateAndProfileIdAndActivityId(String name,
                                                          Timestamp startDate,
                                                          Integer profileId,
                                                              Integer activityId);
}
