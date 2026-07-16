package ru.yandex.taskserviceapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;


@Builder
public record ValidationErrorResponse
        (
                @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
                LocalDateTime timestamp,
                int status,
                String message,
                Map<String, String> errors,
                String path
        ) {
}
