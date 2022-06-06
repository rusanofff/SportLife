package diploma.sportlife.service.userteam;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.MismatchedException;
import diploma.sportlife.exception.notfound.UserTeamNotFoundException;
import diploma.sportlife.model.UserTeam;
import diploma.sportlife.repository.UserTeamRepository;
import diploma.sportlife.service.team.TeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserTeamServiceImpl implements UserTeamService {
    private final UserTeamRepository userTeamRepository;
    private final diploma.sportlife.service.user.UserService userService;
    private final TeamService teamService;

    public UserTeamServiceImpl(UserTeamRepository userTeamRepository, diploma.sportlife.service.user.UserService userService, TeamService teamService) {
        this.userTeamRepository = userTeamRepository;
        this.userService = userService;
        this.teamService = teamService;
    }

    @Override
    public List<UserTeam> readAll() {
        return new ArrayList<>(userTeamRepository.findAll());
    }

    @Override
    public UserTeam findById(Integer id) {
        return userTeamRepository.findById(id).orElseThrow(UserTeamNotFoundException::new);
    }

    @Override
    public List<UserTeam> findByUserId(Integer id) {
        userService.assertUserExists(id);
        return new ArrayList<>(userTeamRepository.findByUserId(id));
    }

    @Override
    public List<UserTeam> findByTeamId(Integer id) {
        teamService.assertTeamExists(id);
        return new ArrayList<>(userTeamRepository.findByTeamId(id));
    }

    @Override
    public UserTeam insertUserTeam(UserTeam userTeam) {
        Integer profileId = userTeam.getUser().getId();
        Integer teamId = userTeam.getTeam().getId();

        userService.assertUserExists(profileId);
        teamService.assertTeamExists(teamId);

        if (userTeamRepository.existsByUserIdAndTeamId(profileId, teamId)){
            throw new EntityAlreadyExistsException();
        }
        return userTeamRepository.save(userTeam);
    }

    @Override
    public UserTeam deleteById(Integer id) {
        UserTeam userTeam = userTeamRepository.findById(id)
                                              .orElseThrow(UserTeamNotFoundException::new);
        userTeamRepository.deleteById(id);
        return userTeam;
    }

    @Override
    public UserTeam putById(Integer id, UserTeam givenUserTeam) {
        assertUserTeamExists(id);

        Integer profileId = givenUserTeam.getUser().getId();
        userService.assertUserExists(profileId);

        Integer teamId = givenUserTeam.getTeam().getId();
        teamService.assertTeamExists(teamId);

        UserTeam userTeam = userTeamRepository.getByUserIdAndTeamId(profileId, teamId);

        if (userTeam != null && !Objects.equals(userTeam.getId(), id)){
            throw new MismatchedException();
        }

        givenUserTeam.setId(id);
        return userTeamRepository.save(givenUserTeam);
    }

    @Override
    public void assertUserTeamExists(Integer id) {
        if(!userTeamRepository.existsById(id)){
            throw new UserTeamNotFoundException();
        }
    }
}
