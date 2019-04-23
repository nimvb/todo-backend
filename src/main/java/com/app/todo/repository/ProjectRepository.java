package com.app.todo.repository;

import com.app.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
