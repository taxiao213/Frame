package com.hr.exception;

/**
 * 重写toString的Exception 简单应用到框架中
 * Created by zhangjutao on 16/8/25.
 */
public class MyException extends Throwable {
    private String detailMessage;

    public MyException(String detailMessage) {
        super(detailMessage);
        this.detailMessage = detailMessage;
    }

    @Override
    public String toString() {
        return detailMessage;
    }
}
