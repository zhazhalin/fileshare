package com.zhang.fileshare.exceptionHandler;

import com.zhang.fileshare.utils.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zhazhalin
 * @version 1.0
 * @date 2022/8/10 10:48
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }
}
