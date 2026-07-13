package ru.yandex.taskserviceapp.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import ru.yandex.taskserviceapp.entity.Task;
import ru.yandex.taskserviceapp.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<Task> getTasks(@AuthenticationPrincipal Jwt jwt){

        String userId = jwt.getSubject();

        return service.findAll(userId);
    }



    @PostMapping
    public Task create(@RequestBody Task task, @AuthenticationPrincipal Jwt jwt){
        return service.createTask(task, jwt.getSubject());

    }

}
