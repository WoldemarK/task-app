package ru.yandex.taskserviceapp.dto;

import lombok.Builder;
import ru.yandex.taskserviceapp.entity.Category;
import ru.yandex.taskserviceapp.entity.Priority;
import ru.yandex.taskserviceapp.entity.TaskStatus;

import java.time.LocalDateTime;

@Builder
public record TaskResponse
        (
                Long id,

                String title,

                String description,

                TaskStatus status,

                Priority priority,

                Category category,

                LocalDateTime remindAt,

                LocalDateTime createdAt,

                LocalDateTime updatedAt
        ) {
}
