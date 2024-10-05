package com.example.spring_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.spring_rest.entity.Error;

@ControllerAdvice // mark this class as global exception handlers
public class RestExceptionHandler {
    // we can put this exception handler directly in controller
    // but we want handle this exception if occur in any controller.
    // so we put it in a public container, all controllers can see it.

    @ExceptionHandler // to handle the exception when it happens
    public ResponseEntity<Error> handleException(EmployeeNotFoundException exc) {
        // 1] exception handler method must return `ResponseEntity`.
        // 2] ResponseEntity represents entire HTTP response, so you can specify anything in the response.
              // like response line, headers, body
        // 3] we don't return the `Error` directly to can change status code explicitly.

        Error error = new Error();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage()); // developer will write the message
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(BadRequestException exc) {
        Error error = new Error();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // This is default handler, that handle any exception you don't specify or create
    @ExceptionHandler
    public ResponseEntity<Error> handleException(Exception exc) {
        Error error = new Error();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
