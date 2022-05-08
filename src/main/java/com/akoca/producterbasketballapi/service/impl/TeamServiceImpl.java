package com.akoca.producterbasketballapi.service.impl;

import com.akoca.producterbasketballapi.dto.TeamDto;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.exception.unchecked.TeamOpFailureException;
import com.akoca.producterbasketballapi.repo.TeamRepository;
import com.akoca.producterbasketballapi.service.TeamService;
import com.akoca.producterbasketballapi.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public List<Team> getAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findByName(String teamName) {
        return teamRepository.findByNameEquals(teamName);
    }

    @Override
    public Optional<Team> findById(Long teamId) {
        return teamRepository.findById(teamId);
    }

    @Override
    public Team addNewTeam(TeamDto teamDto) {
        Optional<Team> newTeamOpt = EntityUtils.teamFromDto(teamDto);

        if (newTeamOpt.isEmpty()) {
            log.error("Team dto contains invalid data: {}", teamDto);
            throw new TeamOpFailureException("Team dto contains invalid data: " + teamDto);
        }

        return teamRepository.save(newTeamOpt.get());
    }

    @Override
    public Team deleteTeam(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);

        if(teamOptional.isEmpty()) {
            log.error("Team with id " + teamId + " does not exist");
            throw new TeamOpFailureException("Team with id " + teamId + " does not exist");
        }

        teamRepository.deleteById(teamId);

        return teamOptional.get();
    }
}
