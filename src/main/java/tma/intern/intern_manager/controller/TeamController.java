package tma.intern.intern_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tma.intern.intern_manager.dto.team.ITeamQuantity;
import tma.intern.intern_manager.dto.team.TeamQuantity;
import tma.intern.intern_manager.dto.team.TeamRequestDto;
import tma.intern.intern_manager.dto.team.TeamDetailDto;
import tma.intern.intern_manager.service.team.TeamService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/teams")
public class TeamController {
    @Autowired
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    ResponseEntity<Page<ITeamQuantity>> getListTeam(
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value ="page", defaultValue = "0") int page,
            @RequestParam(value = "sort", defaultValue = "updatedAt") String sort,
            @RequestParam(value = "direction", defaultValue = "desc") String direction
    ) {
        Sort.Direction sortingDirection = direction.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest pr = PageRequest.of(page, limit, Sort.by(sortingDirection, sort));
        Page<ITeamQuantity> getResponse = teamService.getListTeam(pr);
        return ResponseEntity.ok(getResponse);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<TeamQuantity> getTeamById(@PathVariable(value = "id")UUID id) {
        TeamQuantity response = teamService.getTeamById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<TeamDetailDto> addTeam(@RequestBody TeamRequestDto request) {
        TeamDetailDto response = teamService.addTeam(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<TeamDetailDto> updateTeam(@RequestBody TeamRequestDto request, @PathVariable(value = "id") UUID id) {
        TeamDetailDto response = teamService.updateTeam(request, id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/{id}")
    ResponseEntity<String> deleteTeam(@PathVariable(value = "id") UUID id) {
        String response = teamService.deleteTeam(id);
        return ResponseEntity.ok(response);
    }
}
