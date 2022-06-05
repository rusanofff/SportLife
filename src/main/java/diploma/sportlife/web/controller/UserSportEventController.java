package diploma.sportlife.web.controller;

import diploma.sportlife.converter.SportEventConverterImpl;
import diploma.sportlife.converter.UserConverterImpl;
import diploma.sportlife.converter.UserSportEventConverterImpl;
import diploma.sportlife.model.UserSportEvent;
import diploma.sportlife.service.usersportevent.UserSportEventService;
import diploma.sportlife.web.model.SportEventDto;
import diploma.sportlife.web.model.UserDto;
import diploma.sportlife.web.model.UserSportEventDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserSportEventController {
    private final UserSportEventService service;

    private final UserSportEventConverterImpl userSportEventConverter;

    private final UserConverterImpl userConverter;

    private final SportEventConverterImpl sportEventConverter;

    public UserSportEventController(UserSportEventService service,
                                    UserSportEventConverterImpl userSportEventConverter,
                                    UserConverterImpl userConverter,
                                    SportEventConverterImpl sportEventConverter) {
        this.service = service;
        this.userSportEventConverter = userSportEventConverter;
        this.userConverter = userConverter;
        this.sportEventConverter = sportEventConverter;
    }

    @GetMapping("/user-events")
    public List<UserSportEventDto> getUserSportEventList(){
        return service.readAll()
            .stream()
            .map(userSportEventConverter::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/user-event/{id}")
    public UserSportEventDto getUserSportEvent(@PathVariable(name = "id") Integer id){
        UserSportEvent userSportEvent = service.findById(id);
        return userSportEventConverter.toDto(userSportEvent);
    }

    @GetMapping(path = "/user-event/user/{id}")
    public List<UserDto> getUserSportEventByUserId(@PathVariable(name = "id") Integer id) {
        return service.findByUserId(id)
                      .stream()
                      .map(userSportEvent -> userConverter.toDto(userSportEvent.getUser()))
                      .collect(Collectors.toList());
    }

    @GetMapping(path = "/user-event/event/{id}")
    public List<SportEventDto> getUserSportEventBySportEventId(@PathVariable(name = "id") Integer id) {
        return service.findBySportEventId(id)
                      .stream()
                      .map(userSportEvent -> sportEventConverter.toDto(userSportEvent.getSportEvent()))
                      .collect(Collectors.toList());
    }


    @PostMapping(path = "/user-event")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserSportEventDto insertUserSportEvent(@Valid @RequestBody UserSportEventDto userSportEventDto) {
        UserSportEvent givenUserSportEvent = userSportEventConverter.fromDto(userSportEventDto);
        UserSportEvent insertUserSportEvent = service.insertUserSportEvent(givenUserSportEvent);
        return userSportEventConverter.toDto(insertUserSportEvent);
    }

    @DeleteMapping("/user-event/{id}")
    public UserSportEventDto deleteUserSportEvent(@PathVariable(name = "id") Integer id) {
        UserSportEvent deletedUserSportEvent = service.deleteById(id);
        return userSportEventConverter.toDto(deletedUserSportEvent);
    }

    @PutMapping(path = "/user-event/{id}")
    public UserSportEventDto putUserSportEvent(@PathVariable(name = "id") Integer id,
                                               @Valid @RequestBody UserSportEventDto userSportEventDto) {
        UserSportEvent userSportEvent = userSportEventConverter.fromDto(userSportEventDto);
        UserSportEvent updatedUserSportEvent = service.putById(id, userSportEvent);
        return userSportEventConverter.toDto(updatedUserSportEvent);
    }
}
