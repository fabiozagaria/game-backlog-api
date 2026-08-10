package org.esercizi.gamebacklog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.esercizi.gamebacklog.dto.CreateGameRequest;
import org.esercizi.gamebacklog.dto.PatchGameRequest;
import org.esercizi.gamebacklog.model.Game;
import org.esercizi.gamebacklog.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGameListCopy();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(
            @PathVariable long id
    ) {
       Game game =  gameService.findById(id);
       return ResponseEntity
               .ok(game);
    }

    @PostMapping
    public ResponseEntity<Game> postGame(
            @Valid @RequestBody CreateGameRequest gameRequest,
            HttpServletRequest request
            ) {
        Game game = gameService.addGame(gameRequest);
        return ResponseEntity
                .created(URI.create(request.getRequestURI()+"/"+game.getId().toString())).body(game);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchGame(
            @PathVariable long id,
            @Valid @RequestBody PatchGameRequest gameRequest
    ) {
        gameService.save(gameRequest, id);
        return ResponseEntity
                .noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(
            @PathVariable long id
    ) {
        gameService.removeById(id);
        return ResponseEntity
                .ok().build();
    }
}
