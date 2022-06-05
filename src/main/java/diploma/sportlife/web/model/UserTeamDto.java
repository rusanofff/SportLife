package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Value
public class UserTeamDto {
    @Null
    Integer id;

    @NotNull
    Integer profileId;

    @NotNull
    Integer teamId;

    @JsonCreator
    public UserTeamDto(@JsonProperty("id") Integer id,
                       @JsonProperty("profileId") Integer profileId,
                       @JsonProperty("teamId") Integer teamId) {
        this.id = id;
        this.profileId = profileId;
        this.teamId = teamId;
    }
}
