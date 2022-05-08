package com.akoca.producterbasketballapi.repo;

import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.entity.Team;
import com.akoca.producterbasketballapi.enums.Position;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class PlayerRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    Team team1;

    @BeforeEach
    void setUp() {
        team1 = new Team();
        team1.setName("team1");
        teamRepository.save(team1);
    }

    @AfterEach
    void tearDown() {
        playerRepository.deleteAll();
        teamRepository.deleteAll();
    }

    @Test
    void whenSaved_thenFindAllReturnsSize() {

        Player player1 = new Player();
        player1.setName("PlayerName");
        player1.setSurname("PlayerSurname");
        player1.setPosition(Position.C);
        player1.setTeam(team1);
        playerRepository.save(player1);

        Player player2 = new Player();
        player2.setName("PlayerName");
        player2.setSurname("PlayerSurname");
        player2.setPosition(Position.C);
        player2.setTeam(team1);
        playerRepository.save(player2);

        assertEquals(2, playerRepository.findAll().size());
    }

    @Test
    void whenSaved_thenFindByTeamNameReturns() {

        Player newPlayer = new Player();

        newPlayer.setName("PlayerName");
        newPlayer.setSurname("PlayerSurname");
        newPlayer.setPosition(Position.C);
        newPlayer.setTeam(team1);

        newPlayer = playerRepository.save(newPlayer);

        assertEquals(
                1,
                playerRepository.findAllByTeamNameEquals(team1.getName()).size());

        Player playerOnDb = playerRepository.findAllByTeamNameEquals(team1.getName()).get(0);

        assertEquals(newPlayer.getId(), playerOnDb.getId());
    }

    @Test
    void whenNameIsNull_thenThrowsException() {

        Player newPlayer = new Player();

        newPlayer.setSurname("PlayerSurname");
        newPlayer.setPosition(Position.C);
        newPlayer.setTeam(team1);

        assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    playerRepository.save(newPlayer);
                }
        );
    }

    @Test
    void whenPositionIsNull_thenThrowsException() {

        Player newPlayer = new Player();

        newPlayer.setName("PlayerName");
        newPlayer.setSurname("PlayerSurname");
        newPlayer.setTeam(team1);

        assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    playerRepository.save(newPlayer);
                }
        );
    }
}