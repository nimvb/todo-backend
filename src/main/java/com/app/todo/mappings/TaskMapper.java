package com.app.todo.mappings;

import com.app.todo.model.Task;
import com.app.todo.model.resource.TaskDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Mapping(source = "project.id", target = "projectId")
    TaskDto toTaskDto(Task task);

    @InheritConfiguration
    List<TaskDto> toTasksDto(List<Task> task);

    @InheritInverseConfiguration
    Task toTask(TaskDto taskDto);

    @InheritInverseConfiguration
    List<Task> toTasks(List<TaskDto> taskDtoList);
}
