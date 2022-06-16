package diploma.sportlife.service.team;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.notfound.TeamNotFoundException;
import diploma.sportlife.model.SportEvent;
import diploma.sportlife.model.Team;
import diploma.sportlife.repository.TeamRepository;
import diploma.sportlife.service.sportevent.SportEventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService{
    private final TeamRepository teamRepository;
    private final SportEventService sportEventService;

    public TeamServiceImpl(TeamRepository teamRepository, SportEventService sportEventService) {
        this.teamRepository = teamRepository;
        this.sportEventService = sportEventService;
    }

    @Override
    public List<Team> readAll(Integer id) {

        return new ArrayList<>(teamRepository.findBySportEvent(sportEventService.findById(id)));
    }


    @Override
    public Team findById(Integer id) {
        return teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    @Override
    public Team insertTeam(Team team) {
        SportEvent sportEvent = team.getSportEvent();
        Integer sportEventId = sportEvent.getId();
        sportEventService.assertSportEventExists(sportEventId);

        if (teamRepository.existsByNameAndSportEventId(team.getName(), team.getSportEvent().getId())){
            throw new EntityAlreadyExistsException();
        }
        return teamRepository.save(team);
    }

    @Override
    public Team putById(Integer id, Team givenTeamFromJson) {
        assertTeamExists(id);

        Integer sportEventIdFromJson = givenTeamFromJson.getSportEvent().getId();
        sportEventService.assertSportEventExists(sportEventIdFromJson);

        if (teamRepository.existsByNameAndSportEventId(
                givenTeamFromJson.getName(),
                sportEventIdFromJson)){
            throw new EntityAlreadyExistsException();
        }

        givenTeamFromJson.setId(id);
        return givenTeamFromJson;
    }

    @Override
    public Team deleteById(Integer id) {
        Team team = teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
        teamRepository.deleteById(id);
        return team;
    }

    @Override
    public void assertTeamExists(Integer id) {
        if (!teamRepository.existsById(id)){
            throw new TeamNotFoundException();
        }
    }
}
