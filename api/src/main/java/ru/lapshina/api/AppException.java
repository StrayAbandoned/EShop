package ru.lapshina.api;


public class AppException {
    private int status;
    private String message;

    public AppException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public AppException() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
