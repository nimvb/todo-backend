package com.app.todo.service;

import com.app.todo.model.Project;
import com.app.todo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public List<Project> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Project> filterByName(String name) {

        List<Project> projects = findAll();

        List<Project> result = projects.stream().filter(project -> project.getName().startsWith(name)).collect(Collectors.toList());

        return result;

    }

    @Transactional
    @Override
    public void save(Project project) {
        Optional<Project> p = repository.findProjectByName(project.getName());
        if (!p.isPresent()) {
            repository.save(project);
        }

    }

    @Transactional
    @Override
    public void delete(Project project) {
        repository.delete(project);
    }

    @Override
    public void deleteByName(String name) {
        repository.findProjectByName(name).ifPresent(project -> {
            repository.delete(project);
        });
    }


}
