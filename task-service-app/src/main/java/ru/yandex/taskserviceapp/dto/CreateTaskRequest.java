package ru.yandex.taskserviceapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import ru.yandex.taskserviceapp.entity.Category;
import ru.yandex.taskserviceapp.entity.Priority;

import java.time.LocalDateTime;

@Builder
public record CreateTaskRequest
        (
                @NotBlank(message = "Title is required")
                String title,

                @NotBlank(message = "Description is required")
                String description,

                @NotNull(message = "Priority is required")
                Priority priority,

                @NotNull(message = "Category is required")
                Category category,

                LocalDateTime remindAt
        ) {
}
