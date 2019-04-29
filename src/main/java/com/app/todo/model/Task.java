package com.app.todo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table

public class Task implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Task() {
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
