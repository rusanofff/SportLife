package diploma.sportlife.web.controller;

import diploma.sportlife.converter.ActivityConverterImpl;
import diploma.sportlife.model.Activity;
import diploma.sportlife.service.activity.ActivityService;
import diploma.sportlife.web.model.ActivityDto;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    private final ActivityService service;

    private final ActivityConverterImpl converter;

    public ActivityController(ActivityService service,
                              ActivityConverterImpl converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping( "/sports")
    public List<ActivityDto> getActivityList(){
        return service.readAll()
            .stream()
            .map(converter::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/sport/{id}")
    public ActivityDto getActivity(@PathVariable(name = "id") Integer id){
        Activity activity = service.findById(id);
        return converter.toDto(activity);
    }

    @PostMapping("/sport")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ActivityDto insertActivity(@Valid @RequestBody ActivityDto activityDto){
        Activity activity = converter.fromDto(activityDto);
        activity = service.insertActivity(activity);
        return converter.toDto(activity);
    }

    @PutMapping("/sport/{id}")
    public ActivityDto putActivity(@PathVariable(name = "id") Integer id,
                                   @Valid @RequestBody ActivityDto activityDto){
        Activity activity = converter.fromDto(activityDto);
        Activity updatedActivity = service.putById(id, activity);
        return converter.toDto(updatedActivity);
    }

    @DeleteMapping("/activity/{id}")
    public ActivityDto deleteActivity(@PathVariable(name = "id") Integer id){
        Activity activity = service.deleteById(id);
        return converter.toDto(activity);
    }
}
