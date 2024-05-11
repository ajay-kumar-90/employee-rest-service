package com.ajaycodes.employeeservice.exception.handler;

import com.ajaycodes.employeeservice.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class EmployeeGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        EmployeeExceptionResponse exceptionResponse = new EmployeeExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        EmployeeExceptionResponse exceptionResponse = new EmployeeExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(exceptionResponse, HttpStatusCode.valueOf(404));
    }
}
