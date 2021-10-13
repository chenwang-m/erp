package com.codingfuture.util;

import lombok.Data;

@Data
public class ResultMsg {
    private int code;
    private String msg;
    private String message;

    private static final int CODE_SUCCESS = 200;
    private static final int CODE_ERROR = 500;
    private static final String MESSAGE_SUCCESS = "操作成功";
    private static final String MESSAGE_ERROR = "操作失败";

    public static ResultMsg success() {
        ResultMsg result = new ResultMsg();
        result.code = CODE_SUCCESS;
        result.msg = MESSAGE_SUCCESS;
        result.message = MESSAGE_SUCCESS;
        return result;
    }

    public static ResultMsg SUCCESS(int code, String message) {
        ResultMsg result = new ResultMsg();
        result.code = code;
        result.msg = message;
        result.message = message;
        return result;
    }

    public static ResultMsg error(int code, String message) {
        ResultMsg result = new ResultMsg();
        result.code = code;
        result.msg = message;
        result.message = message;
        return result;
    }
}

