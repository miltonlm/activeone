package com.activeone.posts.config;

import com.activeone.posts.exceptions.HttpNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpNotFoundException.class)
    public ResponseEntity<Map<String, String>> httpNotFoundException(HttpNotFoundException exception) {
        var body = new HashMap<String, String>();

        body.put("error", exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var body = new HashMap<String, Object>();
        var errors = new ArrayList<String>();

        for (FieldError fieldError : exception.getFieldErrors()) {
            errors.add("%s: %s".formatted(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        body.put("errors", errors);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
