package org.esercizi.gamebacklog.exceptions.error;

import org.springframework.http.HttpStatus;

public record APIError(
        String errorName,
        String details,
        String path,
        HttpStatus status
) {

}
