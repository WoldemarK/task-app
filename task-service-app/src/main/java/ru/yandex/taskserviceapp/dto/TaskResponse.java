package ru.yandex.taskserviceapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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

                @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
                LocalDateTime remindAt,

                @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
                LocalDateTime createdAt,

                @JsonFormat(pattern = "dd.MM.yyyy-HH:mm")
                LocalDateTime updatedAt
        ) {
}
