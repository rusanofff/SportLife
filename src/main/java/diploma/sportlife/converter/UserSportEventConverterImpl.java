package diploma.sportlife.converter;

import diploma.sportlife.model.SportEvent;
import diploma.sportlife.model.User;
import diploma.sportlife.model.UserSportEvent;
import diploma.sportlife.web.model.UserSportEventDto;
import org.springframework.stereotype.Component;

@Component
public class UserSportEventConverterImpl implements ConverterInterface<UserSportEventDto, UserSportEvent>{
    @Override
    public UserSportEvent fromDto(UserSportEventDto dto) {
        return new UserSportEvent(
                dto.getId(),
                User.builder().id(dto.getUserId()).build(),
                SportEvent.builder().id(dto.getSportEventId()).build()
        );
    }

    @Override
    public UserSportEventDto toDto(UserSportEvent model) {
        return new UserSportEventDto(
                model.getId(),
                model.getUser().getId(),
                model.getSportEvent().getId()
        );
    }
}
