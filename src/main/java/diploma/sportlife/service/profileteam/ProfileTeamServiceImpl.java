package diploma.sportlife.service.profileteam;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.MismatchedException;
import diploma.sportlife.exception.notfound.ProfileTeamNotFoundException;
import diploma.sportlife.model.ProfileTeam;
import diploma.sportlife.repository.ProfileTeamRepository;
import diploma.sportlife.service.profile.ProfileService;
import diploma.sportlife.service.team.TeamService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProfileTeamServiceImpl implements ProfileTeamService{
    private final ProfileTeamRepository profileTeamRepository;
    private final ProfileService profileService;
    private final TeamService teamService;

    public ProfileTeamServiceImpl(ProfileTeamRepository profileTeamRepository, ProfileService profileService, TeamService teamService) {
        this.profileTeamRepository = profileTeamRepository;
        this.profileService = profileService;
        this.teamService = teamService;
    }

    @Override
    public List<ProfileTeam> readAll() {
        return new ArrayList<>(profileTeamRepository.findAll());
    }

    @Override
    public ProfileTeam findById(Integer id) {
        return profileTeamRepository.findById(id).orElseThrow(ProfileTeamNotFoundException::new);
    }

    @Override
    public List<ProfileTeam> findByProfileId(Integer id) {
        profileService.assertProfileExists(id);
        return new ArrayList<>(profileTeamRepository.findByProfile_Id(id));
    }

    @Override
    public List<ProfileTeam> findByTeamId(Integer id) {
        teamService.assertTeamExists(id);
        return new ArrayList<>(profileTeamRepository.findByTeam_id(id));
    }

    @Override
    public ProfileTeam insertProfileTeam(ProfileTeam profileTeam) {
        Integer profileId = profileTeam.getProfile().getId();
        Integer teamId = profileTeam.getTeam().getId();

        profileService.assertProfileExists(profileId);
        teamService.assertTeamExists(teamId);

        if (profileTeamRepository.existsByProfile_IdAndTeam_Id(profileId, teamId)){
            throw new EntityAlreadyExistsException();
        }
        return profileTeamRepository.save(profileTeam);
    }

    @Override
    public ProfileTeam deleteById(Integer id) {
        ProfileTeam profileTeam = profileTeamRepository.findById(id)
                .orElseThrow(ProfileTeamNotFoundException::new);
        profileTeamRepository.deleteById(id);
        return profileTeam;
    }

    @Override
    public ProfileTeam putById(Integer id, ProfileTeam givenProfileTeam) {
        assertProfileTeamExists(id);

        Integer profileId = givenProfileTeam.getProfile().getId();
        profileService.assertProfileExists(profileId);

        Integer teamId = givenProfileTeam.getTeam().getId();
        teamService.assertTeamExists(teamId);

        ProfileTeam profileTeam = profileTeamRepository.getByProfile_IdAndTeam_Id(profileId, teamId);

        if (profileTeam != null && !Objects.equals(profileTeam.getId(), id)){
            throw new MismatchedException();
        }

        givenProfileTeam.setId(id);
        return profileTeamRepository.save(givenProfileTeam);
    }

    @Override
    public void assertProfileTeamExists(Integer id) {
        if(!profileTeamRepository.existsById(id)){
            throw new ProfileTeamNotFoundException();
        }
    }
}
