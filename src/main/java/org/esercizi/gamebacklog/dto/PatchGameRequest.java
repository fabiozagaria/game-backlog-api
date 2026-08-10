package org.esercizi.gamebacklog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.esercizi.gamebacklog.model.GameStatus;

public record PatchGameRequest(
        String title,
        String platform,
        GameStatus status,

        @Min(value = 1)
        @Max(value = 10)
        @Positive
        Integer rating
) {
}
