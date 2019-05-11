package com.app.todo.controller;


import com.app.todo.exceptions.ResourceNotFoundException;
import com.app.todo.mappings.ProjectMapper;
import com.app.todo.model.Project;
import com.app.todo.model.resource.ProjectDto;
import com.app.todo.service.ProjectService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProjectController {


    @Autowired
    ProjectService service;

    ProjectMapper mapper = Mappers.getMapper(ProjectMapper.class);


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("projects")
    public ResponseEntity<List<ProjectDto>> projects() {
        List<Project> projects = service.findAll();
        return ResponseEntity.ok().body(mapper.toProjectsDto(projects));
        //return new ResponseEntity(mapper.toProjectsDto(projects), HttpStatus.OK);
        //return mapper.toProjectsDto(projects);
    }

    @GetMapping("projects/{id}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("id") long id) {

        Project project = service.findAll().stream().filter(p -> p.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("Not found"));
        return ResponseEntity.ok().body(mapper.toProjectDto(project));

    }


    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("projects/{id}")
    public ResponseEntity deleteProject(@PathVariable("id") long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
