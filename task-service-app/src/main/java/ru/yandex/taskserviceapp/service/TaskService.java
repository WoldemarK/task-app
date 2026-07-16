package ru.yandex.taskserviceapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.taskserviceapp.dto.CreateTaskRequest;
import ru.yandex.taskserviceapp.dto.TaskResponse;
import ru.yandex.taskserviceapp.dto.UpdateTaskRequest;
import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.entity.TaskStatus;
import ru.yandex.taskserviceapp.exception.TaskNotFoundException;
import ru.yandex.taskserviceapp.mapper.TaskMapper;
import ru.yandex.taskserviceapp.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponse> findAll(String userId) {
        return taskRepository.findAllByUserId(userId)
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Transactional
    public TaskResponse createTask(CreateTaskRequest request, String userId) {
        var task = getTask(request, userId);
        return taskMapper.toResponse(taskRepository.save(task));
    }

    public TaskResponse getById(String userId, Long taskId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        return taskMapper.toResponse(task);
    }

    @Transactional
    public void delete(Long id, String userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        taskRepository.delete(task);
    }

    @Transactional
    public TaskResponse updateTask(Long id, UpdateTaskRequest request, String userId) {

        Task task = taskRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setPriority(request.priority());
        task.setCategory(request.category());
        task.setRemindAt(request.remindAt());
        task.setUpdatedAt(LocalDateTime.now());

        return taskMapper.toResponse(taskRepository.save(task));
    }


    private Task getTask(CreateTaskRequest request, String userId) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .status(TaskStatus.NEW)
                .priority(request.priority())
                .category(request.category())
                .userId(userId)
                .remindAt(request.remindAt())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
