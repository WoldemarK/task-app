package ru.yandex.taskserviceapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskRequest
        (
                @NotBlank
                String title,
                String description,
                String category,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
}
