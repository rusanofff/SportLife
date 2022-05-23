package diploma.sportlife.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Date;

@Value
public class ProfileDto {
    @Null
    Integer id;

    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    String surname;

    @NotNull
    @NotBlank
    String password;

    @NotNull
    @NotBlank
    String email;

    @NotNull
    @Length(min = 10)
    @NotBlank
    String phone;

    String description;

    @NotNull
    @NotBlank
    String town;

    @NotNull
    Date dateOfBirth;

    Byte[] image;

    @JsonCreator
    public ProfileDto(@JsonProperty("id") Integer id,
                       @JsonProperty("name") String name,
                       @JsonProperty("surname") String surname,
                       @JsonProperty("password") String password,
                       @JsonProperty("email") String email,
                       @JsonProperty("phone") String phone,
                       @JsonProperty("description") String description,
                       @JsonProperty("town") String town,
                       @JsonProperty("dateOfBirth") Date dateOfBirth,
                       @JsonProperty("image") Byte[] image) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.description = description;
        this.town = town;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }
}
