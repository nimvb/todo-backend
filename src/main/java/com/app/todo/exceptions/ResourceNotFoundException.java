package com.app.todo.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

    private List<String> errors;


    public ResourceNotFoundException() {
        errors = new ArrayList<>();
    }

    public ResourceNotFoundException(String message) {

        this();
        errors.add(message);

    }

    public ResourceNotFoundException(String... messages) {
        this();
        Arrays.asList(messages).stream().forEach(this.errors::add);

    }

    public ResourceNotFoundException addError(String message) {
        this.errors.add(message);
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }
}
