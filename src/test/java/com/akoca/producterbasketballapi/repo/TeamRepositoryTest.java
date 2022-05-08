package com.akoca.producterbasketballapi.repo;

import com.akoca.producterbasketballapi.entity.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class TeamRepositoryTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        playerRepository.deleteAll();
        teamRepository.deleteAll();
    }

    @Test
    void whenSaved_thenFindAllReturnsSize() {

        Team team1 = new Team();
        team1.setName("Team1");
        teamRepository.save(team1);

        Team team2 = new Team();
        team2.setName("Team2");
        teamRepository.save(team2);

        Team team3 = new Team();
        team3.setName("Team3");
        teamRepository.save(team3);

        assertEquals(3, teamRepository.findAll().size());
    }

    @Test
    void whenSaved_thenFindByNameReturns() {

        String teamName = "teamName";

        Team team1 = new Team();
        team1.setName(teamName);
        teamRepository.save(team1);

        assertDoesNotThrow(() ->
                teamRepository.findByNameEquals(teamName).orElseThrow()
        );

        Team teamOnDb = teamRepository.findByNameEquals(teamName).orElseThrow();

        assertEquals(teamName, teamOnDb.getName());
    }

    @Test
    void whenNameIsNull_thenThrowsException() {

        Team team1 = new Team();

        assertThrows(
                DataIntegrityViolationException.class,
                () -> {
                    teamRepository.save(team1);
                }
        );
    }
}