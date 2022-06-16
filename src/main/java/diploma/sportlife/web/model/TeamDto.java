package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Value
public class  TeamDto {
    @Null
    Integer id;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    Integer sportEventId;

    @NotNull
    Integer count;

    @JsonCreator
    public TeamDto(@JsonProperty("id") Integer id,
                   @JsonProperty("name") String name,
                   @JsonProperty("sportEventId") Integer sportEventId,
                   @JsonProperty("count") Integer count) {
        this.id = id;
        this.name = name;
        this.sportEventId = sportEventId;
        this.count = count;
    }
}
