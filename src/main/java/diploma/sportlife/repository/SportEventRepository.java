package diploma.sportlife.repository;

import diploma.sportlife.model.SportEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SportEventRepository extends JpaRepository<SportEvent, Integer>,
    JpaSpecificationExecutor<SportEvent> {
    boolean existsByNameAndStartDateAndUserIdAndActivityId(String name,
                                                          Timestamp startDate,
                                                          Integer profileId,
                                                              Integer activityId);
}
