package ru.yandex.taskserviceapp.notification.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskOverdueEvent
        (
                Long taskId,
                String userId,
                String title,
                LocalDateTime dueAt
        ) {
}
