package com.akoca.producterbasketballapi.resolver.fieldresolvers;

import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.service.PlayerService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamResolver implements GraphQLResolver<Team> {
    private final PlayerService playerService;

    public List<Player> getPlayers(Team team) {
        return playerService.getAllInTeam(team.getName());
    }
}
