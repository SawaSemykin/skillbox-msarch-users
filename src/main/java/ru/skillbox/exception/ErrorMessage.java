package ru.skillbox.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorMessage {
    int statusCode;
    LocalDateTime timestamp;
    String message;
    String description;
}
