package ru.lapshina.api;


public class ItemNotFound extends RuntimeException{
    private final String message;


    public ItemNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
