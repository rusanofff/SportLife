package diploma.sportlife.service.sportevent;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.notfound.SportEventNotFoundException;
import diploma.sportlife.model.Activity;
import diploma.sportlife.model.Profile;
import diploma.sportlife.model.SportEvent;
import diploma.sportlife.repository.SportEventRepository;
import diploma.sportlife.service.activity.ActivityService;
import diploma.sportlife.service.profile.ProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SportEventServiceImpl implements SportEventService{
    private final SportEventRepository sportEventRepository;
    private final ProfileService profileService;
    private final ActivityService activityService;

    public SportEventServiceImpl(SportEventRepository sportEventRepository,
                                 ProfileService profileService,
                                 ActivityService activityService) {
        this.sportEventRepository = sportEventRepository;
        this.profileService = profileService;
        this.activityService = activityService;
    }

    @Override
    public List<SportEvent> readAll() {
        return new ArrayList<>(sportEventRepository.findAll());
    }

    @Override
    public SportEvent findById(Integer id) {
        return sportEventRepository.findById(id).orElseThrow(SportEventNotFoundException::new);
    }

    @Override
    public SportEvent insertSportEvent(SportEvent sportEvent) {
        Profile profile = sportEvent.getProfile();
        Integer profileId = profile.getId();
        profileService.assertProfileExists(profileId);

        Activity activity = sportEvent.getActivity();
        Integer activityId  = activity.getId();
        activityService.assertActivityExists(activityId);

        if (sportEventRepository.existsByNameAndStartDateAndProfileIdAndActivityId(
                sportEvent.getName(), sportEvent.getStartDate(), profileId, activityId)){
            throw new EntityAlreadyExistsException();
        }

        return sportEventRepository.save(sportEvent);
    }

    @Override
    public SportEvent putById(Integer id, SportEvent givenSportEvent) {
        assertSportEventExists(id);

        Integer profileIdFromJson = givenSportEvent.getProfile().getId();
        profileService.assertProfileExists(profileIdFromJson);

        Integer activityIdFromJson = givenSportEvent.getActivity().getId();
        activityService.assertActivityExists(activityIdFromJson);

        if (sportEventRepository.existsByNameAndStartDateAndProfileIdAndActivityId(
                givenSportEvent.getName(),
                givenSportEvent.getStartDate(),
                profileIdFromJson,
                activityIdFromJson)){
            throw new EntityAlreadyExistsException();
        }

        givenSportEvent.setId(id);
        return sportEventRepository.save(givenSportEvent);
    }

    @Override
    public SportEvent deleteById(Integer id) {
        SportEvent sportEvent = sportEventRepository.findById(id).orElseThrow(SportEventNotFoundException::new);
        sportEventRepository.deleteById(id);
        return sportEvent;
    }

    @Override
    public void assertSportEventExists(Integer id) {
        if (!sportEventRepository.existsById(id)) {
            throw new SportEventNotFoundException();
        }
    }
}
