package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Value
public class ProfileSportEventDto {
    @Null
    Integer id;

    @NotNull
    Integer profileId;

    @NotNull
    Integer sportEventId;

    @JsonCreator
    public ProfileSportEventDto(@JsonProperty("id") Integer id,
                                @JsonProperty("disciplineId") Integer profileId,
                                @JsonProperty("sportEventId") Integer sportEventId) {
        this.id = id;
        this.profileId = profileId;
        this.sportEventId = sportEventId;
    }
}
