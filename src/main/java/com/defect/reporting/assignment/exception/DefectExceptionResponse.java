package com.defect.reporting.assignment.exception;

/**
 * @author Safwan
 */
public class DefectExceptionResponse {

    private int status;
    private String title;
    private String message;

    public DefectExceptionResponse() {
    }

    public DefectExceptionResponse(int status, String title, String message) {
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
