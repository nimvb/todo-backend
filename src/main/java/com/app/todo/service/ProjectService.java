package com.app.todo.service;

import com.app.todo.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    List<Project> filterByName(String name);

    void save(Project project);

    void delete(Project project);

    void deleteByName(String name);
}
