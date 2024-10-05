package com.example.spring_rest.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(int employeeId) {
        super("Employee id not found - " + employeeId);
    }
}
