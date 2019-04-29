package com.app.todo.service.core.exceptions;

import com.app.todo.exceptions.ResourceNotFoundException;
import com.app.todo.model.exception.Exception;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerServiceImpl extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity handleResourceNotFoundException(ResourceNotFoundException ex) {
        Exception exception = new Exception(HttpStatus.NOT_FOUND);
        ex.getErrors().forEach(exception::addError);
        return new ResponseEntity(exception, HttpStatus.NOT_FOUND);

    }
}
