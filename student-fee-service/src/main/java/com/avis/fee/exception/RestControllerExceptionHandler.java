package com.avis.fee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestControllerExceptionHandler {


    @ExceptionHandler(value = { ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {

        List<String> errorMessage=new ArrayList<>();
        errorMessage.add(e.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errorMessage,
                request.getDescription(false),
                request.getContextPath());

        return message;
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(MethodArgumentNotValidException  e, WebRequest request) {
        List<String> errors = e.getBindingResult().getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage()).collect(Collectors.toList());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errors,
                request.getDescription(false),
                request.getContextPath());

        return message;
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataNotFoundException(Exception e, WebRequest request) {
        List<String> errorMessage=new ArrayList<>();
        errorMessage.add(e.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                errorMessage,
                request.getDescription(false),
                request.getContextPath());
        return message;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(Exception e, WebRequest request) {
        List<String> errorMessage=new ArrayList<>();
        errorMessage.add(e.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                errorMessage,
                request.getDescription(false),
                request.getContextPath());
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleMethodArgsNotValidException(Exception e, WebRequest request) {
        List<String> errorMessage=new ArrayList<>();
        errorMessage.add(e.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                errorMessage,
                request.getDescription(false),
                request.getContextPath());

        return message;
    }
}
