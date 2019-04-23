package com.app.todo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table

public class Task implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private boolean done;

    public Task() {
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
}
