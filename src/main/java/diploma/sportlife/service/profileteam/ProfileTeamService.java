package diploma.sportlife.service.profileteam;

import diploma.sportlife.model.ProfileTeam;

import java.util.List;

public interface ProfileTeamService {
    List<ProfileTeam> readAll();

    ProfileTeam findById(Integer id);

    List<ProfileTeam> findByProfileId(Integer id);

    List<ProfileTeam> findByTeamId(Integer id);

    ProfileTeam insertProfileTeam(ProfileTeam profileTeam);

    ProfileTeam deleteById(Integer id);

    ProfileTeam putById(Integer id, ProfileTeam givenProfileSport);

    void assertProfileTeamExists(Integer id);
}
