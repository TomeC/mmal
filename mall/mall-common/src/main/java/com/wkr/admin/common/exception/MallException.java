package com.wkr.admin.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * mall异常类

 */
@Slf4j
@RestControllerAdvice
public class MallException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MallException() {}

    public MallException(String message) {
        super(message);
    }
}
