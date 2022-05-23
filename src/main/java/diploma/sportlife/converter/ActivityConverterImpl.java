package diploma.sportlife.converter;

import diploma.sportlife.model.Activity;
import diploma.sportlife.web.model.ActivityDto;
import org.springframework.stereotype.Component;

@Component
public class ActivityConverterImpl implements ConverterInterface<ActivityDto, Activity>{
    @Override
    public Activity fromDto(ActivityDto dto) {
        return new Activity(
                dto.getId(),
                dto.getName(),
                dto.getActivityType(),
                dto.getSportEventTypeOpportunities(),
                dto.getStatus());
    }

    @Override
    public ActivityDto toDto(Activity model) {
        return new ActivityDto(
                model.getId(),
                model.getName(),
                model.getActivityType().getDisplayName(),
                model.getSportEventTypeOpportunities().getDisplayName(),
                model.getStatus().toString());
    }
}
