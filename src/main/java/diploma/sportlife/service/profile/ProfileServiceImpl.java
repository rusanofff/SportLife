package diploma.sportlife.service.profile;

import diploma.sportlife.exception.EntityAlreadyExistsException;
import diploma.sportlife.exception.MismatchedException;
import diploma.sportlife.exception.notfound.ProfileNotFoundException;
import diploma.sportlife.model.Profile;
import diploma.sportlife.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProfileServiceImpl implements ProfileService{
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> readAll() {
        return new ArrayList<>(profileRepository.findAll());
    }

    @Override
    public Profile findById(Integer id) {
        return profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    public Profile insertProfile(Profile profile) {
        if (profileRepository.existsParticipantByFullNameAndDateOfBirth(profile.getName(),
                profile.getSurname(), profile.getDateOfBirth())){
            throw new EntityAlreadyExistsException();
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile deleteById(Integer id) {
        Profile deleteProfile = profileRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
        profileRepository.deleteById(id);
        return deleteProfile;
    }

    @Override
    public Profile putById(Integer id, Profile givenProfileFromJson) {
        assertProfileExists(id);
        givenProfileFromJson.setId(id);
        Profile profileUnique = profileRepository.findByFullNameAndDateOfBirth(
                givenProfileFromJson.getName(), givenProfileFromJson.getSurname(),
        givenProfileFromJson.getDateOfBirth());

        if (profileUnique != null && !Objects.equals(profileUnique.getId(), id)){
            throw new MismatchedException();
        }
        return profileRepository.save(givenProfileFromJson);
    }

    @Override
    public void assertProfileExists(Integer id) {
        if (!profileRepository.existsById(id)){
            throw new ProfileNotFoundException();
        }
    }
}
