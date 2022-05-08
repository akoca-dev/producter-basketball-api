package com.akoca.producterbasketballapi.resolver;

import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.service.PlayerService;
import com.akoca.producterbasketballapi.service.TeamService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final PlayerService playerService;
    private final TeamService teamService;

    public List<Player> listPlayers() {
        return playerService.getAll();
    }

    public List<Team> listTeams() {
        return teamService.getAll();
    }
}
