package com.akoca.producterbasketballapi.service;

import com.akoca.producterbasketballapi.dto.TeamDto;
import com.akoca.producterbasketballapi.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> getAll();

    Optional<Team> findByName(String teamName);

    Optional<Team> findById(Long teamId);

    Team addNewTeam(TeamDto teamDto);

    Team deleteTeam(Long teamId);
}
