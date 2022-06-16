package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Value
public class UserSportEventDto {
    @Null
    Integer id;

    @NotNull
    Integer userId;

    @NotNull
    Integer sportEventId;

    @JsonCreator
    public UserSportEventDto(@JsonProperty("id") Integer id,
                             @JsonProperty("userId") Integer userId,
                             @JsonProperty("sportEventId") Integer sportEventId) {
        this.id = id;
        this.userId = userId;
        this.sportEventId = sportEventId;
    }
}
