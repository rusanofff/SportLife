package diploma.sportlife.service.profilesportevent;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.MismatchedException;
import diploma.sportlife.exception.notfound.ProfileSportEventNotFoundException;
import diploma.sportlife.model.ProfileSportEvent;
import diploma.sportlife.repository.ProfileSportEventRepository;
import diploma.sportlife.service.profile.ProfileService;
import diploma.sportlife.service.sportevent.SportEventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProfileSportEventServiceImpl implements ProfileSportEventService{
    private final ProfileSportEventRepository profileSportEventRepository;
    private final ProfileService profileService;
    private final SportEventService sportEventService;

    public ProfileSportEventServiceImpl(ProfileSportEventRepository profileSportEventRepository,
                                        ProfileService profileService,
                                        SportEventService sportEventService) {
        this.profileSportEventRepository = profileSportEventRepository;
        this.profileService = profileService;
        this.sportEventService = sportEventService;
    }

    @Override
    public List<ProfileSportEvent> readAll() {
        return new ArrayList<>(profileSportEventRepository.findAll());
    }

    @Override
    public ProfileSportEvent findById(Integer id) {
        return profileSportEventRepository.findById(id)
                .orElseThrow(ProfileSportEventNotFoundException::new);
    }

    @Override
    public List<ProfileSportEvent> findByProfileId(Integer id) {
        profileService.assertProfileExists(id);
        return new ArrayList<>(profileSportEventRepository.findByProfile_Id(id));
    }

    @Override
    public List<ProfileSportEvent> findBySportEventId(Integer id) {
        sportEventService.assertSportEventExists(id);
        return new ArrayList<>(profileSportEventRepository.findBySportEvent_id(id));
    }

    @Override
    public ProfileSportEvent insertProfileSportEvent(ProfileSportEvent profileSportEvent) {
        Integer profileId = profileSportEvent.getProfile().getId();
        Integer sportEventId = profileSportEvent.getSportEvent().getId();

        profileService.assertProfileExists(profileId);
        sportEventService.assertSportEventExists(sportEventId);

        if (profileSportEventRepository.existsByProfile_IdAndSportEvent_Id(profileId, sportEventId)){
            throw new EntityAlreadyExistsException();
        }

        return profileSportEventRepository.save(profileSportEvent);
    }

    @Override
    public ProfileSportEvent deleteById(Integer id) {
        ProfileSportEvent profileSportEvent = profileSportEventRepository.findById(id)
                .orElseThrow(ProfileSportEventNotFoundException::new);
        profileSportEventRepository.deleteById(id);
        return profileSportEvent;
    }

    @Override
    public ProfileSportEvent putById(Integer id, ProfileSportEvent givenProfileSportEvent) {
        assertProfileSportEventExists(id);

        Integer profileId = givenProfileSportEvent.getProfile().getId();
        profileService.assertProfileExists(profileId);

        Integer sportEventId = givenProfileSportEvent.getSportEvent().getId();
        sportEventService.assertSportEventExists(sportEventId);

        ProfileSportEvent profileSportEvent = profileSportEventRepository.getByProfile_IdAndSportEvent_Id(profileId, sportEventId);

        if (profileSportEvent != null && !Objects.equals(profileSportEvent.getId(), id)){
            throw new MismatchedException();
        }

        givenProfileSportEvent.setId(id);
        return profileSportEventRepository.save(givenProfileSportEvent);
    }

    @Override
    public void assertProfileSportEventExists(Integer id) {
        if (!profileSportEventRepository.existsById(id)) {
            throw new ProfileSportEventNotFoundException();
        }
    }
}
