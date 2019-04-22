package com.app.todo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column
    private boolean done;
    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
