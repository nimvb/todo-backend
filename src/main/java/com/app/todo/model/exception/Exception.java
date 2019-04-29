package com.app.todo.model.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Exception implements Serializable {

    private HttpStatus    status;
    private List<Error>   errors;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;

    private Exception() {
        time = LocalDateTime.now();
        errors = new ArrayList<>();

    }

    public Exception(HttpStatus status) {
        this();
        this.status = status;
    }

    public void addError(Error error) {
        this.errors.add(error);
    }

    public void addError(String message) {
        this.errors.add(new Error(message));
    }

    public void removeError(Error error) {
        this.errors.remove(error);
    }


}
