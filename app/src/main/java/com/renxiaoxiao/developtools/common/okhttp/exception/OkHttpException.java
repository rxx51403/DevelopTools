package com.renxiaoxiao.developtools.common.okhttp.exception;

/**
 * Created by renxiaoxiao on 2016/11/1.
 * 自定义异常类
 */

public class OkHttpException extends Exception {

    // 序列化标识
    private static final long serialVersionUID = 1L;

    // 返回码
    private int eCode;

    // 错误的返回信息
    private Object eMsg;

    public OkHttpException(int eCode, Object eMsg) {
        this.eCode = eCode;
        this.eMsg = eMsg;
    }

    public int geteCode() {
        return eCode;
    }

    public void seteCode(int eCode) {
        this.eCode = eCode;
    }

    public Object geteMsg() {
        return eMsg;
    }

    public void seteMsg(Object eMsg) {
        this.eMsg = eMsg;
    }
}
