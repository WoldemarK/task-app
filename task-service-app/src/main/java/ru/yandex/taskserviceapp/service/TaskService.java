package ru.yandex.taskserviceapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.taskserviceapp.dto.TaskRequest;
import ru.yandex.taskserviceapp.dto.TaskResponse;
import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.repository.TaskRepository;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll(String userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public Task createTask(Task task, String userId) {
        task.setUserId(userId);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
}
