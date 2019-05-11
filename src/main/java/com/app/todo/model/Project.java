package com.app.todo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Project implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @OneToMany(mappedBy = "project", cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Project() {
        this.tasks = new ArrayList<>();
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        task.setProject(this);
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
        task.setProject(null);
    }
}
