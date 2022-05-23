package diploma.sportlife.service.profilesportevent;

import diploma.sportlife.model.ProfileSportEvent;

import java.util.List;

public interface ProfileSportEventService {
    List<ProfileSportEvent> readAll();

    ProfileSportEvent findById(Integer id);

    List<ProfileSportEvent> findByProfileId(Integer id);

    List<ProfileSportEvent> findBySportEventId(Integer id);

    ProfileSportEvent insertProfileSportEvent(ProfileSportEvent profileSportEvent);

    ProfileSportEvent deleteById(Integer id);

    ProfileSportEvent putById(Integer id, ProfileSportEvent givenProfileSport);

    void assertProfileSportEventExists(Integer id);
}
