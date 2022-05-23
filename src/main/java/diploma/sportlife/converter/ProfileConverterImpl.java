package diploma.sportlife.converter;

import diploma.sportlife.model.Activity;
import diploma.sportlife.model.Profile;
import diploma.sportlife.web.model.ActivityDto;
import diploma.sportlife.web.model.ProfileDto;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverterImpl implements ConverterInterface<ProfileDto, Profile>{
    @Override
    public Profile fromDto(ProfileDto dto) {
        return new Profile(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getDescription(),
                dto.getTown(),
                dto.getDateOfBirth(),
                dto.getImage());
    }

    @Override
    public ProfileDto toDto(Profile model) {
        return new ProfileDto(
                model.getId(),
                model.getName(),
                model.getSurname(),
                model.getPassword(),
                model.getEmail(),
                model.getPhone(),
                model.getDescription(),
                model.getTown(),
                model.getDateOfBirth(),
                model.getImage());
    }
}
