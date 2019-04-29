package com.app.todo.model.resource;

import java.io.Serializable;
import java.util.List;

public class ProjectDto implements Serializable {

    private long          id;
    private String        name;
    private List<TaskDto> tasks;

    public ProjectDto() {
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

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
