package diploma.sportlife.repository;

import diploma.sportlife.model.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeam, Integer> {
    List<UserTeam> findByProfile_Id(Integer profile_id);

    List<UserTeam> findByTeam_id(Integer team_id);

    UserTeam getByProfile_IdAndTeam_Id(Integer profile_id, Integer team_id);

    boolean existsByProfile_IdAndTeam_Id(Integer profile_id, Integer team_id);
}
