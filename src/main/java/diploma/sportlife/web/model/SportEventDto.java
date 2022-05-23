package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import diploma.sportlife.model.modelenum.SportEventType;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Value
public class SportEventDto {
    @Null
    Integer id;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    SportEventType sportEventType;

    @NotNull
    Timestamp startDate;

    String description;

    @NotNull
    Integer profileId;

    @NotNull
    @NotBlank
    String town;

    @NotNull
    @NotBlank
    String longitude;

    @NotNull
    @NotBlank
    String latitude;

    @NotNull
    Integer activityId;


    Integer minCount;

    @NotNull
    Integer maxCount;

    @JsonCreator
    public SportEventDto(@JsonProperty("id") Integer id,
                         @JsonProperty("name") String name,
                         @JsonProperty("startDate") Timestamp startDate,
                         @JsonProperty("sportEventType") String eventType,
                         @JsonProperty("profileId") Integer profileId,
                         @JsonProperty("town") String town,
                         @JsonProperty("longitude") String longitude,
                         @JsonProperty("latitude") String latitude,
                         @JsonProperty("activityId") Integer activityId,
                         @JsonProperty("description") String description,
                         @JsonProperty("minCount") Integer minCount,
                         @JsonProperty("maxCount") Integer maxCount){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.sportEventType = SportEventType.createWithValue(eventType);
        this.profileId = profileId;
        this.town = town;
        this.longitude = longitude;
        this.latitude = latitude;
        this.activityId = activityId;
        this.description = description;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }
}
