package ru.skillbox.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    private ErrorMessage handle(HttpStatus status, Exception e, WebRequest request,
        boolean includeRequestClientInfo) {
        ErrorMessage errorMessage = ErrorMessage.builder()
            .statusCode(status.value())
            .timestamp(LocalDateTime.now())
            .message(e.getMessage())
            .description(request.getDescription(includeRequestClientInfo))
            .build();
        log.error(errorMessage.toString(), e);
        return errorMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
        WebRequest request) {
        return handle(HttpStatus.BAD_REQUEST, e, request, true);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(ResourceNotFoundException e,
                                                        WebRequest request) {
        return handle(HttpStatus.NOT_FOUND, e, request, false);
    }

    @ExceptionHandler(UserIdNotProvidedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleResourceNotFoundException(UserIdNotProvidedException e,
                                                        WebRequest request) {
        return handle(HttpStatus.BAD_REQUEST, e, request, false);
    }

    @ExceptionHandler(UserIdNotConsistentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleResourceNotFoundException(UserIdNotConsistentException e,
                                                        WebRequest request) {
        return handle(HttpStatus.BAD_REQUEST, e, request, false);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleNullPointerException(NullPointerException e, WebRequest request) {
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, e, request, true);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleRuntimeException(RuntimeException e, WebRequest request) {
        return handle(HttpStatus.INTERNAL_SERVER_ERROR, e, request, true);
    }
}
