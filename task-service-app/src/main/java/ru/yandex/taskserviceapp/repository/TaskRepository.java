package ru.yandex.taskserviceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.taskserviceapp.entity.Priority;
import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.entity.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(String userId);


    List<Task> findAllByUserIdAndStatus(String userId, TaskStatus status);

    List<Task> findAllByUserIdAndPriority(String userId, Priority priority);

    List<Task> findAllByUserIdAndCategory(String userId, String category);

    List<Task> findByRemindAtBeforeAndStatusNot(LocalDateTime now, TaskStatus status);

    Optional<Task> findByIdAndUserId(Long id, String userId);
}
