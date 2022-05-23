package diploma.sportlife.service.sportevent;

import diploma.sportlife.model.SportEvent;

import java.util.List;

public interface SportEventService {
    List<SportEvent> readAll();

    SportEvent findById(Integer id);

    SportEvent insertSportEvent(SportEvent sportEvent);

    SportEvent putById(Integer id, SportEvent givenSportEvent);

    SportEvent deleteById(Integer id);

    void assertSportEventExists(Integer id);
}
