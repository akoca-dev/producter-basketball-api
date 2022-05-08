package com.akoca.producterbasketballapi.util;

import com.akoca.producterbasketballapi.dto.PlayerDto;
import com.akoca.producterbasketballapi.dto.TeamDto;
import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.enums.Position;
import com.akoca.producterbasketballapi.exception.unchecked.InvalidDataException;

import java.util.Optional;

public final class EntityUtils {
    private EntityUtils() {}

    public static Optional<Player> playerFromDto(PlayerDto playerDto) {

        if(!Position.isValidPosition(playerDto.getPosition())) {
            return Optional.empty();
        }

        Position playerPosition = Position
                .fromStringValue(playerDto.getPosition())
                .orElseThrow(() -> new InvalidDataException("Position Value Invalid"));

        Player newPlayer = new Player();

        newPlayer.setName(playerDto.getName());
        newPlayer.setSurname(playerDto.getSurname());
        newPlayer.setPosition(playerPosition);

        return Optional.of(newPlayer);
    }

    public static Optional<Team> teamFromDto(TeamDto teamDto) {
        Team newTeam = new Team();

        newTeam.setName(teamDto.getName());

        return Optional.of(newTeam);
    }
}
