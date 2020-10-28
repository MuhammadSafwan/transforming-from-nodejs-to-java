package com.defect.reporting.assignment.exception;

/**
 * @author Safwan
 */
public class DefectNotFoundException extends RuntimeException {
    public DefectNotFoundException(String message) {
        super(message);
    }

    public DefectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefectNotFoundException(Throwable cause) {
        super(cause);
    }
}
