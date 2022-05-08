package com.akoca.producterbasketballapi.repo;

import com.akoca.producterbasketballapi.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeamNameEquals(String teamName);
}
