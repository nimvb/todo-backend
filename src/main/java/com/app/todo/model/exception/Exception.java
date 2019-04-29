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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
