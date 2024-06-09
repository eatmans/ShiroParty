package com.eatmans.shiro710.exception;

import com.eatmans.shiro710.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义异常
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalException extends RuntimeException {
    private ResponseCode responseCode;

    public GlobalException(ResponseCode responseCode, String message) {
        super(message);

        setResponseCode(responseCode);
    }

}