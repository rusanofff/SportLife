package diploma.sportlife.converter;

import diploma.sportlife.model.Profile;
import diploma.sportlife.model.ProfileTeam;
import diploma.sportlife.model.Team;
import diploma.sportlife.web.model.ProfileTeamDto;

public class ProfileTeamConverterImpl implements ConverterInterface<ProfileTeamDto, ProfileTeam>{
    @Override
    public ProfileTeam fromDto(ProfileTeamDto dto) {
        return new ProfileTeam(
                dto.getId(),
                Profile.builder().id(dto.getProfileId()).build(),
                Team.builder().id(dto.getTeamId()).build()
        );
    }

    @Override
    public ProfileTeamDto toDto(ProfileTeam model) {
        return new ProfileTeamDto(
                model.getId(),
                model.getProfile().getId(),
                model.getTeam().getId()
        );
    }
}
