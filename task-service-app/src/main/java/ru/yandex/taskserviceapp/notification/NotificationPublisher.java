package ru.yandex.taskserviceapp.notification;

import ru.yandex.taskserviceapp.notification.event.ReminderEvent;
import ru.yandex.taskserviceapp.notification.event.TaskOverdueEvent;

public interface NotificationPublisher {

    void publishReminder(ReminderEvent event);

    void publishTaskOverdue(TaskOverdueEvent event);
}
