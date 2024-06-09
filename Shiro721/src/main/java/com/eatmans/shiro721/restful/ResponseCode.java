package com.eatmans.shiro721.restful;

/**
 * 响应状态码
 */
public enum ResponseCode {
    /**
     * 请求成功
     */
    OK(200),
    /**
     * 失败的请求
     */
    BAD_REQUEST(400),
    /**
     * 未授权
     */
    UNAUTHORIZED(401),
    /**
     * 禁止访问
     */
    FORBIDDEN(403),
    /**
     * 找不到（该状态不可用）
     */
    NOT_FOUND(404),
    /**
     * 不可访问
     */
    NOT_ACCEPTABLE(406),
    /**
     * 冲突
     */
    CONFLICT(409),
    /**
     * 服务器发生异常
     */
    ERROR(500);

    private final Integer value;

    ResponseCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}