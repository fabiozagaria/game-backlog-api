package org.esercizi.gamebacklog.dto;

import jakarta.validation.constraints.*;
import org.esercizi.gamebacklog.model.GameStatus;

import java.time.LocalDate;

public record CreateGameRequest(
        @NotBlank(message = "Titolo obbligatorio")
        String title,

        @NotBlank
        String platform,

        @NotNull
        GameStatus status,

        @NotNull
        @Min(value = 1)
        @Max(value = 10)
        @Positive
        Integer rating,

        @NotNull
        @PastOrPresent
        LocalDate releaseDate
) {
}
