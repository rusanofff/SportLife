package diploma.sportlife.web.controller;

import diploma.sportlife.converter.TeamConverterImpl;
import diploma.sportlife.converter.UserConverterImpl;
import diploma.sportlife.converter.UserTeamConverterImpl;
import diploma.sportlife.model.UserTeam;
import diploma.sportlife.service.userteam.UserTeamService;
import diploma.sportlife.web.model.TeamDto;
import diploma.sportlife.web.model.UserDto;
import diploma.sportlife.web.model.UserTeamDto;
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
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserTeamController {
    private final UserTeamService service;

    private final UserTeamConverterImpl userTeamConverter;

    private final UserConverterImpl userConverter;

    private final TeamConverterImpl teamConverter;

    public UserTeamController(UserTeamService service, UserTeamConverterImpl userTeamConverter,
                              UserConverterImpl userConverter, TeamConverterImpl teamConverter) {
        this.service = service;
        this.userTeamConverter = userTeamConverter;
        this.userConverter = userConverter;
        this.teamConverter = teamConverter;
    }

    @GetMapping("/user-teams")
    public List<UserTeamDto> getUserTeamList(){
        return service.readAll()
                      .stream()
                      .map(userTeamConverter::toDto)
                      .collect(Collectors.toList());
    }

    @GetMapping("/user-team/{id}")
    public UserTeamDto getUserTeam(@PathVariable(name = "id") Integer id){
        UserTeam userTeam = service.findById(id);
        return userTeamConverter.toDto(userTeam);
    }

    @GetMapping(path = "/user-team/user/{id}")
    public List<UserDto> getUserTeamByUserId(@PathVariable(name = "id") Integer id) {
        return service.findByUserId(id)
                      .stream()
                      .map(userTeam -> userConverter.toDto(userTeam.getUser()))
                      .collect(Collectors.toList());
    }

    @GetMapping(path = "/user-team/team/{id}")
    public List<TeamDto> getUserTeamByTeamId(@PathVariable(name = "id") Integer id) {
        return service.findByTeamId(id)
                      .stream()
                      .map(userTeam -> teamConverter.toDto(userTeam.getTeam()))
                      .collect(Collectors.toList());
    }


    @PostMapping(path = "/user-team")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UserTeamDto insertUserTeam(@Valid @RequestBody UserTeamDto userTeamDto) {
        UserTeam userTeam = userTeamConverter.fromDto(userTeamDto);
        UserTeam insertUserTeam = service.insertUserTeam(userTeam);
        return userTeamConverter.toDto(insertUserTeam);
    }

    @DeleteMapping("/user-team/{id}")
    public UserTeamDto deleteUserTeam(@PathVariable(name = "id") Integer id) {
        UserTeam deletedUserTeam = service.deleteById(id);
        return userTeamConverter.toDto(deletedUserTeam);
    }

    @PutMapping(path = "/user-team/{id}")
    public UserTeamDto putUserTeam(@PathVariable(name = "id") Integer id,
                                         @Valid @RequestBody UserTeamDto userTeamDto) {
        UserTeam userTeam = userTeamConverter.fromDto(userTeamDto);
        UserTeam updatedUserTeam = service.putById(id, userTeam);
        return userTeamConverter.toDto(updatedUserTeam);
    }
}
