package com.app.todo.service.core.configurations.seeders;

import com.app.todo.model.Project;
import com.app.todo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseSeederService {

    @Autowired
    ProjectService projectService;


    public DatabaseSeederService(){



    }




    public void seed() {
        Project project = new Project();
        project.setName("default");
        projectService.save(project);
    }
}
