package com.app.todo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Table
@Entity
public class Project implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long      id;
    @Column
    private String    name;

    @OneToMany
    private List<Task> tasks;

    public Project() {
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
}
