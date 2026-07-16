package ru.yandex.taskserviceapp.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.taskserviceapp.notification.event.ReminderEvent;
import ru.yandex.taskserviceapp.notification.event.TaskOverdueEvent;

@Slf4j
@Component
public class LoggingNotificationPublisher implements NotificationPublisher {

    @Override
    public void publishReminder(ReminderEvent event) {
        log.info("""
                        ==========================
                        REMINDER EVENT
                        
                        taskId={}
                        title={}
                        userId={}
                        remindAt={}
                        
                        ==========================
                        """,
                event.taskId(),
                event.title(),
                event.userId(),
                event.remindAt());
    }

    @Override
    public void publishTaskOverdue(TaskOverdueEvent event) {
        log.info("""
                        ==========================
                        TASK OVERDUE EVENT
                        
                        taskId={}
                        title={}
                        userId={}
                        dueAt={}
                        
                        ==========================
                        """,
                event.taskId(),
                event.title(),
                event.userId(),
                event.dueAt());

    }
}
