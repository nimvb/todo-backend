package com.app.todo.mappings;

import com.app.todo.model.Project;
import com.app.todo.model.resource.ProjectDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = TaskMapper.class)
public interface ProjectMapper {

    ProjectDto toProjectDto(Project project);

    List<ProjectDto> toProjectsDto(List<Project> projects);

    Project toProject(ProjectDto projectDto);

    List<Project> toProjects(List<ProjectDto> projectDtoList);
}
