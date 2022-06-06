package diploma.sportlife.service.usersportevent;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.MismatchedException;
import diploma.sportlife.exception.notfound.UserSportEventNotFoundException;
import diploma.sportlife.model.UserSportEvent;
import diploma.sportlife.repository.UserSportEventRepository;
import diploma.sportlife.service.sportevent.SportEventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserSportEventServiceImpl implements UserSportEventService {
    private final UserSportEventRepository userSportEventRepository;
    private final diploma.sportlife.service.user.UserService userService;
    private final SportEventService sportEventService;

    public UserSportEventServiceImpl(UserSportEventRepository userSportEventRepository,
                                     diploma.sportlife.service.user.UserService userService,
                                     SportEventService sportEventService) {
        this.userSportEventRepository = userSportEventRepository;
        this.userService = userService;
        this.sportEventService = sportEventService;
    }

    @Override
    public List<UserSportEvent> readAll() {
        return new ArrayList<>(userSportEventRepository.findAll());
    }

    @Override
    public UserSportEvent findById(Integer id) {
        return userSportEventRepository.findById(id)
                                       .orElseThrow(UserSportEventNotFoundException::new);
    }

    @Override
    public List<UserSportEvent> findByUserId(Integer id) {
        userService.assertUserExists(id);
        return new ArrayList<>(userSportEventRepository.findByUserId(id));
    }

    @Override
    public List<UserSportEvent> findBySportEventId(Integer id) {
        sportEventService.assertSportEventExists(id);
        return new ArrayList<>(userSportEventRepository.findBySportEvent_id(id));
    }

    @Override
    public UserSportEvent insertUserSportEvent(UserSportEvent userSportEvent) {
        Integer profileId = userSportEvent.getUser().getId();
        Integer sportEventId = userSportEvent.getSportEvent().getId();

        userService.assertUserExists(profileId);
        sportEventService.assertSportEventExists(sportEventId);

        if (userSportEventRepository.existsByUserIdAndSportEventId(profileId, sportEventId)){
            throw new EntityAlreadyExistsException();
        }

        return userSportEventRepository.save(userSportEvent);
    }

    @Override
    public UserSportEvent deleteById(Integer id) {
        UserSportEvent userSportEvent = userSportEventRepository.findById(id)
                                                                .orElseThrow(UserSportEventNotFoundException::new);
        userSportEventRepository.deleteById(id);
        return userSportEvent;
    }

    @Override
    public UserSportEvent putById(Integer id, UserSportEvent givenUserSportEvent) {
        assertUserSportEventExists(id);

        Integer profileId = givenUserSportEvent.getUser().getId();
        userService.assertUserExists(profileId);

        Integer sportEventId = givenUserSportEvent.getSportEvent().getId();
        sportEventService.assertSportEventExists(sportEventId);

        UserSportEvent
            userSportEvent = userSportEventRepository.getByUserIdAndSportEventId(profileId, sportEventId);

        if (userSportEvent != null && !Objects.equals(userSportEvent.getId(), id)){
            throw new MismatchedException();
        }

        givenUserSportEvent.setId(id);
        return userSportEventRepository.save(givenUserSportEvent);
    }

    @Override
    public void assertUserSportEventExists(Integer id) {
        if (!userSportEventRepository.existsById(id)) {
            throw new UserSportEventNotFoundException();
        }
    }
}
