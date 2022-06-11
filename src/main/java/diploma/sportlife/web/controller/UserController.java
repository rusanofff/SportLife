package diploma.sportlife.web.controller;

import diploma.sportlife.converter.UserConverterImpl;
import diploma.sportlife.model.User;
import diploma.sportlife.service.user.UserService;
import diploma.sportlife.web.model.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService service;

    private final UserConverterImpl converter;

    public UserController(UserService service, UserConverterImpl converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/users")
    public List<UserDto> getUserList() {
        return service.readAll()
                      .stream()
                      .map(converter::toDto)
                      .collect(Collectors.toList());
    }

    @GetMapping("/login")
    public void login() {

    }

    @GetMapping("/user/{id}")
    public UserDto getUser(@PathVariable(name = "id") Integer id){
        User user = service.findById(id);
        return converter.toDto(user);
    }

    @GetMapping("/user")
    public UserDto getUserByEmail(@RequestParam String email) {
        return converter.toDto(service.findByEmail(email));
    }

    @PostMapping(path = "/user")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserDto insertUser(@Valid @RequestBody UserDto userDto) {
        User user = converter.fromDto(userDto);
        User insertedUser = service.insertUser(user);
        return converter.toDto(insertedUser);
    }

    @DeleteMapping("/user/{id}")
    public UserDto deleteUser(@PathVariable(name = "id") Integer id) {
        User deletedUser = service.deleteById(id);
        return converter.toDto(deletedUser);
    }

    @PutMapping(path = "/user/{id}")
    public UserDto putUser(@PathVariable(name = "id") Integer id,
                                         @Valid @RequestBody UserDto userDto) {
        User user = converter.fromDto(userDto);
        User updatedUser = service.putById(id, user);
        return converter.toDto(updatedUser);
    }
}
