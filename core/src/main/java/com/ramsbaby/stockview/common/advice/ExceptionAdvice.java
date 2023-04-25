package com.ramsbaby.stockview.common.advice;

import com.ramsbaby.stockview.common.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse generalException(Exception e) {
        return new ApiErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ApiErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .setFieldErrorData(e.getAllErrors())
            .build();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        return new ApiErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleInvalidParameterException(InvalidParameterException e) {
        return new ApiErrorResponse.Builder()
            .setCode(HttpStatus.BAD_REQUEST.value())
            .setMsg(e.getMessage())
            .build();
    }

}
