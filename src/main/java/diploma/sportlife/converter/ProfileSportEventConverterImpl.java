package diploma.sportlife.converter;

import diploma.sportlife.model.SportEvent;
import diploma.sportlife.model.Profile;
import diploma.sportlife.model.ProfileSportEvent;
import diploma.sportlife.web.model.ProfileSportEventDto;
import org.springframework.stereotype.Component;

@Component
public class ProfileSportEventConverterImpl implements ConverterInterface<ProfileSportEventDto, ProfileSportEvent>{
    @Override
    public ProfileSportEvent fromDto(ProfileSportEventDto dto) {
        return new ProfileSportEvent(
                dto.getId(),
                Profile.builder().id(dto.getProfileId()).build(),
                SportEvent.builder().id(dto.getSportEventId()).build()
        );
    }

    @Override
    public ProfileSportEventDto toDto(ProfileSportEvent model) {
        return new ProfileSportEventDto(
                model.getId(),
                model.getProfile().getId(),
                model.getSportEvent().getId()
        );
    }
}
