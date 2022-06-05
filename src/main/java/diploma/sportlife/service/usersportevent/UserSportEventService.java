package diploma.sportlife.service.usersportevent;

import diploma.sportlife.model.UserSportEvent;

import java.util.List;

public interface UserSportEventService {
    List<UserSportEvent> readAll();

    UserSportEvent findById(Integer id);

    List<UserSportEvent> findByUserId(Integer id);

    List<UserSportEvent> findBySportEventId(Integer id);

    UserSportEvent insertUserSportEvent(UserSportEvent userSportEvent);

    UserSportEvent deleteById(Integer id);

    UserSportEvent putById(Integer id, UserSportEvent givenProfileSport);

    void assertUserSportEventExists(Integer id);
}
