package com.akoca.producterbasketballapi.controller;

import com.akoca.producterbasketballapi.dto.TeamDto;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teams")
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> allTeams = teamService.getAll();

        return ResponseEntity.ok(allTeams);
    }

    @PostMapping
    public ResponseEntity<String> addTeam(@Valid @RequestBody TeamDto teamDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Team data is invalid");
        }

        Optional<Team> addedTeamOpt = Optional.of(teamService.addNewTeam(teamDto));

        return addedTeamOpt
                .map(team -> ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("New team added.Team id: " + team.getId()))
                .orElseGet(() -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to add new team.Unexpected error occurred"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@Positive @PathVariable Long id, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Team id invalid");
        }

        Optional<Team> deletedTeamOpt = Optional.of(teamService.deleteTeam(id));

        return deletedTeamOpt
                .map(team -> ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Team with id: " + id + " deleted"))
                .orElseGet(() -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to delete team with id: " + id));
    }
}
