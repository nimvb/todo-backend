package com.app.todo.repository;

import com.app.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    Optional<Project> findProjectById(long id);

    Optional<Project> findProjectByName(String name);

    @Query("select  p from Project p where p.name like :name%")
    List<Project> findProjectsByName(@Param("name") String name);

}
