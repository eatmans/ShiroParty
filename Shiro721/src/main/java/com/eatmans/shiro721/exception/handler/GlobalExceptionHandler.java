package com.eatmans.shiro721.exception.handler;

import com.eatmans.shiro721.exception.GlobalException;
import com.eatmans.shiro721.restful.JsonResult;
import com.eatmans.shiro721.restful.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 * @version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理GlobalException
     * @param e GlobalException
     * @return JsonResult<Void>
     */
    @ExceptionHandler(GlobalException.class)
    public JsonResult<Void> handlerGlobalException(HttpServletResponse response, GlobalException e) {
        System.err.println(e.getMessage());
        e.printStackTrace();
        response.setStatus(e.getResponseCode().getValue());

        return JsonResult.error(e.getResponseCode(), e);
    }

    /**
     * 处理BindException
     * @param e BindException
     * @return JsonResult<Void>
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult<Void> handlerBindException(BindException e) {
        e.printStackTrace();

        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        assert fieldError != null;
        String defaultMessage = fieldError.getDefaultMessage();

        return JsonResult.error(ResponseCode.BAD_REQUEST, defaultMessage);
    }

    /**
     * 处理Exception
     * @param e Exception
     * @return JsonResult<Void>
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonResult<Void> handlerException(Exception e) {
        e.printStackTrace();

        return JsonResult.error(ResponseCode.ERROR, e);
    }

}