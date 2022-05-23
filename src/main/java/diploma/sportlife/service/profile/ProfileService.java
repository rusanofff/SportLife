package diploma.sportlife.service.profile;

import diploma.sportlife.model.Profile;

import java.util.List;

public interface ProfileService {
    List<Profile> readAll();

    Profile findById(Integer id);

    Profile insertProfile(Profile Profile);

    Profile deleteById(Integer id);

    Profile putById(Integer id, Profile givenProfileFromJson);

    void assertProfileExists(Integer id);
}
