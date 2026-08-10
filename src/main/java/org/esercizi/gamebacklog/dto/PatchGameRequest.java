package org.esercizi.gamebacklog.dto;

import org.esercizi.gamebacklog.model.GameStatus;

public record PatchGameRequest(
        String title,
        String platform,
        GameStatus status,
        Integer rating
) {
}
