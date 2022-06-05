package diploma.sportlife.service.userteam;

import diploma.sportlife.model.UserTeam;

import java.util.List;

public interface UserTeamService {
    List<UserTeam> readAll();

    UserTeam findById(Integer id);

    List<UserTeam> findByUserId(Integer id);

    List<UserTeam> findByTeamId(Integer id);

    UserTeam insertUserTeam(UserTeam userTeam);

    UserTeam deleteById(Integer id);

    UserTeam putById(Integer id, UserTeam givenProfileSport);

    void assertUserTeamExists(Integer id);
}
