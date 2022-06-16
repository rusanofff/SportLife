package diploma.sportlife.web.controller;

import diploma.sportlife.converter.SportEventConverterImpl;
import diploma.sportlife.model.SportEvent;
import diploma.sportlife.service.sportevent.SportEventService;
import diploma.sportlife.web.model.SportEventDto;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SportEventController {
    private final SportEventService service;

    private final SportEventConverterImpl converter;

    public SportEventController(SportEventService service,
                                SportEventConverterImpl converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/events")
    public List<SportEventDto> getEventList(@RequestParam(required = false) Optional<Integer> activityId,
                                            @RequestParam(required = false) Optional<Timestamp> startDate,
                                            @RequestParam(required = false) Optional<String> town,
                                            @RequestParam(required = false) Optional<Integer> author){
        return service.getSportEventByFilters(activityId, startDate, town, author)
                      .stream()
                      .map(converter::toDto)
                      .collect(Collectors.toList());
    }

    @GetMapping("/event/{id}")
    public SportEventDto getSportEvent(@PathVariable(name = "id") Integer id){
        SportEvent sportEvent = service.findById(id);
        return converter.toDto(sportEvent);
    }

    @GetMapping("/event")
    public List<SportEventDto> getSportEventByUserId(@RequestParam(name = "userId") Integer userId){
        List<SportEvent> sportEvent = service.findByUserId(userId);
        return sportEvent.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/event")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SportEventDto insertSportEvent(@Valid @RequestBody SportEventDto eventDto) {
        SportEvent givenSportEvent = converter.fromDto(eventDto);
        SportEvent insertedSportEvent = service.insertSportEvent(givenSportEvent);
        return converter.toDto(insertedSportEvent);
    }

    @DeleteMapping("/event/{id}")
    public SportEventDto deleteSportEvent(@PathVariable(name = "id") Integer id) {
        SportEvent deletedSportEvent = service.deleteById(id);
        return converter.toDto(deletedSportEvent);
    }

    @PutMapping(path = "/event/{id}")
    public SportEventDto putSportEvent(@PathVariable(name = "id") Integer id,
                                  @RequestBody SportEventDto eventDto) {
        SportEvent givenSportEvent = converter.fromDto(eventDto);
        SportEvent updatedSportEvent = service.putById(id, givenSportEvent);
        return converter.toDto(updatedSportEvent);
    }
}
