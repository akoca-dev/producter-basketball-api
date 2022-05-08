package com.akoca.producterbasketballapi.resolver;

import com.akoca.producterbasketballapi.dto.PlayerDto;
import com.akoca.producterbasketballapi.dto.TeamDto;
import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.service.PlayerService;
import com.akoca.producterbasketballapi.service.TeamService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@Validated
@Component
@RequiredArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

    private final PlayerService playerService;
    private final TeamService teamService;

    public Team addTeam(@Valid TeamDto teamDto) {
        return teamService.addNewTeam(teamDto);
    }

    public Team deleteTeam(@Positive Long teamId) {
        return teamService.deleteTeam(teamId);
    }

    public Player addPlayer(@Valid PlayerDto playerDto) {
        return playerService.addNewPlayer(playerDto);
    }

    public Player deletePLayer(@Positive Long playerId) {
        return playerService.deletePlayerById(playerId);
    }
}
