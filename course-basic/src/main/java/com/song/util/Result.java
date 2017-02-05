package com.song.util;

import java.io.Serializable;

/**
 * Created by song on 2017/2/5.
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6829941384830110232L;

    private boolean success = false;
    private T data;
    private String message;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public static <T> Result<T> create() {
        return new Result<>();
    }

    public static <T> Result<T> create(boolean success) {
        return new Result<>(success);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
