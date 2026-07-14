package ru.yandex.taskserviceapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.yandex.taskserviceapp.dto.CreateTaskRequest;
import ru.yandex.taskserviceapp.dto.TaskResponse;
import ru.yandex.taskserviceapp.dto.UpdateTaskRequest;
import ru.yandex.taskserviceapp.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskRestController {

    private final TaskService service;

    @GetMapping("/all")
    public List<TaskResponse> getTasks(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return service.findAll(userId);
    }

    @PostMapping
    public TaskResponse create(@RequestBody CreateTaskRequest task,
                               @AuthenticationPrincipal Jwt jwt) {
        return service.createTask(task, jwt.getSubject());

    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id,
                                   @RequestBody UpdateTaskRequest task,
                                   @AuthenticationPrincipal Jwt jwt) {
        return service.updateTask(id, task, jwt.getSubject());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id,
                           @AuthenticationPrincipal Jwt jwt) {
        service.delete(id, jwt.getSubject());
    }

    @GetMapping("/{taskId}")
    public TaskResponse getById(@PathVariable Long taskId,
                                @AuthenticationPrincipal Jwt jwt) {
        return service.getById(jwt.getSubject(), taskId);
    }
}
