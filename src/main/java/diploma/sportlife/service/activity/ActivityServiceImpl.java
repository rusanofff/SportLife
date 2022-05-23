package diploma.sportlife.service.activity;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.notfound.ActivityNotFoundException;
import diploma.sportlife.model.Activity;
import diploma.sportlife.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> readAll() {
        return new ArrayList<>(activityRepository.findAll());
    }

    @Override
    public Activity findById(Integer id) {
        return activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
    }

    @Override
    public Activity insertActivity(Activity activity) {
        if (activityRepository.existsByName(activity.getName())){
            throw new EntityAlreadyExistsException();
        }
        return activityRepository.save(activity);
    }

    @Override
    public Activity putById(Integer id, Activity givenActivityFromJson) {
        assertActivityExists(id);
        givenActivityFromJson.setId(id);
        return activityRepository.save(givenActivityFromJson);
    }

    @Override
    public Activity deleteById(Integer id) {
        Activity deletedActivity = activityRepository.findById(id).orElseThrow(ActivityNotFoundException::new);
        activityRepository.deleteById(id);
        return deletedActivity;
    }

    @Override
    public void assertActivityExists(Integer id) {
        if (!activityRepository.existsById(id)){
            throw new ActivityNotFoundException();
        }
    }
}
