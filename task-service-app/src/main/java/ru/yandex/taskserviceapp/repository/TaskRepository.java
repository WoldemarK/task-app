package ru.yandex.taskserviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.taskserviceapp.entity.Priority;
import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(String userId);

    Optional<Task> findByIdAndUserId(Long id, String userId);

    /**
     * Найти все задачи, у которых reminderSent = false и remindAt раньше указанного времени.
     * @param now
     * @return
     */
    List<Task> findByReminderSentFalseAndRemindAtBefore(LocalDateTime now);

    List<Task> findByStatusNotAndDueAtBefore(TaskStatus status, LocalDateTime now);

    /**
     * Найти все задачи, у которых статус находится в указанном списке и дата dueAt раньше указанного времени.
     * @param statuses
     * @param now
     * @return
     */
    List<Task>findByStatusInAndDueAtBefore(Collection<TaskStatus> statuses, LocalDateTime now);
}
