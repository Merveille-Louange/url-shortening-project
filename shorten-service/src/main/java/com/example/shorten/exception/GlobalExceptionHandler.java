package com.example.shorten.exception;

import com.example.shorten.dto.response.GenericResponse;
import com.example.shorten.exception.UserExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import util.ResponseCode;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger LOG = LogManager.getLogger();

    @ExceptionHandler({UserExistException.class})
    public ResponseEntity<Object> handleUserExistException(UserExistException exception, WebRequest request) {
        GenericResponse apiError = new GenericResponse();
        apiError.setErc(exception.getErrorCode());
        apiError.setMessage(exception.getMessage());
        LOG.info("An error occurred: {}", exception.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.OK);

    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        GenericResponse apiError = new GenericResponse();
        apiError.setErc(ResponseCode.ERROR);
        apiError.setMessage("An error occurred, please try again later");
        LOG.info("An error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.OK);
    }
}
