package diploma.sportlife.service.activity;

import diploma.sportlife.model.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> readAll();

    Activity findById(Integer id);

    Activity insertActivity(Activity activity);

    Activity putById(Integer id, Activity givenActivityFromJson);

    Activity deleteById(Integer id);

    void assertActivityExists(Integer id);
}
