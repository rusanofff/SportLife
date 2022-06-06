package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import diploma.sportlife.model.modelenum.ActivityType;
import diploma.sportlife.model.modelenum.EntityStatus;
import diploma.sportlife.model.modelenum.SportEventTypeOpportunities;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Value
public class ActivityDto {
    @Null
    Integer id;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    ActivityType activityType;

    @NotNull
    SportEventTypeOpportunities sportEventTypeOpportunities;

    @JsonCreator
    public ActivityDto(@JsonProperty("id") Integer id,
                         @JsonProperty("name") String name,
                         @JsonProperty("activityType") String activityType,
                         @JsonProperty("sportEventTypeOpportunities") String sportEventTypeOpportunities) {
        this.id = id;
        this.name = name;
        this.activityType = ActivityType.createWithValue(activityType);
        this.sportEventTypeOpportunities = SportEventTypeOpportunities.createWithValue(sportEventTypeOpportunities);
    }
}
