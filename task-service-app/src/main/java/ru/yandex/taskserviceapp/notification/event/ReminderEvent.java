package ru.yandex.taskserviceapp.notification.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReminderEvent
        (
                Long taskId,
                String userId,
                String title,
                LocalDateTime remindAt
        ) {
}
