package ru.yandex.taskserviceapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import ru.yandex.taskserviceapp.dto.CreateTaskRequest;
import ru.yandex.taskserviceapp.dto.TaskResponse;
import ru.yandex.taskserviceapp.entity.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponse toResponse(Task task);

    @Mapping(target ="id", ignore =true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Task toEntity(CreateTaskRequest request);
}
