package com.app.todo.repository;

import com.app.todo.model.Project;
import com.app.todo.model.Task;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class ProjectRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProjectRepository repository;


    List<String>  projectNames = Arrays.asList("project-1", "project-2", "project-3", "project-4");
    List<String>  taskNames    = Arrays.asList("task-1", "task-2", "task-3", "task-4");
    List<Project> projects     = new ArrayList<>();


    @Before
    public void init() {

        projects = projectNames.stream().map(projectName -> {
            Project project = new Project();
            project.setName(projectName);
            taskNames.stream().map(taskName -> new Task(taskName, false)).forEach(project::addTask);
            return project;
        }).collect(Collectors.toList());
        projects.forEach(entityManager::persistAndFlush);


    }


    @Test
    public void initTest() {


    }


    @Test
    public void testFindProjectById() {

        Project actual = projects.stream().findAny().get();
        Project result = repository.findProjectById(actual.getId()).orElseThrow(() -> new AssertionError("Project not found!"));

        Assertions.assertThat(result.getId()).isEqualTo(actual.getId());
        Assertions.assertThat(result).isEqualTo(actual);
    }

    @Test
    public void testFindProjectByName() {

        Project actual = projects.stream().findAny().get();
        Project result = repository.findProjectByName(actual.getName()).orElseThrow(() -> new AssertionError("Project not found!"));

        Assertions.assertThat(result.getId()).isEqualTo(actual.getId());
        Assertions.assertThat(result).isEqualTo(actual);

    }

    @Test
    public void testFindProjectsByName() {

        Project       testCase = projects.stream().findAny().get();
        List<Project> all      = repository.findProjectsByName(testCase.getName().substring(0, 3));
        List<Project> expected = repository.findProjectsByName(testCase.getName());

        Assertions.assertThat(all).isNotEmpty();
        Assertions.assertThat(all.size()).isEqualTo(projects.size());
        Assertions.assertThat(expected).isNotEmpty();
        Assertions.assertThat(expected.size()).isEqualTo(1);
        Assertions.assertThat(expected.stream().findFirst().get()).isEqualTo(testCase);

    }


}