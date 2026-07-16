package ru.yandex.taskserviceapp.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.entity.TaskStatus;
import ru.yandex.taskserviceapp.notification.LoggingNotificationPublisher;
import ru.yandex.taskserviceapp.notification.event.ReminderEvent;
import ru.yandex.taskserviceapp.notification.event.TaskOverdueEvent;
import ru.yandex.taskserviceapp.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskLifecycleService {

    private final TaskRepository repository;
    private final LoggingNotificationPublisher publisher;

    @Transactional
    public void processReminders() {

        List<Task> tasks = repository.findByReminderSentFalseAndRemindAtBefore(LocalDateTime.now());

        if (tasks.isEmpty()) {
            return;
        }
        log.info("Found {} reminder(s)", tasks.size());

        for (Task task : tasks) {

            publisher.publishReminder(ReminderEvent.builder()
                    .taskId(task.getId())
                    .userId(task.getUserId())
                    .title(task.getTitle())
                    .remindAt(task.getRemindAt())
                    .build());

            task.setReminderSent(true);
        }
        repository.saveAll(tasks);
    }

    @Transactional
    public void processOverdueTasks() {
        List<Task> tasks = repository.findByStatusInAndDueAtBefore(
                List.of(
                        TaskStatus.NEW,
                        TaskStatus.IN_PROGRESS
                ),
                LocalDateTime.now()
        );
        if (tasks.isEmpty()) {
            return;
        }
        for (Task task : tasks) {

            publisher.publishTaskOverdue(TaskOverdueEvent.builder()
                    .taskId(task.getId())
                    .userId(task.getUserId())
                    .title(task.getTitle())
                    .dueAt(task.getDueAt())
                    .build());

            task.setStatus(TaskStatus.OVERDUE);
            task.setUpdatedAt(LocalDateTime.now());
            log.info("Marked {} task(s) as OVERDUE", tasks.size());
        }
        repository.saveAll(tasks);
    }
}
