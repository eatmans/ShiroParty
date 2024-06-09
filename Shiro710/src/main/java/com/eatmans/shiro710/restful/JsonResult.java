package com.eatmans.shiro710.restful;

import lombok.Data;

@Data
public class JsonResult<T> {
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public static JsonResult<Void> success() {
        return success(null);
    }

    public static JsonResult<Void> success(String message) {
        return success(message, null);
    }

    public static <T> JsonResult<T> success(String message, T data) {
        JsonResult<T> jsonResult = new JsonResult<>();

        jsonResult.setCode(ResponseCode.OK.getValue());
        jsonResult.setMessage(message);
        jsonResult.setData(data);

        return jsonResult;
    }

    public static JsonResult<Void> error(String message) {
        return error(ResponseCode.ERROR, message);
    }

    public static JsonResult<Void> error(ResponseCode responseCode, Throwable e) {
        return error(responseCode, e.getMessage() != null ? e.getMessage() : "系统发生异常，请联系管理员！");
    }

    public static JsonResult<Void> error(ResponseCode responseCode, String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();

        jsonResult.setCode(responseCode.getValue());
        jsonResult.setMessage(message);

        return jsonResult;
    }

}