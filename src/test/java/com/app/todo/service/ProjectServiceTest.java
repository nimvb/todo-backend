package com.app.todo.service;

import com.app.todo.model.Project;
import com.app.todo.model.Task;
import com.app.todo.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProjectServiceTest {


    @TestConfiguration
    static class Configure {
        @Bean
        ProjectService projectService() {
            return new ProjectServiceImpl();
        }
    }


    @MockBean
    ProjectRepository repository;

    @Autowired
    ProjectService service;


    @Before
    public void setUp() {

        final long[] projectId = {1};
        final long[] taskId    = {10};
        List<Project> projects = Arrays.asList("project-1", "project-2", "project-3", "project-4").stream().map(p -> {
            Project project = new Project();
            project.setName(p);
            project.setId(projectId[0]++);
            Arrays.asList("task-1", "task-2").stream().map(t -> {
                Task task = new Task();
                task.setName(t);
                task.setId(taskId[0]++);
                return task;
            }).forEach(project::addTask);
            return project;
        }).collect(Collectors.toList());

        // Repos findAll mock
        Mockito.when(repository.findAll()).thenReturn(projects);

        // Repos findProjectByName mock
        Mockito.when(repository.findProjectByName(ArgumentMatchers.any(String.class))).thenAnswer(s -> {
            String name = s.getArgument(0);
            return projects.stream().filter(p -> p.getName().equals(name)).findFirst();
        });

        // Repos save mock
        Mockito.when(repository.save(ArgumentMatchers.any(Project.class))).thenAnswer(p -> {
            Project project = p.getArgument(0);
            project.setId(projectId[0]++);
            projects.add(project);
            return project;
        });

        // Repos delete mock
        Mockito.doAnswer(p -> {
            Project project = p.getArgument(0);
            projects.stream().filter(prj -> prj.getId() == project.getId()).findFirst().ifPresent(pp -> {
                projects.remove(pp);
            });
            return null;
        }).when(repository).delete(ArgumentMatchers.any(Project.class));
    }

    @Test
    public void testFindAll() {
        List<Project> projects = service.findAll();
        List<Project> expected = repository.findAll();

        Assertions.assertThat(projects).isNotEmpty();
        Assertions.assertThat(projects).isEqualTo(expected);
    }

    @Test
    public void testFilterByName() {


        List<Project> expected = repository.findAll();

        List<Project> projects = service.filterByName(expected.stream().findAny().get().getName().substring(0, 3));

        Assertions.assertThat(projects).isNotEmpty();
        Assertions.assertThat(projects).isEqualTo(expected);

    }

    @Test
    public void testSave() {
        List<Project> previousProjects = repository.findAll();

        int prevSize = previousProjects.size();

        Project project = new Project();
        project.setName("project-testsave");
        service.save(project);

        List<Project> newProjects = repository.findAll();

        Assertions.assertThat(newProjects.size()).isEqualTo(prevSize + 1);
    }

    @Test
    public void testDelete() {
        Project project = repository.findAll().stream().findAny().get();

        service.delete(project);

        Optional<Project> expectedProject = repository.findProjectById(project.getId());

        Assertions.assertThat(expectedProject.isPresent()).isFalse();

    }


    @Test
    public void testDeleteByName() {
        Project project = repository.findAll().stream().findAny().get();

        service.deleteByName(project.getName());

        Optional<Project> expectedProject = repository.findProjectById(project.getId());

        Assertions.assertThat(expectedProject.isPresent()).isFalse();

    }

}