package diploma.sportlife.converter;

import diploma.sportlife.model.SportEvent;
import diploma.sportlife.model.Team;
import diploma.sportlife.web.model.TeamDto;
import org.springframework.stereotype.Component;

@Component
public class TeamConverterImpl implements ConverterInterface<TeamDto, Team>{

    @Override
    public Team fromDto(TeamDto dto) {
        return new Team(
                dto.getId(),
                dto.getName(),
                SportEvent.builder().id(dto.getSportEventId()).build(),
                dto.getCount());
    }

    @Override
    public TeamDto toDto(Team model) {
        return new TeamDto(
                model.getId(),
                model.getName(),
                model.getSportEvent().getId(),
                model.getCount()
        );
    }
}
