package com.codingfuture.util;


public class Result {
    private int code;
    private String message;
    private String msg;
    private Object data;

    private static final int CODE_SUCCESS = 200;
    private static final int CODE_ERROR = 500;
    private static final String MESSAGE_SUCCESS = "操作成功";
    private static final String MESSAGE_ERROR = "ERROR";

    public static Result success() {
        Result result = new Result();
        result.code = CODE_SUCCESS;
        result.message = MESSAGE_SUCCESS;
        result.msg = MESSAGE_SUCCESS;
        return result;
    }

    public static Result success(String message) {
        Result result = new Result();
        result.code = CODE_SUCCESS;
        result.message = message;
        result.msg = message;
        return result;
    }

    public static Result success(Object data) {
        Result result = success();
        result.data = data;
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.code = CODE_ERROR;
        result.message = MESSAGE_ERROR;
        result.msg = MESSAGE_ERROR;
        return result;
    }

    public static Result error(int code) {
        Result result = new Result();
        result.code = code;
        result.message = MESSAGE_ERROR;
        result.msg = MESSAGE_ERROR;
        return result;
    }

    public static Result error(int code, String message) {
        Result result = new Result();
        result.code = code;
        result.message = message;
        result.msg = message;
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.code = CODE_ERROR;
        result.message = message;
        result.msg = message;
        return result;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
