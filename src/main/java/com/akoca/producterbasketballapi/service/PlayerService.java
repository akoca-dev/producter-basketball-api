package com.akoca.producterbasketballapi.service;

import com.akoca.producterbasketballapi.dto.PlayerDto;
import com.akoca.producterbasketballapi.entity.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getAll();

    List<Player> getAllInTeam(String teamName);

    Player addNewPlayer(PlayerDto playerDto);

    Player deletePlayerById(Long playerId);
}
