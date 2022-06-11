package diploma.sportlife.service.user;

import diploma.sportlife.model.User;

import java.util.List;

public interface UserService {
    List<User> readAll();

    User findById(Integer id);

    User insertUser(User User);

    User deleteById(Integer id);

    User putById(Integer id, User givenUserFromJson);

    void assertUserExists(Integer id);

    User findByEmail(String email);
}
