package diploma.sportlife.service.sportevent;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.notfound.SportEventNotFoundException;
import diploma.sportlife.model.Activity;
import diploma.sportlife.model.User;
import diploma.sportlife.model.SportEvent;
import diploma.sportlife.repository.SportEventRepository;
import diploma.sportlife.service.activity.ActivityService;
import java.sql.Timestamp;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SportEventServiceImpl implements SportEventService{
    private final SportEventRepository sportEventRepository;
    private final diploma.sportlife.service.user.UserService userService;
    private final ActivityService activityService;

    public SportEventServiceImpl(SportEventRepository sportEventRepository,
                                 diploma.sportlife.service.user.UserService userService,
                                 ActivityService activityService) {
        this.sportEventRepository = sportEventRepository;
        this.userService = userService;
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
        User user = sportEvent.getUser();
        Integer profileId = user.getId();
        userService.assertUserExists(profileId);

        Activity activity = sportEvent.getActivity();
        Integer activityId  = activity.getId();
        activityService.assertActivityExists(activityId);

        if (sportEventRepository.existsByNameAndStartDateAndUserIdAndActivityId(
                sportEvent.getName(), sportEvent.getStartDate(), profileId, activityId)){
            throw new EntityAlreadyExistsException();
        }

        return sportEventRepository.save(sportEvent);
    }

    @Override
    public SportEvent putById(Integer id, SportEvent givenSportEvent) {
        assertSportEventExists(id);

        Integer profileIdFromJson = givenSportEvent.getUser().getId();
        userService.assertUserExists(profileIdFromJson);

        Integer activityIdFromJson = givenSportEvent.getActivity().getId();
        activityService.assertActivityExists(activityIdFromJson);

        if (sportEventRepository.existsByNameAndStartDateAndUserIdAndActivityId(
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

    @Override
    public List<SportEvent> findByUserId(Integer id) {
        return sportEventRepository.findByUserId(id);
    }
    @Override
    public List<SportEvent> getSportEventByFilters(Optional<Integer> activityId, Optional<Timestamp> startDate, Optional<String> town,
                                                   Optional<Integer> author) {

        Specification<SportEvent> spec = Specification.where(townIn(town))
            .and(startDateIn(startDate))
            .and(activityIn(activityId))
            .and(authorIn(author));
        return sportEventRepository.findAll(spec);
    }

    public static Specification<SportEvent> townIn(Optional<String> town){
        return (root, query, builder) ->
            town.isPresent() ?
                root.get("town").in(town.get()) :
                builder.conjunction();
    }

    public static Specification<SportEvent> startDateIn(Optional<Timestamp> startDate){
        return (root, query, builder) ->
            startDate.isPresent() ?
                root.get("startDate").in(startDate.get()) :
                builder.conjunction();
    }

    public static Specification<SportEvent> activityIn(Optional<Integer> activityId){
        return (root, query, builder) ->
            activityId.isPresent() ?
                root.get("activity").in(activityId.get()) :
                builder.conjunction();
    }

    public static Specification<SportEvent> authorIn(Optional<Integer> author){
        return (root, query, builder) ->
            author.isPresent() ?
                root.get("author").in(author.get()) :
                builder.conjunction();
    }
}
