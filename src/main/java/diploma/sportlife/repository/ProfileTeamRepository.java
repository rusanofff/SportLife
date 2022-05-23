package diploma.sportlife.repository;

import diploma.sportlife.model.ProfileTeam;
import diploma.sportlife.model.ProfileTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileTeamRepository extends JpaRepository<ProfileTeam, Integer> {
    List<ProfileTeam> findByProfile_Id(Integer profile_id);

    List<ProfileTeam> findByTeam_id(Integer team_id);

    ProfileTeam getByProfile_IdAndTeam_Id(Integer profile_id, Integer team_id);

    boolean existsByProfile_IdAndTeam_Id(Integer profile_id, Integer team_id);
}
