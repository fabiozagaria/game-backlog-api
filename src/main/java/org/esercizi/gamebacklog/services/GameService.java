package org.esercizi.gamebacklog.services;

import org.esercizi.gamebacklog.dto.CreateGameRequest;
import org.esercizi.gamebacklog.dto.PatchGameRequest;
import org.esercizi.gamebacklog.exceptions.DuplicateGameException;
import org.esercizi.gamebacklog.exceptions.GameNotFoundException;
import org.esercizi.gamebacklog.model.Game;
import org.esercizi.gamebacklog.model.GameStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final List<Game> gameList = new ArrayList<>();
    private long idCounter = 11L;

    public GameService() {
        gameList.add(new Game(
                1L,
                "Elden Ring",
                "PS5",
                GameStatus.COMPLETED,
                10,
                LocalDate.of(2022, 2, 25)
        ));

        gameList.add(new Game(
                2L,
                "Resident Evil Village",
                "PS5",
                GameStatus.COMPLETED,
                9,
                LocalDate.of(2021, 5, 7)
        ));

        gameList.add(new Game(
                3L,
                "Cyberpunk 2077",
                "PS5",
                GameStatus.PLAYING,
                8,
                LocalDate.of(2020, 12, 10)
        ));

        gameList.add(new Game(
                4L,
                "Baldur's Gate 3",
                "PC",
                GameStatus.BACKLOG,
                null,
                LocalDate.of(2023, 8, 3)
        ));

        gameList.add(new Game(
                5L,
                "Resident Evil 4",
                "PS5",
                GameStatus.COMPLETED,
                10,
                LocalDate.of(2023, 3, 24)
        ));

        gameList.add(new Game(
                6L,
                "Alan Wake 2",
                "PS5",
                GameStatus.BACKLOG,
                null,
                LocalDate.of(2023, 10, 27)
        ));

        gameList.add(new Game(
                7L,
                "Dark Souls III",
                "PC",
                GameStatus.DROPPED,
                7,
                LocalDate.of(2016, 4, 12)
        ));

        gameList.add(new Game(
                8L,
                "God of War Ragnarök",
                "PS5",
                GameStatus.COMPLETED,
                9,
                LocalDate.of(2022, 11, 9)
        ));

        gameList.add(new Game(
                9L,
                "Death Stranding",
                "PS5",
                GameStatus.PLAYING,
                8,
                LocalDate.of(2019, 11, 8)
        ));

        gameList.add(new Game(
                10L,
                "Lies of P",
                "PS5",
                GameStatus.BACKLOG,
                null,
                LocalDate.of(2023, 9, 19)
        ));
    }

    public List<Game> getGameListCopy() {
        return List.copyOf(gameList);
    }

    public Game findById(long id) {
        return gameList.stream()
                .filter(
                        game -> game.getId().equals(id)
                ).findFirst()
                .orElseThrow(() -> new GameNotFoundException("Game non trovato"));
    }

    public Game save(PatchGameRequest patchGameRequest, long id) {
        Game game = findById(id);

        if (patchGameRequest.title() != null)
            game.setTitle(patchGameRequest.title());
        if (patchGameRequest.platform() != null)
            game.setPlatform(patchGameRequest.platform());
        if (patchGameRequest.status() != null)
            game.setStatus(patchGameRequest.status());
        if (patchGameRequest.rating() != null)
            game.setRating(patchGameRequest.rating());

        return game;
    }

    public Game addGame(CreateGameRequest gameRequest) {
        if(!existsGame(gameRequest)) {
            Game game = toGame(gameRequest);
            game.setId(idCounter++);
            gameList.add(game);
            return game;
        } else {
            throw new DuplicateGameException("Game gia esistente");
        }

    }

    public void removeById(long id) {
        gameList.removeIf(
                game -> game.getId().equals(id)
        );
    }

    public boolean existsGame(CreateGameRequest newGame) {
        return gameList.stream()
                .anyMatch(game -> game.getTitle().equalsIgnoreCase(newGame.title()));

    }

    public Game toGame(CreateGameRequest gameRequest) {
        return new Game(
                null,
                gameRequest.title(),
                gameRequest.platform(),
                gameRequest.status(),
                gameRequest.rating(),
                gameRequest.releaseDate()
        );

    }
}
