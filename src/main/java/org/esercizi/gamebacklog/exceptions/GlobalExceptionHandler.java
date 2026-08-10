package org.esercizi.gamebacklog.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.esercizi.gamebacklog.exceptions.error.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateGameException.class)
    public ResponseEntity<APIError> handleDuplicateGame(
            DuplicateGameException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.CONFLICT;
        APIError apiError = new APIError(
                "DUPLICATE_GAME",
                exception.getMessage(),
                URI.create(request.getRequestURI()).toString(),
                status

        );
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<APIError> handleNotFoundGame(
            GameNotFoundException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        APIError apiError = new APIError(
                "NOT_FOUND_GAME",
                exception.getMessage(),
                URI.create(request.getRequestURI()).toString(),
                status

        );
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                "VALIDATION",
                exception.getMessage(),
                URI.create(request.getRequestURI()).toString(),
                status

        );
        return new ResponseEntity<>(apiError, status);
    }

    @ExceptionHandler(InvalidGameDataException.class)
    public ResponseEntity<APIError> handleMethodArgumentNotValid(
            InvalidGameDataException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        APIError apiError = new APIError(
                "INVALID_GAME_DATA",
                exception.getMessage(),
                URI.create(request.getRequestURI()).toString(),
                status

        );
        return new ResponseEntity<>(apiError, status);
    }

}
