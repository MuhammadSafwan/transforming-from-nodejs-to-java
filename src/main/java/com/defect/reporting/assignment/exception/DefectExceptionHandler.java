package com.defect.reporting.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Safwan
 */

@ControllerAdvice
public class DefectExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DefectExceptionResponse> handleException(InvalidInputException e) {
        DefectExceptionResponse exception = new DefectExceptionResponse(HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<DefectExceptionResponse> handleException(DefectNotFoundException e) {
        DefectExceptionResponse exception = new DefectExceptionResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DefectExceptionResponse> handleException(WorkerNotFoundException e) {
        DefectExceptionResponse exception = new DefectExceptionResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DefectExceptionResponse> handleException(MachineNotFoundException e) {
        DefectExceptionResponse exception = new DefectExceptionResponse(HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}
