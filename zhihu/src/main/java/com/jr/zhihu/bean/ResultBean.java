package com.jr.zhihu.bean;

import javax.validation.ValidationException;
import java.io.Serializable;

public class ResultBean<T> implements Serializable {

    public static final Integer SUCCESS = 0;
    public static final Integer FATL = 1;
    public static final Integer VALIDATION_EXCEPTION = 2;
    public static final Integer USER_IS_EXIST=3;

    private Integer status = SUCCESS;
    private String msg = "success";
    private T data;

    public ResultBean() {
    }

    public ResultBean(T data) {
        this.data = data;
    }

    public ResultBean(Throwable throwable) {
        if (throwable instanceof ValidationException) {
            this.status = VALIDATION_EXCEPTION;
            this.msg = throwable.getMessage();
        } else {
            this.status = FATL;
            this.msg = "未知错误";
        }
    }

    public ResultBean(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
