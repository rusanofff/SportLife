package diploma.sportlife.service.team;

import diploma.sportlife.model.Team;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface TeamService {
    List<Team> readAll(Integer id);

    Team findById(Integer id);

    Team insertTeam(Team team);

    Team putById(Integer id, Team givenTeamFromJson);

    Team deleteById(Integer id);

    void assertTeamExists(Integer id);

}
