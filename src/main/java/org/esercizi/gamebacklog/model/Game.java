package org.esercizi.gamebacklog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    private Long id;

    @NotBlank(message = "Titolo obbligatorio")
    private String title;

    @NotBlank(message = "Piattaforma obbligatoria")
    private String platform;

    @NotNull
    private GameStatus status;

    @NotNull
    @Min(value = 1)
    @Max(value = 10)
    private Integer rating;

    @NotNull
    @PastOrPresent
    private LocalDate releaseDate;

    public Game() {

    }

    public Game(Long id, String title, String platform, GameStatus status, Integer rating, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.platform = platform;
        this.status = status;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
