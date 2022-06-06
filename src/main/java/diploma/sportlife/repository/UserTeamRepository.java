package diploma.sportlife.repository;

import diploma.sportlife.model.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeam, Integer> {
    List<UserTeam> findByUserId(Integer profile_id);

    List<UserTeam> findByTeamId(Integer team_id);

    UserTeam getByUserIdAndTeamId(Integer profile_id, Integer team_id);

    boolean existsByUserIdAndTeamId(Integer profile_id, Integer team_id);
}
