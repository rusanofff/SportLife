package diploma.sportlife.converter;

import diploma.sportlife.model.User;
import diploma.sportlife.model.UserTeam;
import diploma.sportlife.model.Team;
import diploma.sportlife.web.model.UserTeamDto;

public class UserTeamConverterImpl implements ConverterInterface<UserTeamDto, UserTeam>{
    @Override
    public UserTeam fromDto(UserTeamDto dto) {
        return new UserTeam(
                dto.getId(),
                User.builder().id(dto.getProfileId()).build(),
                Team.builder().id(dto.getTeamId()).build()
        );
    }

    @Override
    public UserTeamDto toDto(UserTeam model) {
        return new UserTeamDto(
                model.getId(),
                model.getUser().getId(),
                model.getTeam().getId()
        );
    }
}
