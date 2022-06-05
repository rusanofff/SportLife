package diploma.sportlife.converter;

import diploma.sportlife.model.User;
import diploma.sportlife.web.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverterImpl implements ConverterInterface<UserDto, User>{
    @Override
    public User fromDto(UserDto dto) {
        return new User(
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
    public UserDto toDto(User model) {
        return new UserDto(
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
