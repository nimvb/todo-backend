package com.app.todo.mapping;

import com.app.todo.mappings.ProjectMapper;
import com.app.todo.model.Project;
import com.app.todo.model.Task;
import com.app.todo.model.resource.ProjectDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;


public class ProjectMapperTest {

    ProjectMapper mapper;

    @Before
    public void setUp() {
        mapper = Mappers.getMapper(ProjectMapper.class);
    }


    @Test
    public void toProjectDtoTest() {

        Project project = new Project();
        project.setId(12);
        project.setName("project1");
        project.addTask(new Task("task-1"));
        project.addTask(new Task("task-2"));
        project.addTask(new Task("task-3"));
        ProjectDto result = mapper.toProjectDto(project);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getName()).isEqualTo(project.getName());
        Assertions.assertThat(
                result.getTasks().stream().map(
                        taskDto -> taskDto.getProjectId()).collect(
                        Collectors.toList()
                )
        ).isEqualTo(
                project.getTasks().stream().map(
                        task -> task.getProject().getId()).collect(
                        Collectors.toList()
                )
        );

    }

}