package com.renxiaoxiao.developtools.common.okhttp.listener;

/**
 * Created by renxiaoxiao on 2016/11/1.
 * 请求回调
 */

public interface DisposeDataListener {

    /**
     * 请求成功回调事件处理
     */
    public void onSuccess(Object responseObj);

    /**
     * 请求失败回调事件处理
     */
    public void onFailure(Object reasonObj);
}
