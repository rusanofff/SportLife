package diploma.sportlife.web.controller;

import diploma.sportlife.converter.TeamConverterImpl;
import diploma.sportlife.model.Team;
import diploma.sportlife.model.User;
import diploma.sportlife.service.team.TeamService;
import diploma.sportlife.web.model.TeamDto;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private final TeamService service;

    private final TeamConverterImpl converter;

    public TeamController(TeamService service, TeamConverterImpl converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeamList(){
        return service.readAll()
            .stream()
            .map(converter::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/team")
    public TeamDto getTeam(@PathVariable(name = "id") Integer id){
        Team team = service.findById(id);
        return converter.toDto(team);
    }

    @PostMapping(path = "/team")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TeamDto insertTeam(@Valid @RequestBody TeamDto teamDto) {
        Team team = converter.fromDto(teamDto);
        Team insertedTeam = service.insertTeam(team);
        return converter.toDto(insertedTeam);
    }

    @DeleteMapping("/team/{id}")
    public TeamDto deleteTeam(@PathVariable(name = "id") Integer id) {
        Team deletedTeam = service.deleteById(id);
        return converter.toDto(deletedTeam);
    }

    @PutMapping(path = "/user/{id}")
    public TeamDto putTeam(@PathVariable(name = "id") Integer id,
                           @Valid @RequestBody TeamDto teamDto) {
        Team team = converter.fromDto(teamDto);
        Team updatedTeam = service.putById(id, team);
        return converter.toDto(updatedTeam);
    }

}
