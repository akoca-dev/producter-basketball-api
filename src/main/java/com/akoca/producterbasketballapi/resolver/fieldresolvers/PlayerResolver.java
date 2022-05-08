package com.akoca.producterbasketballapi.resolver.fieldresolvers;

import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.exception.unchecked.TeamOpFailureException;
import com.akoca.producterbasketballapi.service.TeamService;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerResolver implements GraphQLResolver<Player> {
    private final TeamService teamService;

    public Team getTeam(Player player) {
        return teamService.findById(player.getTeam().getId()).orElseThrow(() -> new TeamOpFailureException("Team not found"));
    }
}
