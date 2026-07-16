package ru.yandex.taskserviceapp.notification;

import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.notification.event.ReminderEvent;
import ru.yandex.taskserviceapp.notification.event.TaskOverdueEvent;

public interface NotificationPublisher {

    void publishReminder(ReminderEvent task);

    void publishTaskOverdue(TaskOverdueEvent task);
}
