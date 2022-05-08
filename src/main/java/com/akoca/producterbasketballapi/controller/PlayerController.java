package com.akoca.producterbasketballapi.controller;

import com.akoca.producterbasketballapi.dto.PlayerDto;
import com.akoca.producterbasketballapi.entity.Player;
import com.akoca.producterbasketballapi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/players")
public class PlayerController {
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> allPlayers = playerService.getAll();

        return ResponseEntity.ok(allPlayers);
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@Valid @RequestBody PlayerDto playerDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Player data is invalid");
        }

        Optional<Player> addedPlayerOpt = Optional.of(playerService.addNewPlayer(playerDto));

        return addedPlayerOpt
                .map(player -> ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("New player added.Player id: " + player.getId()))
                .orElseGet(() -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to add new player.Unexpected error occurred"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(@Positive @PathVariable Long id, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Player id invalid");
        }

        Optional<Player> deletedPlayerOpt = Optional.of(playerService.deletePlayerById(id));

        return deletedPlayerOpt
                .map(player -> ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Player with id: " + id + " deleted"))
                .orElseGet(() -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to delete player with id: " + id));
    }
}
