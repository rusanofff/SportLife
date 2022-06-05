package diploma.sportlife.service.sportevent;

import diploma.sportlife.model.SportEvent;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface SportEventService {
    List<SportEvent> readAll();

    SportEvent findById(Integer id);

    SportEvent insertSportEvent(SportEvent sportEvent);

    SportEvent putById(Integer id, SportEvent givenSportEvent);

    SportEvent deleteById(Integer id);

    void assertSportEventExists(Integer id);

    List<SportEvent> getSportEventByFilters(Optional<Integer> activityId, Optional<Timestamp> startDate, Optional<String> town,
                                            Optional<Integer> author);
}
