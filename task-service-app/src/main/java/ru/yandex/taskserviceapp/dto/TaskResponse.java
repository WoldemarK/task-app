package ru.yandex.taskserviceapp.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskResponse
        (
                Long id,
                String title,
                String description,
                String category,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
        ) {
}
