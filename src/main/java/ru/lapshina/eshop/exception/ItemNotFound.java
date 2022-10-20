package ru.lapshina.eshop.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ItemNotFound extends RuntimeException{
    private final String message;
}
