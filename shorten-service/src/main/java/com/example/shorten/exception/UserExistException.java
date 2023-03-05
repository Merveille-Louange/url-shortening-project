package com.example.shorten.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserExistException extends RuntimeException{
    private String message;
    private int errorCode;
}
