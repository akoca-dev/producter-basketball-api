package com.akoca.producterbasketballapi.service.impl;

import com.akoca.producterbasketballapi.dto.PlayerDto;
import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.exception.unchecked.PlayerOpFailureException;
import com.akoca.producterbasketballapi.repo.PlayerRepository;
import com.akoca.producterbasketballapi.service.PlayerService;
import com.akoca.producterbasketballapi.service.TeamService;
import com.akoca.producterbasketballapi.util.AppConstants;
import com.akoca.producterbasketballapi.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    @Override
    public List<Player> getAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> getAllInTeam(String teamName) {
        return playerRepository.findAllByTeamNameEquals(teamName);
    }

    @Override
    @Transactional
    public Player addNewPlayer(PlayerDto playerDto) {
        Optional<Player> playerOptional = EntityUtils.playerFromDto(playerDto);

        if(playerOptional.isEmpty()) {
            log.error("Player dto contains invalid data: {}", playerDto);
            throw new PlayerOpFailureException("Player dto contains invalid data: " + playerDto);
        }
        
        Optional<Team> teamOptional = teamService.findByName(playerDto.getTeamName());

        if(teamOptional.isEmpty()) {
            log.error("Specified team does not exist");
            throw new PlayerOpFailureException("Specified team does not exist: " + playerDto.getTeamName());
        }

        Team teamOfPlayer = teamOptional.get();
        if(teamOfPlayer.getPlayers().size() >= AppConstants.MAX_PLAYERS_IN_TEAM) {
            log.error("Specified team has reached max players.Can not add new player");
            throw new PlayerOpFailureException("Specified team has reached max players.Can not add new player");
        }

        Player newPlayer = playerOptional.get();
        newPlayer.setTeam(teamOfPlayer);

        newPlayer = playerRepository.save(newPlayer);

        return newPlayer;
    }

    @Override
    public Player deletePlayerById(Long playerId) {
        Optional<Player> playerOnDbOpt = playerRepository.findById(playerId);

        if (playerOnDbOpt.isEmpty()) {
            log.error("Player with id " + playerId + " does not exist");
            throw new PlayerOpFailureException("Player with id " + playerId + " does not exist");
        }

        playerRepository.deleteById(playerId);

        return playerOnDbOpt.get();
    }
}
