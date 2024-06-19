package com.zjw.lease.web.app.handler;

import com.zjw.lease.exception.LeaseException;
import com.zjw.lease.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 朱俊伟
 * @since 2024/06/10 11:59
 */
@RestControllerAdvice
@Slf4j
public class GlobalRestControllerAdvice {

    @ExceptionHandler(value = {Exception.class})
    public Result<String> handleNullPointerException(Exception e) {
       log.error("系统异常", e);
       return Result.fail();
    }

    @ExceptionHandler(value = {LeaseException.class})
    public Result<String> handleLeaseException(LeaseException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }
}