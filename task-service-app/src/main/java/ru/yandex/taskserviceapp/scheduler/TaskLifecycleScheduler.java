package ru.yandex.taskserviceapp.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.yandex.taskserviceapp.service.TaskLifecycleService;


@Slf4j
@Component
@RequiredArgsConstructor
public class TaskLifecycleScheduler {

    private final TaskLifecycleService lifecycleService;

    @Scheduled(fixedDelayString = "${task.scheduler.reminder-delay}")
    @SchedulerLock(
            name = "reminderScheduler",
            lockAtMostFor = "5m",
            lockAtLeastFor = "30s"
    )
    public void processReminders() {
        lifecycleService.processReminders();
    }


    @Scheduled(fixedDelayString = "${task.scheduler.overdue-delay}")
    @SchedulerLock(
            name = "overdueScheduler",
            lockAtMostFor = "5m",
            lockAtLeastFor = "30s"
    )
    public void processOverdueTasks() {
        lifecycleService.processOverdueTasks();
    }
}
